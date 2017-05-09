package com.dotamining.mongo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.dotamining.mongo.model.Chat;
import com.dotamining.mongo.model.Match;
import com.dotamining.mongo.model.Player;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Simple interface that abstracts calls to the Dota MongoDB
 * 
\* @author Phillip Lopez - pgl5711@rit.edu - Imran Bohari - iib8085@rit.edu
 *
 */
public class DotaMongoFacade {
	
	
	/**
	 * Singleton
	 */
	private static DotaMongoFacade instance = new DotaMongoFacade();
	
	/**
	 * MongoClient API
	 */
	public MongoClient mongo;
	
	/**
	 * The opened database
	 */
	public MongoDatabase db;
	
	/**
	 * Matches collection
	 */
	public MongoCollection<Document> matches;
	
	/**
	 * Players Collection
	 */
	public MongoCollection<Document> players;
	
	/**
	 * Chat Collection
	 */
	public MongoCollection<Document> chat;
	
	/**
	 * Constructor - Establishes a connection to the mongo db
	 */
	private DotaMongoFacade(){
        try {
			System.getProperties().load(new FileInputStream("mongo.properties"));
		} catch (IOException e) {
			System.err.println("MISSING MONGO.PROPERTIES FILE. DB WILL NOT LOAD CORRECTLY.");
		}
		mongo = new MongoClient(System.getProperty("mongo.address"), 
				Integer.valueOf(System.getProperty("mongo.port")));		
		mongo = new MongoClient("localhost", 27017);
		db = mongo.getDatabase("DotaDB");
		matches = db.getCollection("Match");
		players = db.getCollection("Players");
		chat = db.getCollection("Chat");

	}
	
	/**
	 * Returns the instance of the DotaMongoFacade.
	 * A single instance will exist across the entire app
	 * 
	 * @return DotaMongoFacade
	 */
	public static DotaMongoFacade getInstance(){
		return instance;
	}
	
	/**
	 * Returns the match for the given match_id
	 * @return List<Match>
	 */
	public Match getMatch(Integer match_id){
		Document query = new Document("match_id", match_id);
		return new Match(matches.find(query).first());
	}
	
	/**
	 * Returns all matches in this database
	 * @return List<Match>
	 */
	public List<Match> getMatches(){
		List<Match> matchList = new ArrayList<>();
		for(Document matchDoc : matches.find()){
			matchList.add(new Match(matchDoc));
		}
		return matchList;
	}

	/**
	 * Gets the 10 Players that played in the given match
	 * @param match_id
	 * @return List<Player>
	 */
	public List<Player> getPlayersForMatch(Integer match_id){
		List<Player> playerList = new ArrayList<>();
		Document query = new Document("match_id", match_id);
		for(Document playerDoc : players.find(query)){
			playerList.add(new Player(playerDoc));
		}
		return playerList;
	}	
	
	/**
	 * Gets all chat messages in the database
	 * @return List<Chat>
	 */
	public List<Chat> getChatMessages(){
		List<Chat> chatList = new ArrayList<>();
		for(Document chatDoc : chat.find()){
			chatList.add(new Chat(chatDoc));
		}
		return chatList;
	}
	
	/**
	 * Gets all chat messages for the given match
	 * @param match_id
	 * @return List<Chat>
	 */
	public List<Chat> getChatMessages(String match_id){
		List<Chat> chatList = new ArrayList<>();
		Document query = new Document("match_id", match_id);
		for(Document chatDoc : chat.find(query)){
			chatList.add(new Chat(chatDoc));
		}
		return chatList;
	}
}
