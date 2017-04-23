package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Match;
import com.dotamining.mongo.model.Player;

public class AddWinnersToPlayers {

	public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		Long matchCount = 0L;
		
		for(Document matchDoc : facade.matches.find()){
			if(matchCount % 1000 == 0){
				System.out.println("At " + matchCount + "th match");
			}
			Match match = new Match(matchDoc);
			for(Player player : match.getRadiantPlayers()){
				Document doc = player.getDoc();
				doc.append("won", match.radiantWin());
				facade.players.findOneAndReplace(new Document("_id", doc.get("_id")), doc);
			}
			for(Player player : match.getDirePlayers()){
				Document doc = player.getDoc();
				doc.append("won", !match.radiantWin());
				facade.players.findOneAndReplace(new Document("_id", doc.get("_id")), doc);
			}
			matchCount++;
		}
	}
}
