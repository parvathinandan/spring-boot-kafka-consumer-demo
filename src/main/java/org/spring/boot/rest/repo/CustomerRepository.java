package org.spring.boot.rest.repo;

import java.io.Serializable;

import org.spring.boot.rest.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Serializable>{

}
