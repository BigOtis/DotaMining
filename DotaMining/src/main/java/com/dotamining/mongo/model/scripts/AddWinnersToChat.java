package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Chat;
import com.dotamining.mongo.model.Match;

public class AddWinnersToChat {

	public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		Long chatCount = 0L;
		
		for(Document chatDoc : facade.chat.find()){
			if(chatCount % 10000 == 0){
				System.out.println("At " + chatCount + "th chat message");
			}
			
			Chat chat = new Chat(chatDoc);
			if(chat.isWinner() == null){
				
				Match match = facade.getMatch(chat.getMatchID());
				boolean isWinner = (chat.isRadiant() && match.radiantWin()) || 
									(!chat.isRadiant() && !match.radiantWin());
				
				chat.getDoc().append("won", isWinner);
				facade.chat.findOneAndReplace(
						new Document("_id", chat.getDoc().get("_id")), 
						chat.getDoc());
			}
			chatCount++;
		}
	}
}
