package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;

public class ReplaceRadiantWin {

	public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		
		int count = 0;
		for(Document messageDoc : facade.matches.find()){
			if((count++) % 1000 == 0){
				System.out.println("Match: " + (count - 1));
			}
			String radiantWin = messageDoc.getString("radiant_win");
			Boolean win = Boolean.valueOf(radiantWin);
			messageDoc.remove("radiant_win");
			messageDoc.append("radiant_win", win);
			facade.matches.findOneAndReplace(
					new Document("_id", messageDoc.get("_id")), 
					messageDoc);
		}
		System.out.println("Match true/false updated");
	}
}
