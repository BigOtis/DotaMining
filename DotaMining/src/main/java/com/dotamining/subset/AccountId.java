package com.dotamining.subset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class is used to extract players with account_id
 * that occur in players.csv file & player_ratings.csv.
 * 
 * We have considered only first 5 k matches, thus we will
 * have fewer players (as compared to player_ratings.csv - as
 * it has a comprehensive list of players)
 * 
 * NOTE: replace variables playersPath & player_ratingsPath
 * accordingly
 * 
 * @author mmbohari
 *
 */
public class AccountId {

	// file path of both files
	String playersPath, player_ratingsPath;
	String splitBy = ",";
	// this map is used to store a player's ac id & no. of matches that
	// he participated in
	HashMap<Integer, Integer> map;
	
    BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	
	public AccountId(){
		playersPath = "/home/mmbohari/Desktop/Link to Spring 2017/Data"
				+ " Cleaning & Preparation/Term Project/V3 Cleaned "
				+ "Subset Data Set/players.csv";
		player_ratingsPath = "/home/mmbohari/Desktop/Link to Spring 2017/Data"
				+ " Cleaning & Preparation/Term Project/V3 Cleaned "
				+ "Subset Data Set/player_ratings.csv";
		map = new HashMap<>();
	}
	
	public static void main(String[] args) {
		AccountId obj = new AccountId();
		obj.players();
		obj.player_ratings();

	}
	
	/*
	 * This method is used to store all account_id and the number of times
	 * player with their respective account_id participated in matches
	 */
	public void players(){
		String line;
		int countUnq = 0;
		
		try {
			br = new BufferedReader(new FileReader(playersPath));
			// read the header line of the file
			line = br.readLine();
			System.out.println(line);

			while(br.readLine() != null){
				line = br.readLine();
				String [] array = line.split(",");
				if(!map.containsKey(Integer.parseInt(array[1]))){
					map.put(Integer.parseInt(array[1]), 1);
					countUnq++;
				}
			}
			System.out.println(countUnq);
			
		} catch (FileNotFoundException e) {
			System.err.println("Error (Method: players)!!! " + e);
		} catch (IOException e) {
			System.err.println("File not found (Method: players)");
		}
	}
	
	public void player_ratings(){
		String line;
		int countUnq2 = 0;
		
		try {
			br = new BufferedReader(new FileReader(player_ratingsPath));
			fw = new FileWriter("/home/mmbohari/Desktop/Link to Spring 2017/Data"
				+ " Cleaning & Preparation/Term Project/V3 Cleaned "
				+ "Subset Data Set/player_ratingsNEW.csv");
			bw = new BufferedWriter(fw);
			// read the header line of the file
			line = br.readLine();
			bw.write(line);
			bw.newLine();
			System.out.println(line);
			
			while(br.readLine() != null){
				line = br.readLine();
				String [] array = line.split(",");
				if(map.containsKey(Integer.parseInt(array[0]))){
					bw.write(line);
					bw.newLine();
					countUnq2++;
				}
					
			}
			System.out.println(countUnq2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			// close buffer reader
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            // close buffer writer and file writer
			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
	}

}
