package com.laptrinhjavaweb.builder;

public class BuildingSearchBuilder {

	private String name;
	private String ward;
	private String street;
	//private Integer numberOfBasement;
	private String direction;
	private String level;
	
	private String costRentTo;
	private String costRentFrom;
	private String areaRentFrom;
	private String areaRentTo;
	private String[] buildingTypes = new String[] {};

//	public Integer getNumberOfBasement() {
//		return numberOfBasement;
//	}

	public String getDirection() {
		return direction;
	}

	public String getLevel() {
		return level;
	}

	public String getCostRentTo() {
		return costRentTo;
	}

	public String getCostRentFrom() {
		return costRentFrom;
	}

	public String getAreaRentFrom() {
		return areaRentFrom;
	}

	public String getAreaRentTo() {
		return areaRentTo;
	}

	public String[] getBuildingTypes() {
		return buildingTypes;
	}

	public String getName() {
		return name;
	}

	public String getWard() {
		return ward;
	}

	public String getStreet() {
		return street;
	}

	public BuildingSearchBuilder(Builder builder) {
		this.name = builder.name;
		this.ward = builder.ward;
		this.street = builder.street;
		this.direction = builder.direction;
		this.level = builder.level;
		//this.numberOfBasement = builder.numberOfBasement;
		this.areaRentFrom = builder.areaRentFrom;
		this.areaRentTo = builder.areaRentTo;
		this.costRentFrom = builder.costRentFrom;
		this.costRentTo = builder.costRentTo;
		this.buildingTypes = builder.buildingTypes;
	}

	public static class Builder {
		private String name;
		private String ward;
		private String street;
		//private Integer numberOfBasement;
		private String direction;
		private String level;
		private String costRentTo;
		private String costRentFrom;
		private String areaRentFrom;
		private String areaRentTo;
		private String[] buildingTypes = new String[] {};

//		public Builder setNumberOfBasement(Integer numberOfBasement) {
//			this.numberOfBasement = numberOfBasement;
//			return this;
//		}

		public Builder setDirection(String direction) {
			this.direction = direction;
			return this;
		}

		public Builder setLevel(String level) {
			this.level = level;
			return this;
		}

		public Builder setCostRentTo(String costRentTo) {
			this.costRentTo = costRentTo;
			return this;
		}

		public Builder setCostRentFrom(String costRentFrom) {
			this.costRentFrom = costRentFrom;
			return this;
		}

		public Builder setAreaRentFrom(String areaRentFrom) {
			this.areaRentFrom = areaRentFrom;
			return this;
		}

		public Builder setAreaRentTo(String areaRentTo) {
			this.areaRentTo = areaRentTo;
			return this;
		}

		public Builder setBuildingTypes(String[] buildingTypes) {
			this.buildingTypes = buildingTypes;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setWard(String ward) {
			this.ward = ward;
			return this;
		}

		public Builder setStreet(String street) {
			this.street = street;
			return this;
		}

		public BuildingSearchBuilder build() {
			return new BuildingSearchBuilder(this);
		}
	}
}
