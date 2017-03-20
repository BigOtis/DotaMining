package com.dotamining.mongo.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Match extends DotaObject{

	
	public Match(Document doc){
		super(doc);
	}
	
	public Integer getMatchID(){
		return getDoc().getInteger("match_id");
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
		List<Player> players = db().getPlayersForMatch(getMatchID());
		return players;
	}
	
	public List<Player> getRadiantPlayers(){
		List<Player> radiant = new ArrayList<>();
		for(Player player : getAllPlayers()){
			if(player.getPlayerSlot() < 5){
				radiant.add(player);
			}
		}
		return radiant;
	}
	
	public List<Player> getDirePlayers(){
		List<Player> dire = new ArrayList<>();
		for(Player player : getAllPlayers()){
			if(player.getPlayerSlot() >= 5){
				dire.add(player);
			}
		}
		return dire;
	}
}
