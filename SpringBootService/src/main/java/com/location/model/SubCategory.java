package com.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "subcategory")
public class SubCategory {
	@Id	
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="category_id")
	private Integer category_id;
	
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
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
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
		response.append("category_id:");
		response.append(category_id);
		return response.toString();
	}
	
	
}
