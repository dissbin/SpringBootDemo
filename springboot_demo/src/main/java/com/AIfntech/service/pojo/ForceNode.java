package com.AIfntech.service.pojo;

public class ForceNode {
	public ForceNode(Integer category, String name, Integer value, String label) {
		super();
		this.category = category;
		this.name = name;
		this.value = value;
		this.label = label;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public String toString() {
		return "ForceNode [category=" + category + ", name=" + name + ", value=" + value + ", label=" + label + "]";
	}
	private Integer category;
	private String name;
	private Integer value;
	private String label;
}
