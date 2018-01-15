package com.geog.Controlller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.SearchCity;

@ManagedBean
@SessionScoped
public class CityController {
	ArrayList<City> cities;
	DAO dao;
	
	public CityController() throws Exception{
		this.cities = new ArrayList<City>();
		this.dao = new DAO(); 
	}

	public void loadCities() throws Exception{
		cities.clear();
		cities = dao.loadCities();
		//System.out.println(countries.toString());
	}
	
	public ArrayList<City> getCities() {
		return cities;
	}
	
	public void addCity(City c) {
		try {
			dao.addCity(c);
			FacesContext.getCurrentInstance().getExternalContext().redirect("list_cities.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());        
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	ArrayList<City> citydetail = new ArrayList<City>();
	public String allCity(City city) throws Exception{
		//取当前的值
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("city", city);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("update_country.xhtml");
		return "all_details_city.xhtml";
		
	}
	
	public void findCity(SearchCity searchcity) throws Exception{
		cities.clear();
		cities = dao.findCity(searchcity);
		FacesContext.getCurrentInstance().getExternalContext().redirect("find_result_city.xhtml");
	}
	
	
	
	

}
