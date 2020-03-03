package com.company;
import java.util.Scanner;

public class Chatbot {
	
	Scanner in;
	String listen;
	String response;
	
	String custName;
	String[][] responseData;
	String[] farewellData = {"bye", "goodbye", "thanks", "thank you"};
	
	
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
		char lastChar = listen.charAt(listen.length()-1);
		if(lastChar == '.' || lastChar == '?') {
			listen = listen.substring(0, listen.length()-1);
		}
		
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
		//lowercase for the all the [x][0] values
		responseData = new String[30][2];
		responseData[0][0] = "hello";
		responseData[0][1] = "Hello, how can I help you?";
		responseData[1][0] = "hey";
		responseData[1][1] = "Hello, can I help you?";
		responseData[2][0] = "how are you";
		responseData[2][1] = "I'm doing great. What can I do for you?";
		responseData[3][0] = "how can i place an order";
		responseData[3][1] = "To order, create an account on our website first. You can then fill your basket with items and check out. If you have any special instructions/requests you can enter them there aswell.";
		responseData[4][0] = "how fast will it take my order to arrive";
		responseData[4][1] = "That depends. Did you use standard or express shipping?";
		responseData[5][0] = "i used standard";
		responseData[5][1] = "Your order will be delivered to you between 10-14 days from the day you order. If it has been over two weeks, check back with us.";
		responseData[6][0] = "i used express";
		responseData[6][1] = "Your order will be delivered to you between 2-5 days from the day you order. If it has been over a week, check back with us.";
		responseData[7][0] = "what can i do if i get the wrong order";
		responseData[7][1] = "take a picture of the items you received and send an email to freshfood@info.com with your order number and a representative will contact you able your options.";
		responseData[8][0] = "what are my options";
		responseData[8][1] = "We can pick up the non-perishable items from the order and issue a full refund. We can issue a partial refund if the issue is spoilt items. We could also offer a discount on your next order if you choose to keep the items.";
		responseData[9][0] = "i have issues with my order";
		responseData[9][1] = "I’m sorry to hear that. What is our order number?";
		responseData[10][0] = "ordernum4";
		responseData[10][1] = "I have located this order. What are your concerns?";
		responseData[11][0] = "my order has not arrived yet and it’s been over two weeks";
		responseData[11][1] = "I'm not seeing any issue on our side. To inquire about having the package send again please email us at freshfood@info.com";
		responseData[12][0] = "i would like a refund";
		responseData[12][1] = "I'm sorry to hear that. Please request a refund on the order under the order history tab.";
		responseData[13][0] = "when will my item be in-stock";
		responseData[13][1] = "Depending on the item, it can take 1-3 weeks to become available. Please check back frequently.";
		responseData[14][0] = "i would like to cancel my order";
		responseData[14][1] = "Please go to your Order History and cancel the order.";
	}
}