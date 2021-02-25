package org.spring.boot.rest.config;

import java.util.HashMap;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.spring.boot.rest.model.Customer;
import org.spring.boot.rest.util.KafkaConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class KafkaListenerConfig {

	@Bean(name = "kafkaListener")
	public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListener() {

		ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, Customer> consumerFactory() {
		
		HashMap<String, Object> propsConfig = new HashMap<>();
		propsConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.HOST);
		propsConfig.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);
		propsConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propsConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(propsConfig,new StringDeserializer(),
												 new JsonDeserializer<>(Customer.class));
	}
}
