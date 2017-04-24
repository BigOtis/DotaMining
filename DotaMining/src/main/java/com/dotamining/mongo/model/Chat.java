package com.dotamining.mongo.model;

import org.bson.Document;

/**
 * A single chat comment that occurred during a Dota 2 match
 * 
 * @author Phillip Lopez - pgl5711@rit.edu
 */
public class Chat extends DotaObject{

	public Chat(Document doc) {
		super(doc);
	}
	
	/**
	 * The text that was sent to chat
	 * @return
	 */
	public String getText(){
		return String.valueOf(getDoc().get("key"));
	}
	
	/**
	 * The match this chat belongs to
	 * @return
	 */
	public Integer getMatchID(){
		return getDoc().getInteger("match_id");
	}

	/**
	 * The players name who wrote the text
	 * @return
	 */
	public String getPlayerName(){
		return getDoc().getString("unit");
	}
	
	/**
	 * The time during the match the message was sent
	 * @return
	 */
	public Integer timeSaid(){
		return getDoc().getInteger("time");
	}
	
	/**
	 * The player number assigned to the player during the match
	 * @return
	 */
	public Integer getPlayerNum(){
		return getDoc().getInteger("slot");
	}
	
	public Double getTime(){
		return getDoc().getDouble("time");
	}
	
	public Integer getTimeInteger(){
		Object time = getDoc().get("time");
		if(time instanceof Integer){
			return (Integer) time;
		}
		return null;
	}
	
	/**
	 * Was the player on the Radiant team? 
	 * If not, then they were dire
	 * @return
	 */
	public Boolean isRadiant(){
		return getPlayerNum() < 5;
	}
	
	public Boolean isWinner(){
		return getDoc().getBoolean("won");
	}
}
