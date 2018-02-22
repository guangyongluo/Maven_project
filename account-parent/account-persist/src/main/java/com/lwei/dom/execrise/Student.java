package com.lwei.dom.execrise;

public class Student {
    private int id;
    private String name;
    private int javaScore;
    private int oracelScore;
    private int vbScore;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getJavaScore() {
		return javaScore;
	}
	public void setJavaScore(int javaScore) {
		this.javaScore = javaScore;
	}
	public int getOracelScore() {
		return oracelScore;
	}
	public void setOracelScore(int oracelScore) {
		this.oracelScore = oracelScore;
	}
	public int getVbScore() {
		return vbScore;
	}
	public void setVbScore(int vbScore) {
		this.vbScore = vbScore;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", javaScore=" + javaScore + ", oracelScore=" + oracelScore
				+ ", vbScore=" + vbScore + "]";
	}
}
