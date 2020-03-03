package com.company;

public class Main {

    public static void main(String[] args) {
    	
    	Chatbot bot = new Chatbot();
    	boolean running = true;
    	
    	while(running) {
    		running = bot.listenAndRespond();
    	}
    	
    }
}
