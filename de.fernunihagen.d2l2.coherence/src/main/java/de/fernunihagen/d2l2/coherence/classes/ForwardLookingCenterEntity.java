package de.fernunihagen.d2l2.coherence.classes;

public class ForwardLookingCenterEntity {
	int begin;
	int end;
	String name;
	String dependencyType;
	public ForwardLookingCenterEntity(int begin, int end, String name, String dependencyType) {
		super();
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.dependencyType = dependencyType;
	}
	public ForwardLookingCenterEntity() {
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
	public String getDependencyType() {
		return dependencyType;
	}
	public void setDependencyType(String dependencyType) {
		this.dependencyType = dependencyType;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ForwardLookingCenterEntity entity = (ForwardLookingCenterEntity) o;
        return  begin == entity.begin &&
        		end == entity.end &&
        		name.equals(entity.name) &&
        		dependencyType.equals(entity.dependencyType);
    }
	
	
}
