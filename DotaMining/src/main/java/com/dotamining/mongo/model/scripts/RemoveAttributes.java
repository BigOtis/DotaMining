package com.dotamining.mongo.model.scripts;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;

public class RemoveAttributes {

	public static void main(String[] args){
		
		DotaMongoFacade facade = DotaMongoFacade.getInstance();
		
		System.out.println("Removing attributes for matches...");
		String[] remove = new String[]{ 
			"barracks_status_dire",
			"barracks_status_radiant",
			"game_mode",
			"negative_votes",
			"positive_votes",
			"tower_status_dire",
			"tower_status_radiant"
		};
		for(Document matchDoc : facade.matches.find()){
			for(String attr : remove){
				matchDoc.remove(attr);
			}
			facade.matches.findOneAndReplace(
					new Document("_id", matchDoc.get("_id")), 
					matchDoc);
		}
		
		remove = new String[]{ 
				"gold_abandon",
				"gold_buyback",
				"gold_killing_couriers",
				"gold_destroying_structure",
				"gold_killing_couriers",
				"gold_killing_creeps",
				"gold_killing_heros",
				"gold_killing_roshan",
				"gold_other",
				"gold_sell",
				"item_0",
				"item_1",
				"item_2",
				"item_3",
				"item_4",
				"item_5",
				"tower_damage",
				"unit_order_buyback",
				"unit_order_attack_move",
				"unit_order_attack_target",
				"unit_order_cast_no_target",
				"unit_order_cast_position",
				"unit_order_cast_rune",
				"unit_order_cast_target_tree",
				"unit_order_cast_target",
				"unit_order_cast_toggle",
				"unit_order_cast_toggle_auto",
				"unit_order_continue",
				"unit_order_disassemble_item",
				"unit_order_drop_item",
				"unit_order_eject_item_from_stash",
				"unit_order_give_item",
				"unit_order_glyph",
				"unit_order_hold_position",
				"unit_order_move_item",
				"unit_order_move_to_direction",
				"unit_order_move_to_target",
				"unit_order_none",
				"unit_order_patrol",
				"unit_order_pickup_item",
				"unit_order_pickup_rune",
				"unit_order_ping_ability",
				"unit_order_radar",
				"unit_order_sell_item",
				"unit_order_set_item_combine_lock",
				"unit_order_stop",
				"unit_order_taunt",
				"unit_order_train_ability",
				"unit_order_vector_target_position",
				"xp_other",
				"xp_roshan"
				
		};
		
		System.out.println("Removing attributes for players...");
		int count = 0;
		for(Document playerDoc : facade.players.find()){
			if((count++) % 10000 == 0){
				System.out.println("At player " + count);
			}
			for(String attr : remove){
				playerDoc.remove(attr);
			}
			facade.players.findOneAndReplace(
					new Document("_id", playerDoc.get("_id")), 
					playerDoc);
		}
	}
}
