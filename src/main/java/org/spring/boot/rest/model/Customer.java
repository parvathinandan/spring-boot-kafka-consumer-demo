package org.spring.boot.rest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer cid;
	private String name;
	private String city;
}
