package com.geog.Controlller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.DAO;
import com.geog.Model.Country;
import com.geog.Model.Region;
@ManagedBean
@SessionScoped
public class RegionController {
	ArrayList<Region> regions;
	DAO dao;
	
	public ArrayList<Region> getRegion() {
		return regions;
	}
	
	public RegionController() throws Exception {
		this.regions = new ArrayList<Region>();
		this.dao = new DAO();
	}
	
	public void loadRegions() throws Exception {
		regions.clear();
		regions = dao.loadRegions();
	}
	
	public ArrayList<Region> getRegions(){
		return regions;
	}
	
	public void addRegions(Region region) {
		try {dao.addRegions(region);
		FacesContext.getCurrentInstance().getExternalContext().redirect("list_regions.xhtml");
	    } catch (Exception e) {
		FacesMessage message = new FacesMessage(e.toString());        
		FacesContext.getCurrentInstance().addMessage(null, message);
		}
		}
	
	
}
