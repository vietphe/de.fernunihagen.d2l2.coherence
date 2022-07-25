package de.fernunihagen.d2l2.coherence.classes;

public class RootVerb {
	int beginPosition;
	int endPosition;
	String name;
	public RootVerb(int beginPosition, int endPosition, String name) {
		super();
		this.beginPosition = beginPosition;
		this.endPosition = endPosition;
		this.name = name;		
	}
	public RootVerb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getBeginPosition() {
		return beginPosition;
	}
	public void setBeginPosition(int beginPosition) {
		this.beginPosition = beginPosition;
	}
	public int getEndPosition() {
		return endPosition;
	}
	public void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RootVerb rv = (RootVerb) o;
        return  beginPosition == rv.beginPosition &&
        		endPosition == rv.endPosition &&
        		name.equals(rv.name) ;        		
    }
	
}
