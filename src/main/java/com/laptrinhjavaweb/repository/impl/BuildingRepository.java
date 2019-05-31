package com.laptrinhjavaweb.repository.impl;

import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.IBuildingRepository;

public class BuildingRepository extends AbstractJDBC<BuildingEntity> implements IBuildingRepository{

	/*@Override
	public Long insert(BuildingEntity entity) {
		StringBuilder sql = new StringBuilder("Insert into building( name ,numberofbasement ,buildingarea ,district ,ward ,street ,structure ,");
		sql.append("costrent ,costdescription ,servicecost ,carcost ,motorbikecost ,overtimecost ,electricitycost ,deposit ,payment ,timecontract ,timedecorator ,");
		sql.append("managername ,managerphone , type, createddate ,modifieddate ,createdby ,modifiedby)");
		sql.append(" values( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?, ? )");
		return super.insert(sql.toString(), entity.getName(),entity.getNumberOfBasement(),
				entity.getBuildingArea(),entity.getDistrict(),
				entity.getWard(), entity.getStreet(),
				entity.getStructure(), entity.getCostRent(),
				entity.getCostDescription(),entity.getServiceCost(),
				entity.getCarCost(),entity.getMotorbikeCost(),
				entity.getElectricityCost(), entity.getElectricityCost(),
				entity.getDeposit(),entity.getPayment(), 
				entity.getTimeContract(),entity.getTimeDecorator(), 
				entity.getManagerName(),entity.getManagerPhone(),entity.getType(),
				entity.getCreateDate(),entity.getModifiedDate(),
				entity.getCreateBy(),entity.getModifiedBy());
	}*/

}
