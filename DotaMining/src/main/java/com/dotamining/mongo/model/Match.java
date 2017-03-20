package com.dotamining.mongo.model;

import java.util.List;

import org.bson.Document;

public class Match extends DotaObject{

	private List<Player> players;
	
	public Match(Document doc){
		super(doc);
		players = db().getPlayersForMatch(getMatchID());
	}
	
	public String getMatchID(){
		return getDoc().getString("match_id");
	}
	
	public Boolean radiantWin(){
		return getDoc().getBoolean("radiant_win");
	}
	
	public Integer getDuration(){
		return getDoc().getInteger("duration");	
	}
	
	public Integer getFirstBloodTime(){
		return getDoc().getInteger("first_blood_time");
	}
	
	public List<Player> getAllPlayers(){
		return players;
	}
}
