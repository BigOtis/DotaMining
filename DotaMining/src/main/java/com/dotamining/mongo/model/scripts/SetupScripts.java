package com.dotamining.mongo.model.scripts;

/**
 * Runs all of the setup scripts on the basic database
 * @author pgl57
 *
 */
public class SetupScripts {

	public static void main(String[] args){
		RemoveAttributes.main(new String[]{});
		CleanChat.main(new String[]{});
		ReplaceRadiantWin.main(new String[]{});
		AddWinnersToChat.main(new String[]{});
		AddWinnersToPlayers.main(new String[]{});
	}
	
}
