package com.example.dbtest;

public class Region {

private String code = null;
private String name = null;


public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "Client [code=" + code + ", name=" + name +"]";
}



}