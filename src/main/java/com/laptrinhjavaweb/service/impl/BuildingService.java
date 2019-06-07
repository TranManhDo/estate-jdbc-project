package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService{
	
	private IBuildingRepository buildingRepository;
	
	public BuildingService() {
		buildingRepository = new BuildingRepository();
	}

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long id = buildingRepository.insert(buildingEntity);
		return null;
	}

	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return null;
	}
	
	public void delete(long id) {
		buildingRepository.delete(id);
		System.out.println("end delete");
	}

	@Override
	public <T> T findById(long id) {
		T building = buildingRepository.findById(id);
		System.out.println("abc");
		return building;
	}

	@Override
	public List<BuildingEntity> findAll(BuildingDTO buildingDTO) {
		BuildingConverter buildingConverter = new BuildingConverter();
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		Map<String, Object> maps = buildingRepository.convertEntityToMap(buildingEntity);
		Sorter sorter = new Sorter("id", "DESC");
		PageRequest pr = new PageRequest(1, 5, sorter);
		List<BuildingEntity> listBuilding = buildingRepository.findAll(maps, pr, null);
		System.out.println("abc");
		return listBuilding;
	}


}
