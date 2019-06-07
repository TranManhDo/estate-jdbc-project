package com.laptrinhjavaweb.service;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	BuildingDTO update(BuildingDTO newBuilding);
	void delete(long id);
	<T> T findById(long id);
	List<BuildingEntity> findAll(BuildingDTO buildingDTO);
}
