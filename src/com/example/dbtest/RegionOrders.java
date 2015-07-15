package com.example.dbtest;

public class RegionOrders {

private String idNum = null;
private String name = null;
private String yrCourse = null;
private String cellNum = null;
private String client = null;


public String getIdNum() {
	return idNum;
}
public void setIdNum(String idNum) {
	this.idNum = idNum;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getYrCourse() {
	return yrCourse;
}
public void setYrCourse(String yrCourse) {
	this.yrCourse = yrCourse;
}
public String getCellNum() {
	return cellNum;
}
public void setCellNum(String cellNum) {
	this.cellNum = cellNum;
}
public String getClient() {
	return client;
}
public void setClient(String client) {
	this.client = client;
}
@Override
public String toString() {
	return "Client [idNum=" + idNum + ", name=" + name +", yrCourse=" + yrCourse +", cellNum=" + cellNum + ", client=" + client +"]";
}



}