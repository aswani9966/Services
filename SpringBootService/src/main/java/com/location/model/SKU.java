package com.location.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sku_data")
public class SKU {
	@Id	
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="location_id")
	private Integer location_id;
	@Column(name="department_id")
	private Integer department_id;
	@Column(name="category_id")
	private Integer category_id;
	@Column(name="subcategory_id")
	private Integer subcategory_id;
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
	public Integer getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Integer getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(Integer subcategory_id) {
		this.subcategory_id = subcategory_id;
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
		response.append("location_id:");
		response.append(location_id);
		response.append(",");
		response.append("department_id:");
		response.append(department_id);
		response.append(",");
		response.append("category_id:");
		response.append(category_id);
		response.append(",");
		response.append("subcategory_id:");
		response.append(subcategory_id);
		return response.toString();
	}
}
