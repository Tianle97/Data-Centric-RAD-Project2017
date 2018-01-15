package com.geog.Controlller;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.DAO.MongoDAO;
import com.geog.Model.Country;
import com.geog.Model.Mongo;

@ManagedBean
@SessionScoped
public class CountryController {
	ArrayList<Country> countries;
	DAO dao;
	
	public CountryController() throws Exception{
		this.countries = new ArrayList<Country>();
		this.dao = new DAO(); 
	}

	public void loadCountries() throws Exception{
		countries.clear();
		countries = dao.loadCountries();
		//System.out.println(countries.toString());
	}
	
	public ArrayList<Country> getCountries() {
		return countries;
	}
	
	public void deleteCountry(Country c) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("c", c);
		try {
			dao.deleteCountry(c);
			FacesContext.getCurrentInstance().getExternalContext().redirect("list_countries.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void addCountry(Country country) {
		try {
			dao.addCountry(country);
			FacesContext.getCurrentInstance().getExternalContext().redirect("list_countries.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());        
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public String updateCountry(Country country) throws Exception{
		//取当前的值
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("country", country);
		//FacesContext.getCurrentInstance().getExternalContext().redirect("update_country.xhtml");
		return "update_country.xhtml";
	}
	
	public void renewCountry(Country country) throws Exception{
		dao.renewCountry(country);
		FacesContext.getCurrentInstance().getExternalContext().redirect("list_countries.xhtml");
	}
}
