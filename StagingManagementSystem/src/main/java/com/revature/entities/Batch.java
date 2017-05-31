package com.revature.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Batch {

	@Id
	private Long id;

	private String instructor;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

}
