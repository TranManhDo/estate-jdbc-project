package com.laptrinhjavaweb.enums;

public enum DistrictEnum {
	Quan_1("Quan 1"),
	Quan_2("Quan 2"),
	Quan_3("Quan 3");
	private String value;
	
	DistrictEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
