package com.TwitterAPI.Controllers;
import org.apache.tika.language.LanguageIdentifier;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.TwitterAPI.DAO.ChangetoJSON;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class GetInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
			ConfigurationBuilder conf=new ConfigurationBuilder();
			conf.setDebugEnabled(true)
							.setOAuthConsumerKey("")
							.setOAuthConsumerSecret("")
							.setOAuthAccessToken("")
							.setOAuthAccessTokenSecret("");
							
			
			Twitter twitter = TwitterFactory.getSingleton();
		    Query query = new Query("corona");
		    QueryResult result = null;
		    TreeMap<Integer,Status> map=new TreeMap<Integer, Status>(Collections.reverseOrder());
			try {
				do {
	                result = twitter.search(query);
	                List<Status> tweets = result.getTweets();
	                for (Status tweet : tweets) {
	                	
	                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
	                    map.put(tweet.getRetweetCount(),tweet);
	                }	                
	            } while ((query = result.nextQuery()) != null);
	            System.exit(0);
	            
	            puttoJSON(map);
                RequestDispatcher rd=request.getRequestDispatcher("Download.html");
                rd.forward(request, response);
	        } 
			catch (TwitterException te) {
	            te.printStackTrace();
	            System.out.println("Failed to search tweets: " + te.getMessage());
			}
			
		}
	
		static void puttoJSON(TreeMap<Integer,Status> m)
		{
			int topRetweets=0;
			JSONObject Obj[] = new JSONObject[10] ;
			JSONObject ObjOtherLanguage[] = new JSONObject[10] ;
			for(Integer i:m.keySet())
			{
				if(topRetweets==10)
					break;
				if(checkLanguage(m.get(i)))
				{
					ObjOtherLanguage[count].put("ScreenName",m.get(i).getUser().getScreenName());
					ObjOtherLanguage[count].put("Creation_Time", m.get(i).getCreatedAt());
		        	ObjOtherLanguage[count].put("HashTags", m.get(i).getHashtagEntities());
		        	count++;
				}
				
		        Obj[topRetweets].put("Name", m.get(i).getUser().getScreenName());
		        Obj[topRetweets].put("Retweetcount",  m.get(i).getRetweetCount());
		        topRetweets++;
			}
			ChangetoJSON change=new ChangetoJSON();
	        change.changetoJSON(Obj);
	        change.changetoJSONOtherLanguage(Obj);
		}
		static int count=0;
		static boolean checkLanguage(Status status)
		{
			LanguageIdentifier identifier = new LanguageIdentifier(status.getText());  
	        String language = identifier.getLanguage();  
	        if(language.equals("en"))
	        return true;
	        
	        return false;
			
		}
}


