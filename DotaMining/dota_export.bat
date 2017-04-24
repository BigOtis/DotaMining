mongoexport --db DotaDB --collection Match --out Match.csv --type=csv --fields match_id,duration,first_blood_time,cluster,radiant_win,start_time
mongoexport --db DotaDB --collection Chat --out Chat.csv --type=csv --fields key,march_id,slot,time,unit,won
mongoexport --db DotaDB --collection Players --out Players.csv --type=csv --fields account_id,match_id,assists,deaths,denies,gold_per_min,gold_spent,hero_damage,hero_healing,hero_id,kills,last_hits,leaver_status,level,player_slot,stuns,unit_order_buyback,unit_order_move_to_position,unit_order_purchase_item,won,xp_per_min

