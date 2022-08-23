package de.fernunihagen.d2l2.coherence.classes;

public class CFEntityWithCoref {
	int sentenceIndex;
	int begin;
	int end;
	String name;
	String dependencyType;
	String id;
	String firstMention;
	public CFEntityWithCoref(int sentenceIndex,int begin, int end, String name, String dependencyType, String id, String firstMention) {
		super();
		this.sentenceIndex = sentenceIndex;
		this.begin = begin;
		this.end = end;
		this.name = name;
		this.dependencyType = dependencyType;
		this.id = id;
		this.firstMention = firstMention;
	}
	public CFEntityWithCoref() {
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public int getSentenceIndex() {
		return sentenceIndex;
	}
	public void setSentenceIndex(int sentenceIndex) {
		this.sentenceIndex = sentenceIndex;
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
        CFEntityWithCoref entity = (CFEntityWithCoref) o;
        return  id == entity.id;
    }
	
	
}
