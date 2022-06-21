package de.fernunihagen.d2l2.coherence.classes;

public class CorefEntity {
	int begin;
	int end;
	String name;
	
	public CorefEntity(int begin, int end, String name) {
		super();
		this.begin = begin;
		this.end = end;
		this.name = name;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
