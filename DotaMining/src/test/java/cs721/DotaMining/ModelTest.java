package cs721.DotaMining;

import java.util.List;

import com.dotamining.mongo.MongoFacade;
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
    public void testMongoFacade()
    {
       MongoFacade facade = MongoFacade.getInstance();
       List<Match> matches = facade.getMatches();
       long direGold = 0;
       long radiantGold = 0;
       int matchCount = 0;
       
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
       
       System.out.println("Average dire gold: " + direGold/(matchCount*10));
       System.out.println("Average radiant gold: " + radiantGold/(matchCount*10));
       System.out.println("Over " + matchCount + " matches");
    }
}
