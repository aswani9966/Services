package com.location.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {
	@Id	
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	@Column(name="name")
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuffer response= new StringBuffer();
		response.append("id:");
		response.append(id);
		response.append(",");
		response.append("name:");
		response.append(name);
		return response.toString();
	}
	
	
}
