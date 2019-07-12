package com.AIfntech.service.pojo;

public class ForceLink {
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
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
	public ForceLink(String source, String target, Integer value, String label) {
		super();
		this.source = source;
		this.target = target;
		this.value = value;
		this.label = label;
	}
	@Override
	public String toString() {
		return "ForceLink [source=" + source + ", target=" + target + ", value=" + value + ", label=" + label + "]";
	}
	private String source;
	private String target;
	private Integer value;
	private String label;
}
