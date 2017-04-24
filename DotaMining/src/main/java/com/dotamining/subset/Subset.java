package com.dotamining.subset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * 
 * This class will create a subset dataset from the original dataset.
 * The subset data set will contain instances from match_id 0 to 4999
 * i.e. 5000 matches are being considered
 * 
 */

public class Subset {

    BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fw = null;
	String line;
	String splitBy = ",";
	
	public void createSubsetMatch(String path){
		
		try {
			br = new BufferedReader(new FileReader(path));
			
			fw = new FileWriter("/home/mmbohari/Desktop/Link to Spring 2017"
					+ "/Data Cleaning & Preparation/Term Project/Phase 3"
					+ "/Subset/Match.csv");
			bw = new BufferedWriter(fw);
			
			line = br.readLine();
			bw.write(line);
			bw.newLine();
			
			while(true){
				line = br.readLine();
				String [] array = line.split(splitBy);
				if(array[0].equals("5000"))
					break;
				bw.write(line);
				bw.newLine();
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.err.println("File not Found; Given path: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
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
	
	public void createSubsetPlayer(String path){
		
		try {
			br = new BufferedReader(new FileReader(path));
			
			fw = new FileWriter("/home/mmbohari/Desktop/Link to Spring 2017"
					+ "/Data Cleaning & Preparation/Term Project/Phase 3"
					+ "/Subset/Players.csv");
			bw = new BufferedWriter(fw);
			
			line = br.readLine();
			bw.write(line);
			bw.newLine();
			
			while(true){
				line = br.readLine();
				String [] array = line.split(splitBy);
				if(array[1].equals("5000"))
					break;
				bw.write(line);
				bw.newLine();
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.err.println("File not Found; Given path: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
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
	
	public void createSubsetChat(String path){
		
		try {
			br = new BufferedReader(new FileReader(path));
			
			fw = new FileWriter("/home/mmbohari/Desktop/Link to Spring 2017"
					+ "/Data Cleaning & Preparation/Term Project/Phase 3"
					+ "/Subset/Chat.csv");
			bw = new BufferedWriter(fw);
			
			line = br.readLine();
			bw.write(line);
			bw.newLine();
			
			while(true){
				line = br.readLine();
				String [] array = line.split(splitBy);
				if(array[1].equals("5000"))
					break;
				if( ( (array[array.length-1].equals("true")) || (array[array.length-1].equals("false")) ) ){
					bw.write(line);
					bw.newLine();
				}
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.err.println("File not Found; Given path: " + path);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			
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
	
	public static void main(String[] args) {
		Subset obj = new Subset();
		
		// Create a subset of Match.csv
		obj.createSubsetMatch("/home/mmbohari/Desktop/Link to Spring 2017"
				+ "/Data Cleaning & Preparation/Term Project/Phase 3"
				+ "/Processed Datase v1/Match.csv");
		
		// Create a subset of Player.csv
		obj.createSubsetPlayer("/home/mmbohari/Desktop/Link to Spring 2017"
				+ "/Data Cleaning & Preparation/Term Project/Phase 3"
				+ "/Processed Datase v1/Players.csv");
		
		// Create a subset of Chat.csv
		obj.createSubsetChat("/home/mmbohari/Desktop/Link to Spring 2017"
				+ "/Data Cleaning & Preparation/Term Project/Phase 3"
				+ "/Processed Datase v1/Chat.csv");
	}

}
