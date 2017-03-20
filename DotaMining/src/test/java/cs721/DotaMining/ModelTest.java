package cs721.DotaMining;

import java.util.List;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Match;
import com.dotamining.mongo.model.Player;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ModelTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ModelTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ModelTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testMongoFacade(){
    	
    	// Setup the MongoFacade which connects to the DB
    	DotaMongoFacade facade = DotaMongoFacade.getInstance();
    	
    	// Load in all 50,000 matches from our data set
    	List<Match> matches = facade.getMatches();
    	
    	// Keep track of gold
    	long direGold = 0;
       	long radiantGold = 0;
       	int matchCount = 0;
       
       	// Loop through every match and player
       	// and count the gold collected per player
       	for(Match match : matches){
       		if((matchCount % 1000) == 0){
       			System.out.println("Reached match #: " + matchCount);
       		}
       		for(Player player : match.getRadiantPlayers()){
       			radiantGold += player.getGoldPerMin();
       		}
       		for(Player player : match.getDirePlayers()){
       			direGold += player.getGoldPerMin();
       		}  
       		matchCount++;
       	} 
       
       	// Print out the average gold for each side across all matches
       	System.out.println("Average dire gold: " + direGold/(matchCount*10));
       	System.out.println("Average radiant gold: " + radiantGold/(matchCount*10));
       	System.out.println("Over " + matchCount + " matches");
    }
}
