package cs721.DotaMining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.dotamining.mongo.DotaMongoFacade;
import com.dotamining.mongo.model.Chat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests getting some simple statistics about the Chat collection
 */
public class TextTimeTest extends TestCase
{
	
	public static int NUM_TOP_WORDS = 10;
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TextTimeTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TextTimeTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testMongoFacade(){
    	
    	// Setup the MongoFacade which connects to the DB
    	DotaMongoFacade facade = DotaMongoFacade.getInstance();
    	
    	Map<String, Integer> loserCount = new HashMap<>();
    	Map<String, Integer> winnerCount = new HashMap<>();
    	Long wordCount = 0L;
    	
    	// Loop through every chat message
    	for(Document chatDoc : facade.chat.find()){
    		
    		Chat message = new Chat(chatDoc);
    		Map<String, Integer> map;
    		String[] words = message.getText().split(" ");
    		
    		// Add the words from this message to the winner/loser wordmap
    		if(message.isWinner()){
    			map = winnerCount;
    		}
    		else{
    			map = loserCount;
    		}
    		for(String word : words){
    			if(word.length() > 5){
	    			wordCount++;
	    			if(wordCount % 100000 == 0){
	    				System.out.println("At " + wordCount + "th word");
	    			}
	    			
	    			if(map.containsKey(word)){
	    				map.put(word, map.get(word) + 1);
	    			}
	    			else{
	    				map.put(word, 1);
	    			}
    			}
    		}
    	}	
    	
    	// Sort the words based on the number of times they appear
    	List<String> winnerWords = new ArrayList<>(winnerCount.keySet());
    	List<String> loserWords = new ArrayList<>(loserCount.keySet());
    	
    	CompareWordCount radiantCompare = new CompareWordCount(winnerCount);
    	CompareWordCount direCompare = new CompareWordCount(loserCount);
    	
    	Collections.sort(winnerWords, radiantCompare);
    	Collections.sort(loserWords, direCompare);
    	
    	// output the top words
		System.out.println("Top Winner Words: ");
    	for(int i = 0; i < NUM_TOP_WORDS; i++){
    		String word = winnerWords.get(i);
    		System.out.println(word + "\t - " + winnerCount.get(word));
    	}
    	
		System.out.println("Top Loser Words: ");
    	for(int i = 0; i < NUM_TOP_WORDS; i++){
    		String word = loserWords.get(i);
    		System.out.println(word + "\t - " + loserCount.get(word));    		
    	}
    }
    
    /**
     * Comparator for wordcount maps
     */
    public class CompareWordCount implements Comparator<String>{
    	
    	private Map<String, Integer> map;
    	
    	public CompareWordCount(Map<String, Integer> map) {
    		this.map = map;
		}
    	
		@Override
		public int compare(String w1, String w2) {
			return map.get(w2).compareTo(map.get(w1));
		}
    }
}
