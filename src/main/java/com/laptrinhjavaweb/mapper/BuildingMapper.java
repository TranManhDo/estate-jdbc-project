package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.dto.BuildingDTO;

public class BuildingMapper implements RowMapper<BuildingDTO>{

	@Override
	public BuildingDTO mapRow(ResultSet rs) {
		BuildingDTO bd = new BuildingDTO();
		try {
			bd.setName(rs.getString("name"));
			bd.setWard(rs.getString("ward"));
			bd.setStreet(rs.getString("street"));
			bd.setStructure(rs.getString("structure"));
			bd.setNumberOfBasement(rs.getInt("numberofbasement"));
			bd.setBuildingArea(rs.getInt("buildingarea"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bd;
	}
	
}
