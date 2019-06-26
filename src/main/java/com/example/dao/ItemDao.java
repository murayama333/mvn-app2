package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.dto.Item;
import com.example.utils.ConnectionUtil;

public class ItemDao {

	public List<Item> findAll() {
		// TODO JavaNCSS Multiple try-with-resources error
		try (Connection con = ConnectionUtil.getConnection()) {
			try (PreparedStatement ps = con.prepareStatement("select id, name, price, category from items order by 1")) {
				ResultSet rs = ps.executeQuery();
				List<Item> itemList = new ArrayList<Item>();
				while (rs.next()) {
					Item item = new Item();
					item.setId(rs.getInt(1));
					item.setName(rs.getString(2));
					item.setPrice(rs.getInt(3));
					item.setCategory(rs.getInt(4));
					itemList.add(item);
				}
				return itemList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Item> findByItemName(String itemName) {
		// TODO JavaNCSS Multiple try-with-resources error
		try (Connection con = ConnectionUtil.getConnection()) {
			try(PreparedStatement ps = con.prepareStatement("select id, name, price, category from items where name like ?")){
				ps.setString(1, itemName + "%");
				ResultSet rs = ps.executeQuery();
				List<Item> itemList = new ArrayList<Item>();
				while (rs.next()) {
					Item item = new Item();
					item.setId(rs.getInt(1));
					item.setName(rs.getString(2));
					item.setPrice(rs.getInt(3));
					item.setCategory(rs.getInt(4));
					itemList.add(item);
				}
				return itemList;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void setErrorData() {
		try (Connection con = ConnectionUtil.getConnection()) {
		
			try (PreparedStatement ps = con.prepareStatement("delete from items")) {
				ps.executeUpdate();
			}				
			
			for(int i = 0; i < 10; i++){
				
				String sql = "insert into items values (?, ?, 0, 0)";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setInt(1, i + 1);
					ps.setString(2, "ERROR");
					ps.executeUpdate();
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void setDefaultData() {
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement ps = con.prepareStatement("delete from items")) {
				ps.executeUpdate();
			}				

			String[] sqlArray = {"insert into items values(1, 'ShoesA', 5000, 1)",
								"insert into items values(2, 'ShoesB', 10000, 1)",
								"insert into items values(3, 'ShoesC', 15000, 1)",
								"insert into items values(4, 'BootsA', 10000, 2)",
								"insert into items values(5, 'BootsB', 20000, 2)",
								"insert into items values(6, 'BootsC', 30000, 2)",
								"insert into items values(7, 'SandalsA', 1000, 3)",
								"insert into items values(8, 'SandalsB', 2000, 3)",
								"insert into items values(9, 'SandalsC', 3000, 3)"};

			for(String sql : sqlArray){
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.executeUpdate();
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void setBulkData(int size) {

		if(size > 100000){
			size = 100000; // max
		}
		
		try (Connection con = ConnectionUtil.getConnection()) {

			try (PreparedStatement ps = con.prepareStatement("delete from items")) {
				ps.executeUpdate();
			}				

			for(int i = 0; i < size; i++){
				
				String sql = "insert into items values (?, ?, 1000, 1)";
				try (PreparedStatement ps = con.prepareStatement(sql)) {
					ps.setInt(1, i + 1);
					ps.setString(2, "data" + i);
					ps.executeUpdate();
				}				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
