package com.geog.Model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class City {
	private String co_code;
	private String cty_code;
	private String reg_code;
	private String cty_name;
	private String population;
	private String isCoastal;
	private String areaKM;
	
	public City() {
		super();
	}
	
	public City(String co_code, String cty_code, String reg_code, String cty_name, String population, String isCoastal, String areaKM) {
		super();
		this.co_code = co_code;
		this.cty_code = cty_code;
		this.reg_code = reg_code;
		this.cty_name = cty_name;
		this.population = population;
		this.isCoastal = isCoastal;
		this.areaKM = areaKM;
	}
	
	public String getCo_code() {
		return co_code;
	}
	
	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}
	
	public String getCty_code() {
		return cty_code;
	}
	
	public void setCty_code(String cty_code) {
		this.cty_code = cty_code;
	}
	
	public String getReg_code() {
		return reg_code;
	}
	
	public void setReg_code(String reg_code) {
		this.reg_code = reg_code;
	}
	
	public String getCty_name() {
		return cty_name;
	}
	
	public void setCty_name(String cty_name) {
		this.cty_name = cty_name;
	}
	
	public String getPopulation() {
		return population;
	}
	
	public void setPopulation(String population) {
		this.population = population;
	}
	
	public String getIsCoastal() {
		return isCoastal;
	}
	
	public void setIsCoastal(String isCoastal) {
		this.isCoastal = isCoastal;
	}
	
	public String getAreaKM() {
		return areaKM;
	}
	
	public void setAreaKM(String areaKM) {
		this.areaKM = areaKM;
	}
}
