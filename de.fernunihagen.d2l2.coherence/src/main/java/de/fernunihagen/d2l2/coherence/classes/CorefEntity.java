package de.fernunihagen.d2l2.coherence.classes;

public class CorefEntity {
	int begin;
	int end;
	String name;
	String id;
	String firstMention;
	public CorefEntity(int begin, int end, String name, String id, String firstMention) {
		super();
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.id = id;
		this.firstMention = firstMention;
	}
	public CorefEntity() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstMention() {
		return firstMention;
	}
	public void setFirstMention(String firstMention) {
		this.firstMention = firstMention;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CorefEntity e = (CorefEntity) o;
        return  begin == e.begin &&
        		end == e.end &&
        		name.equals(e.name) ;        		
    }
}
