package com.geog.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import com.geog.Model.Mongo;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MongoDAO {
	private MongoDatabase database;
	
	public MongoDAO() throws Exception {
		MongoClient mongoClient = new MongoClient();
		database = mongoClient.getDatabase("headsOfStateDB");
	}
	
	public ArrayList<Mongo> loadMongo(){
		ArrayList<Mongo> headsOfStates = new ArrayList<Mongo>();
		MongoCollection<Document> headsOfState =  database.getCollection("headsOfState");
		FindIterable<Document> result = headsOfState.find();
		Gson gson = new Gson();
		for (Document d : result) {
			   Mongo mongo = gson.fromJson(d.toJson(), Mongo.class);
			   headsOfStates.add(mongo);
			}
		return headsOfStates;
	}
	
	public void addMongo(Mongo m) {
		MongoCollection<Document> headsOfState =  database.getCollection("headsOfState");
		Document myMog = new Document();
		myMog.append("_id", m.get_id())
		     .append("headOfState", m.getHeadOfState());
		headsOfState.insertOne(myMog);
	}
	
	public void deleteMongo(Mongo m) {
		MongoCollection<Document> headsOfState =  database.getCollection("headsOfState");
		Document myMog = new Document();
		myMog.append("_id", m.get_id())
	     	 .append("headOfState", m.getHeadOfState());
		headsOfState.deleteOne(myMog);
	}
}
