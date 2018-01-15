package com.geog.Model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SearchCity {
	private String flag;
	private String population;
	private String co_code;
	private String isCoastal;
	
	public SearchCity() {
		super();
	}
	
	public SearchCity(String flag, String population, String co_code, String isCoastal) {
		this.flag = flag;
		this.population = population;
		this.co_code = co_code;
		this.isCoastal = isCoastal;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getCo_code() {
		return co_code;
	}

	public void setCo_code(String co_code) {
		this.co_code = co_code;
	}

	public String getIsCoastal() {
		return isCoastal;
	}

	public void setIsCoastal(String isCoastal) {
		this.isCoastal = isCoastal;
	}

	
}
