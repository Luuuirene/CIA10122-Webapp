package com.activity.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;





public class Act_CateDAOJDBCImpl implements Act_CateDao_interface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cactus?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "1234";
	
	private static final String INSERT_STMT = "INSERT INTO activity_category(activity_category_name,activity_category_info) VALUES(?, ?)";
	private static final String UPDATE_STMT = "UPDATE activity_category set activity_category_name=? ,activity_category_info= ? where activity_category_id = ?";
	private static final String DELETE_STMT = "DELETE FROM activity_category where activity_category_id = ?";
	private static final String FIND_BY_PK = "SELECT activity_category_id , activity_category_name, activity_category_info FROM  activity_category where activity_category_id = ?";
	private static final String GET_ALL = "SELECT activity_category_id , activity_category_name, activity_category_info FROM  activity_category";
	
	
	///////////////////////////////////////////////
	@Override
	public void insert(Act_CateVO act_CateVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT_STMT);

			ps.setString(1, act_CateVO.getActivity_category_name());
			ps.setString(2, act_CateVO.getActivity_category_info());

			ps.executeUpdate("set auto_increment_offset=1;"); //初始值為1
			ps.executeUpdate("set auto_increment_increment=1;");//每次增加1
			ps.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	///////////////////////////////////////////////
	@Override
	public void update(Act_CateVO act_CateVO) {
		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT_STMT);
			
			ps.setInt(1, act_CateVO.getActivity_category_id());
			ps.setString(2, act_CateVO.getActivity_category_name());
			ps.setString(3, act_CateVO.getActivity_category_info());
			
			ps.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	///////////////////////////////////////////////
	@Override
	public void delete(Integer activity_category_id) {

		try (
			Connection con = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement ps = con.prepareStatement(DELETE_STMT)){
			
			Class.forName(driver);
			ps.setInt(1, activity_category_id);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
			// 適當的錯誤處理
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	///////////////////////////////////////////////
	@Override
	public Act_CateVO findByPrimaryKey(Integer activity_category_id) {
		
		Act_CateVO act_CateVO = null;
		

		try (
			Connection con = DriverManager.getConnection(url, userid, passwd);
			PreparedStatement ps = con.prepareStatement(FIND_BY_PK)){
			
			Class.forName(driver);
			ps.setInt(1, activity_category_id);
			
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					act_CateVO = new Act_CateVO();
					act_CateVO.setActivity_category_id(rs.getInt("activity_category_id"));
					act_CateVO.setActivity_category_name(rs.getString("activity_category_name"));
					act_CateVO.setActivity_category_info(rs.getString("activity_category_info"));
					
					
				}
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			// 適當的錯誤處理
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return act_CateVO;
	}
	
	///////////////////////////////////////////////
	@Override
	public List<Act_CateVO> getAll() {
		
		List<Act_CateVO> list = new ArrayList<Act_CateVO>();
		try (
				Connection con = DriverManager.getConnection(url, userid, passwd);
				PreparedStatement ps = con.prepareStatement(GET_ALL)){
				
				Class.forName(driver);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					Act_CateVO act_CateVO = new Act_CateVO();
					act_CateVO.setActivity_category_id(rs.getInt("activity_category_id"));
					act_CateVO.setActivity_category_name(rs.getString("activity_category_name"));
					act_CateVO.setActivity_category_info(rs.getString("activity_category_info"));
					list.add(act_CateVO); // Store the row in the list
				}
		}catch (SQLException e) {
			e.printStackTrace();
			// 適當的錯誤處理
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return list;
	}
	
	
	
	
}
