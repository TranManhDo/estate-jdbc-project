package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	BuildingDTO update(BuildingDTO newBuilding);
	void delete(long id);
	<T> T findById(long id);
	//List<BuildingEntity> findAll(BuildingDTO buildingDTO);
	List<BuildingDTO> findAll(BuildingSearchBuilder builder,Pageble pageble);
}
