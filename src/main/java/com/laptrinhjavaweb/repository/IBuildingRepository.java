package com.laptrinhjavaweb.repository;

import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.paging.Pageble;

public interface IBuildingRepository extends GenericJDBC<BuildingEntity>{
	List<BuildingEntity> findAll(BuildingSearchBuilder builder,Pageble pageble);
}
