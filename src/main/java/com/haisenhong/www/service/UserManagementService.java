package com.haisenhong.www.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.haisenhong.www.DAL.ConnectionFactory;
import com.haisenhong.www.model.User;

public class UserManagementService implements UserManagement {
	
	public static long curId;

	@Override
	public boolean saveUser(User user) {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("INSERT INTO Employee (name, department, title, age) VALUES(?, ?, ?, ?)");
//			ps.setLong(1, user.getId());
			ps.setString(1, user.getName());
			ps.setString(2, user.getDepartment());
			ps.setString(3, user.getTitle());
			ps.setInt(4, user.getAge());
			int num = ps.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()) {
				curId = rs.getLong("LAST_INSERT_ID()");
			}
			if(num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public User getUser(long id) {
		Connection conn = ConnectionFactory.getConnection();
		User user = new User();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, department, title, age FROM Employee WHERE id=" + id);
			if(rs.next()) {
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setDepartment(rs.getString("department"));
				user.setTitle(rs.getString("title"));
				user.setAge(rs.getInt("age"));
				return user;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		Connection conn = ConnectionFactory.getConnection();
		List<User> users = new ArrayList<User>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT id, name, department, title, age FROM Employee");
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setName(rs.getString("name"));
				user.setDepartment(rs.getString("department"));
				user.setTitle(rs.getString("title"));
				user.setAge(rs.getInt("age"));
				users.add(user);
			}
			rs.close();
			stmt.close();
			conn.close();
			return users;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public boolean updateUser(long id, User user) {
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE Employee SET name=?, department=?, title=?, age=? WHERE id=?");
			ps.setString(1, user.getName());
			ps.setString(2, user.getDepartment());
			ps.setString(3, user.getTitle());
			ps.setInt(4, user.getAge());
			ps.setLong(5, id);
			int num = ps.executeUpdate();
			ps.close();
			conn.close();
			if(num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteUser(long id) {
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int num = stmt.executeUpdate("DELETE FROM Employee WHERE id=" + id);
			stmt.close();
			conn.close();
			if(num == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAll() {
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement stmt = conn.createStatement();
			int num = stmt.executeUpdate("DELETE FROM Employee");
			System.out.println("Delete " + num + " rows");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean isUserExists(long id) {
		Connection conn = ConnectionFactory.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM Employee WHERE id=" + id);
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
}
