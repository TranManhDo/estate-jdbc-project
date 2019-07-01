package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.repository.IBuildingRepository;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.BuildingRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;
import com.laptrinhjavaweb.service.IBuildingService;

public class BuildingService implements IBuildingService {
	
	//@Inject
	private IBuildingRepository buildingRepository = new BuildingRepository();
	//@Inject
	private BuildingConverter buildingConverter = new BuildingConverter();
	//@Inject
	private IRentAreaRepository rentareaRepository = new RentAreaRepository();
	

	@Override
	public BuildingDTO save(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		buildingEntity.setCreatedBy("");
		buildingEntity.setType(StringUtils.join(buildingDTO.getBuildingTypes(), ","));
		
		Long id = buildingRepository.insert(buildingEntity);
		for(String item: buildingDTO.getRentArea().split(",")) {
			RentAreaEntity rentAreaEntity = new RentAreaEntity();
			rentAreaEntity.setValue(item);
			rentAreaEntity.setBuildingId(id);
			rentareaRepository.insert(rentAreaEntity);
		}
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}

	@Override
	public BuildingDTO update(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
		buildingEntity.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		buildingRepository.update(buildingEntity);
		return null;
	}

	public void delete(long id) {
		buildingRepository.delete(id);
		System.out.println("end delete");
	}

	/*@Override
	public <T> T findById(long id) {
		T building = buildingRepository.findById(id);
		System.out.println("abc");
		return building;
	}*/

	@Override
	public List<BuildingDTO> findAll(BuildingSearchBuilder builder, Pageble pageble) {
		List<BuildingEntity> buidingEntities = buildingRepository.findAll(builder, pageble);
		List<BuildingDTO> results = buidingEntities.stream().map(item -> buildingConverter.convertToDTO(item))
				.collect(Collectors.toList());
		return results;
	}

	@Override
	public BuildingDTO findById(long id) {
		
		return buildingConverter.convertToDTO(buildingRepository.findById(id));
	}


	/*
	 * @Override public List<BuildingEntity> findAll(BuildingDTO buildingDTO) {
	 * BuildingConverter buildingConverter = new BuildingConverter(); BuildingEntity
	 * buildingEntity = buildingConverter.convertToEntity(buildingDTO); Map<String,
	 * Object> maps = buildingRepository.convertEntityToMap(buildingEntity); Sorter
	 * sorter = new Sorter("id", "DESC"); PageRequest pr = new PageRequest(1, 5,
	 * sorter); List<BuildingEntity> listBuilding = buildingRepository.findAll(maps,
	 * pr, null); System.out.println("abc"); return listBuilding; }
	 */

}
