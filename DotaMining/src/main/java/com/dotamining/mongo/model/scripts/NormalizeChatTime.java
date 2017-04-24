package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Chat;
import com.dotamining.mongo.model.Match;

public class NormalizeChatTime {

	public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		int count = 0;
		System.out.println("Normalizing chat doc times");
		for(Document chatDoc : facade.chat.find()){
			
			if(count++ % 100000 == 0){
				System.out.println("At chat " + (count-1));
			}
			
			Chat chat = new Chat(chatDoc);
			
			Integer time = chat.getTimeInteger();
			if(time != null){
				Double chat_time = Double.valueOf(time);
	
				Match match = facade.getMatch(chat.getMatchID());
				Double match_duration = Double.valueOf(match.getDuration());
				
				Double chat_normal = chat_time/match_duration;
				chatDoc.replace("time", chat_normal);
				facade.chat.replaceOne(new Document("_id", chatDoc.get("_id")), chatDoc);
			}
		}
	}
	
}
