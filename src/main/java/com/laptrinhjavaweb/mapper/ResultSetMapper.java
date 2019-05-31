package com.laptrinhjavaweb.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

public class ResultSetMapper<T> {
	public List<T> mapRow(ResultSet rs, Class zClass){
		List<T> results = new ArrayList<>();
		if(zClass.isAnnotationPresent(Entity.class)) {
			try {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				//get field in class
				Field fields[] = zClass.getDeclaredFields();
				
				while(rs.next()) {
					T object = (T) zClass.newInstance();
					
					for(int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
						String columnName = resultSetMetaData.getColumnName(i+1);
						Object columnValue 	= rs.getObject(i+1);
						
						convertResultSetToEntity(fields, columnName, columnValue, object);
						
						Class parentClass = zClass.getSuperclass();
						while(parentClass != null) {
							Field fieldParents[] = parentClass.getDeclaredFields();
							convertResultSetToEntity(fieldParents, columnName, columnValue, object);
							parentClass = parentClass.getSuperclass();
						}
					}
					results.add(object);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}
	
	public void convertResultSetToEntity(Field[] fields,String columnName,Object columnValue, T object) throws IllegalAccessException, InvocationTargetException {
		for(Field field : fields) {
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				if(column.name().equals(columnName) && columnValue != null) {
					BeanUtils.setProperty(object, field.getName(), columnValue);
					break;
				}
			}
		}
	}
}
