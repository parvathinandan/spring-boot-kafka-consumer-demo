package org.spring.boot.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.spring.boot.rest.model.Customer;
import org.spring.boot.rest.repo.CustomerRepository;
import org.spring.boot.rest.util.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	private List<Customer> users = new ArrayList<>();
	
	@Autowired
	private CustomerRepository repo;
	
	@KafkaListener(topics = KafkaConstants.TOPIC,groupId = KafkaConstants.GROUP_ID,
												 containerFactory = "kafkaListener")
	public List<Customer> listener(List<Customer> customerList) {
		System.out.println("*********Message received from kafka topic*******" );
		for (Customer cutomer : customerList) {
			
			users.add(cutomer);
			repo.save(cutomer);
		}
		return customerList;
	}
	
	@GetMapping(value = "/allCustomers",produces = "application/json")
	public List<Customer> customerList() {
		int size = users.size();
		System.out.println("size :"+size);
		return users;
	}

	@GetMapping(value = "/size",produces = "application/json")
	public Integer size() {
		int size = users.size();
		System.out.println("size :"+size);
		return size;
	}
}
