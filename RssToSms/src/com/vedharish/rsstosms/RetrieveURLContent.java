package com.vedharish.rsstosms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.PendingIntent;
import android.os.AsyncTask;

public class RetrieveURLContent extends AsyncTask<Object, Void, Void> {
	@Override
	protected Void doInBackground(Object... inputStringArray) {
		String stringMessage = (String) inputStringArray[0];
		String stringPhoneNumber = (String) inputStringArray[1];
		
		SendSMS sendSMSContent = new SendSMS();
		sendSMSContent.execute(stringMessage, stringPhoneNumber);
		
		try{			
//			URL tempUrl = new URL("http://api.androidhive.info/music/music.xml");
//			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
//			SAXParser saxParser = saxFactory.newSAXParser();
//			XMLReader xmlReader = saxParser.getXMLReader();
//			RssHandler rssHandler = new RssHandler();
//			xmlReader.setContentHandler(rssHandler);
//			xmlReader.parse(new InputSource(tempUrl.openStream()));
//			rssHandler.getData();
			
//			HttpClient httpClient = new DefaultHttpClient();
//			HttpGet httpGetRequest = new HttpGet("http://api.androidhive.info/music/music.xml");
//			HttpResponse httpResponse = httpClient.execute(httpGetRequest);
//	    	InputStream tempInputStream = httpResponse.getEntity().getContent();
//	    	
//	    	sendSMSContent.execute(inputStringArray[1], convertStreamToString(tempInputStream));
		}catch(Exception e){
		}
		return null;
	}

	private String convertStreamToString(InputStream tempInputStream) {
		StringBuilder returnStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(tempInputStream));
		String readLine = "";
		try{
			while((readLine = bufferedReader.readLine()) != null){
				returnStringBuilder.append(readLine);
			}
		}catch(Exception e){
			
		}
		return returnStringBuilder.toString();
	}
}