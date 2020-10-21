package com.TwitterAPI.DAO;

import java.io.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ChangetoJSON {

	public String changetoJSON(JSONObject Obj[]) {
        try {
            
            // Writing to a file
        	FileWriter file = new FileWriter("E:\\Grootan\\TwitterApp\\src\\main\\webapp\\MostNumberOfRetweets.json");
            BufferedWriter bw = new BufferedWriter(file);
            for(int i=0;i<Obj.length;i++)
            bw.write(Obj[i].toJSONString());
            file.close();
            return "success";
 
        } catch (IOException e) {
            return e+"";
        }
 
	}

	public String changetoJSONOtherLanguage(JSONObject Obj[]) {
        try {
            
            // Writing to a file
        	FileWriter file = new FileWriter("E:\\Grootan\\TwitterApp\\src\\main\\webapp\\OtherLanguageTweets.json");
            BufferedWriter bw = new BufferedWriter(file);
            for(int i=0;i<Obj.length;i++)
            bw.write(Obj[i].toJSONString());
            file.close();
            return "success";
 
        } catch (IOException e) {
            return e+"";
        }
 
	}
}
