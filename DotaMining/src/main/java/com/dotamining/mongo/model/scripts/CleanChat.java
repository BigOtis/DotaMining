package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Chat;

public class CleanChat {

public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		Long chatCount = 0L;
		int deleted = 0;
		int updated = 0;
		for(Document chatDoc : facade.chat.find()){
			if(chatCount % 10000 == 0){
				System.out.println("At " + chatCount + "th chat message");
			}
			
			Chat chat = new Chat(chatDoc);
			String text = chat.getText();
			text = text.replaceAll("[^A-Za-z0-9]+", " ").replaceAll("\\s+", " ");
			text = text.toLowerCase();
			if(text.replaceAll(" ", "").length() <= 1){
				facade.chat.deleteOne(new Document("_id", chatDoc.get("_id")));
				deleted++;
			}
			else{
				chatDoc.remove("key");
				chatDoc.append("key", text);
				facade.chat.findOneAndReplace(new Document("_id", chatDoc.get("_id")), chatDoc);
				updated++;
			}
			
			chatCount++;
		}
		
		System.out.println("Deleted " + deleted);
		System.out.println("Updated " + updated);
	}
	
}
