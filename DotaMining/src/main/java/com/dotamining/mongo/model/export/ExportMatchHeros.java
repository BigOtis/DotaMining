package com.dotamining.mongo.model.export;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Match;
import com.dotamining.mongo.model.Player;

public class ExportMatchHeros {

	public static void main(String[] args) throws IOException{
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();

		Map<Integer, List<Integer>> heroMap = new LinkedHashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader("hero_names.csv"));
		reader.readLine();
		String line;
		
		while((line = reader.readLine()) != null){
			
			String[] ary = line.split(",");
			Integer id = Integer.parseInt(ary[1]);
			List<Integer> vals = new ArrayList<>();
			for(int i = 3; i <= 13; i++){
				vals.add(Integer.parseInt(ary[i]));
			}
			heroMap.put(id, vals);
		}
		reader.close();
		
		PrintWriter pw = new PrintWriter("match_hero.csv");
		pw.println("match_id" + "," + "r_strength" + "," + "r_agility" + "," + "r_intelligence"  + "," 
					 + "r_carry" + "," + "r_disabler"  + "," + "r_initiator"  + "," + "r_jungler"
					 + "," + "r_durable"  + "," + "r_nuker"  + "," + "r_pusher"  + "," + "r_escape"
					 + "," + "d_strength" + "," + "d_agility" + "," + "d_intelligence"  + "," 
					 + "d_carry" + "," + "d_disabler"  + "," + "d_initiator"  + "," + "d_jungler"
					 + "," + "d_durable"  + "," + "d_nuker"  + "," + "d_pusher"  + "," + "d_escape,"
					 + "radiantWin");
		int count = 0;
		for(Document doc : facade.matches.find()){
			
			if(count >= 5000){
				continue;
			}
			
			List<Integer> radiantTeam = new ArrayList<>();
			List<Integer> direTeam = new ArrayList<>();
			for(int i = 0; i <= 10; i++){
				radiantTeam.add(0);
				direTeam.add(0);
			}
			
			Match match = new Match(doc);
			for(Player p : match.getRadiantPlayers()){
				addHero(radiantTeam, heroMap, p.getHeroID());
			}
			for(Player p : match.getDirePlayers()){
				addHero(direTeam, heroMap, p.getHeroID());
			}
			
			String result = match.getMatchID() + ",";
			result += StringUtils.join(radiantTeam, ",") + ",";
			result += StringUtils.join(direTeam, ",") + ",";
			result += match.radiantWin();
			
			pw.println(result);
			count++;
		}
		
		pw.flush();
		pw.close();
	}
	
	public static void addHero(List<Integer> team, Map<Integer, List<Integer>> map, Integer hero){
		
		List<Integer> stats = map.get(hero);
		if(stats != null){
			for(int i = 0; i < stats.size(); i++){
				int curr = team.get(i);
				int res = curr + stats.get(i);
				team.set(i, res);
			}
		}
	}
}
