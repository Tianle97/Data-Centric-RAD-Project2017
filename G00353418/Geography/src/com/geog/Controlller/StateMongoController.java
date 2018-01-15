package com.geog.Controlller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.geog.DAO.MongoDAO;
import com.geog.Model.Country;
import com.geog.Model.Mongo;

@ManagedBean
@SessionScoped
public class StateMongoController {
	ArrayList<Mongo> mongo;
	MongoDAO mongodao;
	
	public StateMongoController() throws Exception {
		this.mongo = new ArrayList<Mongo>();
		this.mongodao = new MongoDAO();
	}
	
	public void loadMongo(){
		mongo.clear();
		mongo = mongodao.loadMongo();
	}
	
	public ArrayList<Mongo> getMongo(){
		return mongo;
	}
	
	public void addMongo(Mongo m) {
		try {
			mongodao.addMongo(m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("list_head_of_state.xhtml");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(e.toString());        
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	public void deleteMongo(Mongo m) throws IOException {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("m", m);
			mongodao.deleteMongo(m);
			FacesContext.getCurrentInstance().getExternalContext().redirect("list_head_of_state.xhtml");
	}
}
