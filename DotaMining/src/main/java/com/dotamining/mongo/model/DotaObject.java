package com.dotamining.mongo.model;

import org.bson.Document;

import com.dotamining.mongo.MongoFacade;

public abstract class DotaObject {

	private Document doc;
	private MongoFacade facade = MongoFacade.getInstance();
	
	public DotaObject(Document doc){
		this.setDoc(doc);
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	public MongoFacade db(){
		return facade;
	}
}
