package com.laptrinhjavaweb.repository.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Table;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.mapper.ResultSetMapper;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.paging.Sorter;
import com.laptrinhjavaweb.repository.GenericJDBC;


public class AbstractJDBC<T> implements GenericJDBC<T>{
	
	private Class<T> zClass;
	
	public AbstractJDBC() {
		Type type = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) type;
		zClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3307/estate4month2019";
			String user = "root";
			String password = "1234";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public List<T> query(String sql, Object... parameters) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stm = conn.prepareStatement(sql);
			setParameter(stm, parameters);
			rs = stm.executeQuery();
			if(conn != null) {
				return resultSetMapper.mapRow(rs, this.zClass);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}
	
	@Override
	public void update(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sql);
			setParameter(stm, parameters);
			
			stm.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			stm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParameter(stm, parameters);
			
			int rowInserted = stm.executeUpdate();
			rs = stm.getGeneratedKeys();
			conn.commit();
			if(rowInserted>0)
				while(rs.next()) {
					Long id = rs.getLong(1);
					System.out.println("a row was inserted with id: " + id);
					return id;
				}
				
			
			
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		return null;
	}

	private static void setParameter(PreparedStatement stm, Object... parameters) {

		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				stm.setObject(index, parameters[i]);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public Long insert(Object object) {
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String sql = createSQLInsert();
			stm = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			if(conn!=null) {
				Class<?> zClass = object.getClass();
				
				Field[] fields = zClass.getDeclaredFields();
				for(int i = 0; i < fields.length; i++) {
					int index = i +1;
					Field field = fields[i];
					field.setAccessible(true);
					stm.setObject(index, field.get(object));
				}
				
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length+1;
				while(parentClass != null) {
					for(int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						stm.setObject(indexParent, field.get(object));
						indexParent = indexParent + 1;
					}
					parentClass = parentClass.getSuperclass();
				}
				
				int rowInserted = stm.executeUpdate();
				rs = stm.getGeneratedKeys();
				conn.commit();
				if(rowInserted>0)
					while(rs.next()) {
						Long id = rs.getLong(1);
						System.out.println("a row was inserted with id: " + id);
						return id;
					}
			}
			
		} catch (SQLException | IllegalAccessException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		return null;
	}

	
	@Override
	public void update(Object object) {
		Connection conn = null;
		PreparedStatement stm = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String sql = createSQLUpdate();
			stm = conn.prepareStatement(sql);
			if(conn!=null) {
				Class<?> zClass = object.getClass();
				
				Field[] fields = zClass.getDeclaredFields();
				for(int i = 0; i < fields.length; i++) {
					int index = i +1;
					Field field = fields[i];
					field.setAccessible(true);
					stm.setObject(index, field.get(object));
				}
				
				Class<?> parentClass = zClass.getSuperclass();
				int indexParent = fields.length+1;
				Object id = null;
				while(parentClass != null) {
					for(int i = 0; i < parentClass.getDeclaredFields().length; i++) {
						Field field = parentClass.getDeclaredFields()[i];
						field.setAccessible(true);
						String name = field.getName();
						if(!name.equals("id")) {
							stm.setObject(indexParent, field.get(object));
							indexParent = indexParent + 1;
						} else {
							id = field.get(object);
						}
					}
					parentClass = parentClass.getSuperclass();
				}
				stm.setObject(indexParent, id);
				stm.executeUpdate();
				conn.commit();		
			}
			
		} catch (SQLException | IllegalAccessException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
	}
	
	private String createSQLInsert() {
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		StringBuilder fields = new StringBuilder("");
		StringBuilder params = new StringBuilder("");
		
		for(Field field: zClass.getDeclaredFields()) {
			if(fields.length() > 1) {
				fields.append(",");
				params.append(",");
			}
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				fields.append(column.name());
				params.append("?");
			}
		}
		
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field: parentClass.getDeclaredFields()) {
				if(fields.length() > 1) {
					fields.append(",");
					params.append(",");
				}
				if(field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					fields.append(column.name());
					params.append("?");
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		String sql = "INSERT INTO "+tableName+"("+fields.toString()+") VALUES("+params.toString()+")";
		return sql;
	}


	private String createSQLUpdate() {
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		//Class<?> zClass = object.getClass();
		
		StringBuilder sets = new StringBuilder("");
		String where = null;
		
		for(Field field: zClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				Column column = field.getAnnotation(Column.class);
				String columnName = column.name();
				String value = columnName + " = ?";
				if(!columnName.equals("id")) {
					if(sets.length()>1) {
						sets.append(", ");
					}
					sets.append(value);
				} else {
					where = " WHERE " + value;
				}
			}
		}
		
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field: parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {
					Column column = field.getAnnotation(Column.class);
					String columnName = column.name();
					String value = columnName + " = ?";
					if(!columnName.equals("id")) {
						if(sets.length()>1) {
							sets.append(", ");
						}
						sets.append(value);
					} else {
						where = " WHERE " + value;
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		String sql = "UPDATE "+tableName+" SET "+sets.toString() + where;
		return sql;
	}

	
	
	
	@Override
	public List<T> search(Object object) {
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		BuildingEntity be = new BuildingEntity();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			String sql = createSQLsearch(object);
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			if(conn!=null) {
//				Collections.sort(resultSetMapper.mapRow(rs, this.zClass), new Comparator() {
//					@Override
//					public int compare(Object b1, Object b2) 
//				    { 
//				        return ((BuildingEntity)b1).getName().compareTo(((BuildingEntity)b2).getName()); 
//				    }
//				});
				return resultSetMapper.mapRow(rs, this.zClass);
				
			}
			
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		return null;
	}

	private String createSQLsearch(Object object) {
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		
		StringBuilder where = new StringBuilder("");
		
		for(Field field: zClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				String fieldName = field.getName();
				field.setAccessible(true);
				try {
					if(field.get(object) != null) {
						if(where.length()>1) {
							where.append(" and ");
						}
						where.append(fieldName + " like '%"+field.get(object)+"%' ");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				
				
			}
		}
		
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field: parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {
					String fieldName = field.getName();
					field.setAccessible(true);
					try {
						if(field.get(object) != null) {
							if(where.length()>1) {
								where.append(" and ");
							}
							where.append(fieldName + " like '%"+field.get(object)+"%' ");
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
					
					
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		String sql = "SELECT * FROM "+tableName + " WHERE "+ where.toString() + " ORDER BY id";
		return sql;
	}

	@Override
	public void delete(long id) {
		Connection conn = null;
		PreparedStatement stm = null;
		
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			String sql = "DELETE FROM "+tableName+" WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			if(conn!=null) {
				
				stm.setObject(1, id);
				stm.executeUpdate();
				conn.commit();		
			}
			
		} catch (SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
				try {
					if(conn!= null) {
						conn.close();
					}
					if(stm!= null)
						stm.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		
	}

	@Override
	public <T> T findById(long id) {
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM "+tableName+" WHERE id = ?";
		try {
			conn = getConnection();
			stm = conn.prepareStatement(sql); 
			stm.setObject(1, id);
			rs = stm.executeQuery();
			if(conn != null) {
				return resultSetMapper.mapRow(rs, this.zClass).get(0);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}
	
	public Map<String,Object> convertEntityToMap(Object object){
		Map<String,Object> resultMap =new HashMap<String, Object>(); 
		for(Field field: zClass.getDeclaredFields()) {
			if(field.isAnnotationPresent(Column.class)) {
				String fieldName = field.getName();
				field.setAccessible(true);
				try {
					if(field.get(object) != null) {
						resultMap.put(fieldName, field.get(object));
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		Class<?> parentClass = zClass.getSuperclass();
		while(parentClass != null) {
			for(Field field: parentClass.getDeclaredFields()) {
				if(field.isAnnotationPresent(Column.class)) {
					String fieldName = field.getName();
					field.setAccessible(true);
					try {
						if(field.get(object) != null) {
							if(field.get(object) != null) {
								resultMap.put(fieldName, field.get(object));
							}
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			parentClass = parentClass.getSuperclass();
		}
		
		return resultMap;
	}

	@Override
	public List<T> findAll(Map<String,Object> properties,Pageble pageble, Object...where) {
		
		ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		StringBuilder sql = createSQLfindAll(properties);
		if(where != null && where.length > 0) {
			sql.append(where[0]);
		}
		if(pageble!=null) {
			if(pageble.getSorter() != null) {
				Sorter sorter = pageble.getSorter();
				sql.append(" ORDER BY "+sorter.getSortName()+" "+sorter.getSortBy()+"");
			}
			if(pageble.getOffset() != null && pageble.getLimit() != null) {
				sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
			}
			
		}
		try {
			conn = getConnection();
			stm = conn.createStatement(); 
			rs = stm.executeQuery(sql.toString());
			if(conn != null) {
				return resultSetMapper.mapRow(rs, this.zClass);
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
		return null;
	}

	private StringBuilder createSQLfindAll(Map<String, Object> properties) {
		String tableName = "";
		if(zClass.isAnnotationPresent(Table.class)) {
			Table table = zClass.getAnnotation(Table.class);
			tableName = table.name();
		}
		
		StringBuilder results = new StringBuilder("SELECT * FROM "+tableName+" WHERE 1=1");
		if(properties!= null && properties.size()>0) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int  i = 0;
			for(Map.Entry<?,?> item:properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for(int i1=0;i1< params.length;i1++) {
				if(values[i1] instanceof String) {
					results.append(" and LOWER("+params[i1]+") LIKE '%"+values[i1]+"%' ");
				} else if(values[i1] instanceof Integer) {
					results.append(" and "+params[i1]+" = "+values[i1]+" ");
				}
				
			}
		}
		return results;
	}

}
