package de.fernunihagen.d2l2.coherence.classes;

public class NEntity {
	String name;
	String type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public NEntity(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}
	public NEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
