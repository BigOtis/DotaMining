package com.dotamining.mongo.model;

import org.bson.Document;

public class Player extends DotaObject{

	public Player(Document doc) {
		super(doc);
	}
	
	public Integer getPlayerSlot(){
		return getDoc().getInteger("player_slot");
	}
	
	public Integer getMatchID(){
		return getDoc().getInteger("match_id");
	}
	
	public Integer getAccountID(){
		return getDoc().getInteger("account_id");
	}
	
	public Integer getAssists(){
		return getDoc().getInteger("assists");
	}
	
	public Integer getKills(){
		return getDoc().getInteger("kills");
	}
	
	public Integer getLastHits(){
		return getDoc().getInteger("last_hits");
	}
	
	public Integer getDeaths(){
		return getDoc().getInteger("assists");
	}
	
	public Integer getGoldPerMin(){
		return getDoc().getInteger("gold_per_min");
	}
	
	public Integer getGoldSpent(){
		return getDoc().getInteger("gold_spent");
	}
	
	public Integer getHeroDamage(){
		return getDoc().getInteger("hero_damage");
	}
	
	public Integer getHeroID(){
		return getDoc().getInteger("hero_id");
	}
	
	/**
	 * 0   - Played Full Game
	 * > 0 - Abandoned
	 * @return
	 */
	public Integer getLeaverStatus(){
		return getDoc().getInteger("leave_status");
	}
	
	public Integer getLevel(){
		return getDoc().getInteger("level");
	}
	
	public Integer getStuns(){
		return getDoc().getInteger("stuns");
	}
	
	public Integer xpPerMin(){
		return getDoc().getInteger("xp_per_min");
	}
	
	
}
