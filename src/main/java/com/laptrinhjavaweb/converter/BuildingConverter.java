package com.laptrinhjavaweb.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;

import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.repository.IRentAreaRepository;
import com.laptrinhjavaweb.repository.impl.RentAreaRepository;

public class BuildingConverter {

	//@Inject
	private IRentAreaRepository rentAreaRepository = new RentAreaRepository();

	public BuildingDTO convertToDTO(BuildingEntity buildingEntity) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		Map<String, Object> condition = new HashMap<>();
		condition.put("buildingid", buildingEntity.getId());
		List<String> areas = rentAreaRepository.findAll(condition, new PageRequest(null, null, null)).stream()
				.map(RentAreaEntity::getValue).collect(Collectors.toList());
		if(areas.size()>0) {
			result.setRentArea(StringUtils.join(areas,","));
		}
		if(StringUtils.isNotBlank(buildingEntity.getType())) {
			result.setBuildingTypes(buildingEntity.getType().split(","));
		}
		return result;
	}

	public BuildingEntity convertToEntity(BuildingDTO buildingDTO) {
		ModelMapper modelMapper = new ModelMapper();
		BuildingEntity result = modelMapper.map(buildingDTO, BuildingEntity.class);
		if(StringUtils.isNotBlank(buildingDTO.getNumberOfBasement())) {
			result.setNumberOfBasement(Integer.parseInt(buildingDTO.getNumberOfBasement()));
		}
		if(StringUtils.isNotBlank(buildingDTO.getBuildingArea())) {
			result.setBuildingArea(Integer.parseInt(buildingDTO.getBuildingArea()));
		}
		
		return result;
	}
}
