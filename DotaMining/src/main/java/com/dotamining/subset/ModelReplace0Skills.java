package com.dotamining.subset;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is used to replace instances that have a zero 
 * value in the attributes "trueskill_mu" & "trueskill_sigma"
 * 
 * @author mmbohari
 *
 */

// Results of trueskill_mu & trueskill_sigma
/*
 * TR_MU: 187087; C 7103
 * TR_Sig: 38792; C 7103
 */
public class ModelReplace0Skills {

	String modelPath;
	BufferedReader br = null;
	BufferedWriter bw = null;
	FileWriter fw = null;
	
	double trueSkill_Mu, trueSkill_Sigma;
	int countTS_MU, countTS_Sig;
	
	public ModelReplace0Skills(){
		modelPath = "/home/mmbohari/Desktop/Link to Spring 2017"
				+ "/Data Cleaning & Preparation/Term Project"
				+ "/V3 Cleaned Subset Data Set/model.csv";
	}
	
	public static void main(String[] args) {
		ModelReplace0Skills obj = new ModelReplace0Skills();
		obj.replaceZero();
		
	}
	
	public void replaceZero(){
		String line;
		String[] array;
		
		try {
			br = new BufferedReader(new FileReader(modelPath));
			fw = new FileWriter("/home/mmbohari/Desktop/"
					+ "Link to Spring 2017/Data Cleaning & Preparation"
					+ "/Term Project/V3 Cleaned Subset Data Set/modelNew.csv");
			bw = new BufferedWriter(fw);
			
			// read the header line of the file
			line = br.readLine();
			
			while( (line = br.readLine()) != null ){
				array = line.split(",");
				
				if(Double.parseDouble(array[18]) != 0 
						|| (Double.parseDouble(array[19])) !=0){
					trueSkill_Mu += Double.parseDouble(array[18]);
					countTS_MU++;
					
					trueSkill_Sigma += Double.parseDouble(array[19]);
					countTS_Sig++;
				}
				
			}
			
			System.out.println("TR_MU: " + trueSkill_Mu + "; C " + countTS_MU);
			System.out.println("TR_Sig: " + trueSkill_Sigma + "; C " + countTS_Sig);
			
		} catch (FileNotFoundException e) {
			System.err.println("Error " + e);
		} catch (IOException e) {
			System.err.println("Error " + e);
		}
		
	}

}
