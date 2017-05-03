package com.dotamining.subset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class is used to create a model.csv files that feteches information from
 * players_ratingsNEW.csv and adds it in the model.csv fiel
 * 
 * @author mmbohari
 *
 */
public class Model {

	// file path of both files
	String playersPath, player_ratingsNEWPath;
	String splitBy = ",";
	// this map is used to store a player's ac id & no. of matches that
	// he participated in
	HashMap<Integer, Integer> map;
	HashMap<Integer, Integer> total_win;
	HashMap<Integer, Integer> total__matches;
	HashMap<Integer, Double> trueskill_mu;
	HashMap<Integer, Double> trueskill_sigma;

	BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fw = null;

	public Model() {
		playersPath = "/home/mmbohari/Desktop/Link to Spring 2017/Data"
				+ " Cleaning & Preparation/Term Project/V3 Cleaned " + "Subset Data Set/players.csv";
		player_ratingsNEWPath = "/home/mmbohari/Desktop/Link to Spring 2017/Data"
				+ " Cleaning & Preparation/Term Project/V3 Cleaned " + "Subset Data Set/player_ratingsNEW.csv";
		map = new HashMap<>();
		total_win = new HashMap<>();
		total__matches = new HashMap<>();
		trueskill_mu = new HashMap<>();
		trueskill_sigma = new HashMap<>();
	}
	

	public static void main(String[] args) {
		Model obj = new Model();
		obj.createMap();
		obj.createModel();

	}

	public void createMap() {

		String line;
		int countUnq = 0;

		try {
			br = new BufferedReader(new FileReader(player_ratingsNEWPath));
			// read the header line of the file
			line = br.readLine();
			//System.out.println(line);

			while ((line = br.readLine()) != null) {
				String[] array = line.split(",");
				if (!map.containsKey(Integer.parseInt(array[0]))) {
					map.put(Integer.parseInt(array[0]), 1);
					total_win.put(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
					total__matches.put(Integer.parseInt(array[0]), Integer.parseInt(array[2]));
					trueskill_mu.put(Integer.parseInt(array[0]), Double.parseDouble(array[3]));
					trueskill_sigma.put(Integer.parseInt(array[0]), Double.parseDouble(array[4]));
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

	public void createModel() {
		String line;
		int countUnq2 = 0;

		try {
			br = new BufferedReader(new FileReader(playersPath));
			fw = new FileWriter("/home/mmbohari/Desktop/Link to Spring 2017/Data"
					+ " Cleaning & Preparation/Term Project/V3 Cleaned " + "Subset Data Set/model.csv");
			bw = new BufferedWriter(fw);
			// read the header line of the file
			line = br.readLine();
			System.out.println(line);
			line += ",total_wins,total_matches,trueskill_mu,trueskill_sigma";
			 bw.write(line);
			 bw.newLine();
			// System.out.println(line);
			//
			 while(br.readLine() != null){
			 line = br.readLine();
			 String [] array = line.split(",");
			 if(map.containsKey(Integer.parseInt(array[1]))){
			 line += "," + total_win.get(Integer.parseInt(array[1])) +"," +
					 total__matches.get(Integer.parseInt(array[1])) + "," +
					 trueskill_mu.get(Integer.parseInt(array[1])) + "," +
					 trueskill_sigma.get(Integer.parseInt(array[1]));
			 bw.write(line);
			 bw.newLine();
			 countUnq2++;
			 } else {
				 line += ",0,0,0,0";
				 bw.write(line);
				 bw.newLine();
			 }
			
			 }
			System.out.println(countUnq2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

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
