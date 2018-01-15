package com.geog.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Flag;
import com.geog.Model.Region;
import com.geog.Model.SearchCity;

public class DAO {
	private DataSource mysqlDS;

	public DAO(DataSource mysqlDS) {
		super();
		this.mysqlDS = mysqlDS;
	}

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	//Country
	public ArrayList<Country> loadCountries() throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * " + "from country ");
		myStmt.executeQuery();
		ResultSet rs = myStmt.executeQuery();	
		ArrayList<Country> countries = new ArrayList<Country>();
		while (rs.next()) {
			countries.add(new Country(rs.getString("co_code"), rs.getString("co_name"),
					rs.getString("co_details")));
		}
		return countries;
	}
	
	public void deleteCountry(Country c) throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from country where co_code = ? ");
		myStmt.setString(1, c.getCo_code());
		myStmt.executeUpdate();
	}
	
	public void addCountry(Country c) throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("insert into country values (?,?,?)");
		myStmt.setString(1, c.getCo_code());
		myStmt.setString(2, c.getCo_name());
		myStmt.setString(3, c.getCo_details());
		myStmt.executeUpdate();      // Querry 查找获取资料  Update 操作更新
	}
	
	public void renewCountry(Country c) throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("update country set co_name = ?,co_details = ? where co_code = ?");
		myStmt.setString(1, c.getCo_name());
		myStmt.setString(2, c.getCo_details());
		myStmt.setString(3, c.getCo_code());
		myStmt.executeUpdate();
	}

	//Region
	public ArrayList<Region> loadRegions() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from region ");
		myStmt.executeQuery();
		ResultSet rs = myStmt.executeQuery();	
		ArrayList<Region> regions = new ArrayList<Region>();
		while (rs.next()) {
			regions.add(new Region(rs.getString("co_code"), rs.getString("reg_code"),
					rs.getString("reg_name"),rs.getString("reg_desc")));
		}
		return regions;
	}

	public void addRegions(Region region) throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("insert into region values (?,?,?,?)");
		myStmt.setString(1, region.getCo_code());
		myStmt.setString(2, region.getReg_code());
		myStmt.setString(3, region.getReg_name());
		myStmt.setString(4, region.getReg_desc());
		myStmt.executeUpdate(); 		
	}

	//City
	public ArrayList<City> loadCities() throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from city ");
		ResultSet rs = myStmt.executeQuery();	
		ArrayList<City> cities = new ArrayList<City>();
		while (rs.next()) {
			cities.add(new City(rs.getString("cty_code"), rs.getString("co_code"),
					rs.getString("reg_code"),rs.getString("cty_name"),rs.getString("population"),
					rs.getString("isCoastal"),rs.getString("areaKM")));
		}
		return cities;
	}

	public City allDetailsCity(String cty_code) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from city where cty_code = ? ");
		myStmt.setString(1, cty_code);
		ResultSet rs = myStmt.executeQuery();
		City citydetail = new City(rs.getString("cty_code"), rs.getString("co_code"),
				rs.getString("reg_code"),rs.getString("cty_name"),rs.getString("population"),
				rs.getString("isCoastal"),rs.getString("areaKM"));
		return citydetail;
		
	}

	public void addCity(City c) throws SQLException {
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("insert into city values (?,?,?,?,?,?,?)");
		myStmt.setString(1, c.getCty_code());
		myStmt.setString(2, c.getCo_code());
		myStmt.setString(3, c.getReg_code());
		myStmt.setString(4, c.getCty_name());
		myStmt.setString(5, c.getPopulation());
		myStmt.setString(6, c.getIsCoastal());
		myStmt.setString(7, c.getAreaKM());
		myStmt.executeUpdate(); 			
	}
	
	public  ArrayList<City> findCity(SearchCity sc) throws SQLException{
		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt;
		String a = null;
		if(sc.getCo_code().isEmpty()) {
			if(sc.getPopulation().isEmpty()) {
				myStmt = conn.prepareStatement("select * from city where isCoastal = ?");
				myStmt.setString(1, sc.getIsCoastal());
			}else {
			if(sc.getFlag().equals(Flag.great_than.toString())) { //toString 转化为字符串
				  a = ">";
			}else if(sc.getFlag().equals(Flag.less_than.toString())){
				  a = "<";
			}else if(sc.getFlag().equals(Flag.equal.toString())){
				  a = "=";
			}
			myStmt = conn.prepareStatement("select * from city where population "+a+"? and isCoastal = ?");
			myStmt.setString(1, sc.getPopulation());
			myStmt.setString(2, sc.getIsCoastal());
			}
		}else {
				if(sc.getPopulation().isEmpty()) {
					myStmt = conn.prepareStatement("select * from city where isCoastal = ? and co_code = ?");
					myStmt.setString(1, sc.getIsCoastal());
					myStmt.setString(2, sc.getCo_code());
				}else {
					if(sc.getFlag().equals(Flag.great_than.toString())) {
						a = ">";
					}else if(sc.getFlag().equals(Flag.less_than.toString())){
					    a = "<";
				    }else if(sc.getFlag().equals(Flag.equal.toString())){
					    a = "=";
					    }
				myStmt = conn.prepareStatement("select * from city where population "+a+" ? and isCoastal = ? and co_code = ?");
				myStmt.setString(1, sc.getPopulation());
				myStmt.setString(2, sc.getIsCoastal());
				myStmt.setString(3, sc.getCo_code());
			}
		}
		ResultSet rs = myStmt.executeQuery();	
		ArrayList<City> cities = new ArrayList<City>();
		while (rs.next()) {
			cities.add(new City(rs.getString("cty_code"), rs.getString("co_code"),
					rs.getString("reg_code"),rs.getString("cty_name"),rs.getString("population"),
					rs.getString("isCoastal"),rs.getString("areaKM")));
		}
		return cities;
	}
}
			
	
	


