package com.dotamining.mongo.model;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;

/**
 * Abstract class for objects that wrap the Dota MongoDB BSON objects
 * 
 * @author Phillip Lopez - pgl5711@rit.edu
 */
public abstract class DotaObject {

	/**
	 * The document this class wraps
	 */
	private Document doc;
	
	/**
	 * The Dota MongoFacade Database
	 */
	private DotaMongoFacade facade = DotaMongoFacade.getInstance();
	
	/**
	 * Constructor
	 * @param doc
	 */
	public DotaObject(Document doc){
		this.doc = doc;
	}

	/**
	 * Returns the BSON Document this class wraps
	 * @return Document
	 */
	public Document getDoc() {
		return doc;
	}
	
	/**
	 * @return The Dota MongoDB Facade 
	 */
	public DotaMongoFacade db(){
		return facade;
	}
}
