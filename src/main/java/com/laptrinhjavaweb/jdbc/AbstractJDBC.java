package com.laptrinhjavaweb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.mapper.ResultSetMapper;

public class AbstractJDBC<T> {
	
	
	
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
	
//	public static <T> List<T> query(String sql, RowMapper<T> rowMapper)
//	{
//		List<T> results = new ArrayList<T>();
//		Connection conn = null;
//		PreparedStatement stm = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = getConnection();
//			stm = conn.prepareStatement(sql);
//			rs = stm.executeQuery();
//			if(conn != null) {
//				while(rs.next()) {
//					results.add(rowMapper.mapRow(rs));
//				}
//				return results;
//			}
//			
//			
//		} catch (Exception e) {
//			return null;
//		}finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//				if (stm != null) {
//					stm.close();
//				}
//				if (rs != null) {
//					rs.close();
//				}
//			} catch (SQLException e) {
//				return null;
//			}
//		}
//		return results;
//
//		
//	}
	
	public static <T> List<T> query(String sql,Class<T> zClass, Object...parameters)
	{
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
				return resultSetMapper.mapRow(rs, zClass);
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
	
	public static Long insert(String sql, Object...parameters) {
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
	
	
	public static void delete(String sql, Object...parameters)
	{
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
	
	private static void update(String sql, Object...parameters)
	{
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
}
