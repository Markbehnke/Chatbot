package com.company;
import java.util.Scanner;

public class Chatbot {
	
	Scanner in;
	String listen;
	String response;
	
	String custName;
	String[][] responseData;
	String[] farewellData = {"bye", "goodbye"};
	
	
	public Chatbot() {
		loadData();
		in = new Scanner(System.in);
		System.out.println("Hi, what's your name?");
		custName = in.nextLine();
		System.out.println("How can I help you " + custName + "?");
	}
	public boolean listenAndRespond() {
		listen = in.nextLine();
		listen = listen.toLowerCase();
		
		if(isFarewell()) {
			//If they say bye or goodbye etc.
			System.out.println("Goodbye.");
			return false;
		} else {
			response = "I'm sorry, I do not understand. How else may I help you?";
			getMatch();
		}
		System.out.println(response);
		return true;
	}
	
	boolean isFarewell() {
		for(int i = 0; i < farewellData.length; i++) {
			if(listen.equals(farewellData[i])) {
				return true;
			}
		}
		return false;
	}
	
	void getMatch() {
		for(int i = 0; i < responseData.length; i++) {
			if(listen.equals(responseData[i][0])) {
				response = responseData[i][1];
			}
		}
	}
	
	void loadData() {
		//Only use lowercase
	}
	
}