package com.dotamining.mongo.model.export;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
			String name = lineAry[2].replace(" ", "_").replace("-", "_").replace("'","");
			nameMap.put(Integer.parseInt(lineAry[1]), name);
			hero1.put("r_" + name, false);
			hero2.put("d_" + name, false);
		}
		reader.close();
		
		PrintWriter pw = new PrintWriter("MatchHeroOutcomes.csv");
		pw.println("match_id,duration,first_blood,radiantwin," + StringUtils.join(hero1.keySet(), ",") + "," + StringUtils.join(hero2.keySet(), ","));
		int i = 0;
		for(Document matchDoc : facade.matches.find()){
			if((i++) >= 10000){
				break;
			}
			Map<String, Boolean> hero1Curr = new LinkedHashMap<>(hero1);
			Map<String, Boolean> hero2Curr = new LinkedHashMap<>(hero2);
			Match match = new Match(matchDoc);
			for(Player player : match.getRadiantPlayers()){
				String name = nameMap.get(player.getHeroID());
				if(name != null){
					hero1Curr.put("r_" + name, true);
				}
				else{
					System.out.println("null name: " + player.getHeroID());
				}
			}
			for(Player player : match.getDirePlayers()){
				String name = nameMap.get(player.getHeroID());
				if(name != null){
					hero2Curr.put("d_" + name, true);
				}
				else{
					System.out.println("null name: " + player.getHeroID());
				}
			}
			
			String row = match.getMatchID() + "," + match.getDuration() + "," + match.getFirstBloodTime() + "," + match.radiantWin() + ",";
			for(String hero : hero1Curr.keySet()){
				row += hero1Curr.get(hero) + ",";
			}
			for(String hero : hero2Curr.keySet()){
				row += hero2Curr.get(hero) + ",";
			}
			
			pw.println(row.substring(0, row.length()-1));
		}
		
		pw.flush();
		pw.close();
	}
}
