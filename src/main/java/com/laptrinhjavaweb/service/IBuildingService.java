package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;

public interface IBuildingService {
	BuildingDTO save(BuildingDTO newBuilding);
	BuildingDTO update(BuildingDTO newBuilding);
	BuildingDTO delete(BuildingDTO building);
	List<BuildingEntity> findById(BuildingDTO building);
	List<BuildingEntity> search(BuildingDTO building);
}
