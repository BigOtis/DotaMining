package com.dotamining.mongo.model.export;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Match;
import com.dotamining.mongo.model.Player;

public class ExportMatchHeros {

	public static void main(String[] args) throws IOException{
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();

		Map<Integer, String> nameMap = new LinkedHashMap<>();
		Map<String, Boolean> hero1 = new LinkedHashMap<>();
		Map<String, Boolean> hero2 = new LinkedHashMap<>();
		
		BufferedReader reader = new BufferedReader(new FileReader("hero_names.csv"));
		reader.readLine();
		String line;
		while((line = reader.readLine()) != null){
			String[] lineAry = line.split(",");
			nameMap.put(Integer.parseInt(lineAry[1]), lineAry[2]);
			hero1.put("1 " + lineAry[2], false);
			hero2.put("1 " + lineAry[2], false);
		}
		
		for(Document matchDoc : facade.matches.find()){
			Map<String, Boolean> hero1Curr = new LinkedHashMap<>(hero1);
			Map<String, Boolean> hero2Curr = new LinkedHashMap<>(hero2);
			Match match = new Match(matchDoc);
			for(Player player : match.getRadiantPlayers()){
				hero1Curr.put("1 " + nameMap.get(player.getHeroID()), true);
			}
			for(Player player : match.getDirePlayers()){
				hero2Curr.put("2 " + nameMap.get(player.getHeroID()), true);
			}
		}
	}
}
