package com.laptrinhjavaweb.enums;

public enum DistrictEnum {
	QUAN_1("Quan 1"),
	QUAN_2("Quan 2"),
	QUAN_3("Quan 3"),
	QUAN_4("Quan 4"),
	QUAN_5("Quan 5"),
	QUAN_6("Quan 6");
	private String value;
	
	DistrictEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
