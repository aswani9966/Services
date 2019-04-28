package com.location.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {
	@Id	
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="department_id")
	private Integer department_id;
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
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	@Override
	public String toString() {
		StringBuffer response= new StringBuffer();
		response.append("id:");
		response.append(id);
		response.append(",");
		response.append("name:");
		response.append(name);
		response.append(",");
		response.append("department_id:");
		response.append(department_id);
		return response.toString();
	}
	
}
