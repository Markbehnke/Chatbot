

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Chatbot {
	Object obj;
	Scanner in;
	String listen;
	String response;

	String custName;
	String[] farewellData = { "bye", "goodbye"};

	public Chatbot() {
		in = new Scanner(System.in);
		System.out.println("CUSTOMER SERVICE: Hi, what's your name?");
		System.out.print("CUSTOMER: ");
		custName = in.nextLine();
		System.out.println("CUSTOMER SERVICE: How may I help you " + custName + "?");

	}

	public boolean listenAndRespond() throws FileNotFoundException, IOException, ParseException {//catch exceptions because a file is being read from
		// get user input
		System.out.print("CUSTOMER: ");
		listen = in.nextLine();
		listen = listen.toLowerCase();

		// removes punctuation at the end of a question as well as white spaces before and after
		char lastChar = listen.charAt(listen.length() - 1);
		if (lastChar == '.' || lastChar == '?') {
			listen = listen.substring(0, listen.length() - 1).trim();
		}
		
		//Load up JSON file 
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("prompts.json");
		boolean val = false;
		Object obj = jsonParser.parse(reader);
		
		//Retrieve JSON file 
		JSONArray responseData = (JSONArray) obj;
		int len = responseData.size();//counts the number of dialog prompts in the JSON file that have been parsed.
		
		for (Object x : responseData) {
			val = parseResponseData((JSONObject) x, listen, responseData.size());
			len--;
			//if true is returned and all the prompts in the JSON file have not been parsed,the user input matched a dialog prompt in the JSON file.
			//then the chatbot response has been printed.
			//Break the loop and wait for another user input. 
			if (val == true) {
				break;
			} else if (len == 0) {
				if (isFarewell()) {
					
					//if all the prompts have been parsed and the input is goodbye, return false and and the conversation. 
					System.out.println("CUSTOMER SERVICE: Goodbye "+ custName + "! Come back soon!");
					return false;
				} else {
					//if all the prompts have been parsed and the input is not goodbye, tell the user to rephrase their input.
					//break out of the loop to wait for another user input
					System.out.println("CUSTOMER SERVICE: I'm sorry, I do not understand. Do you mind rephrasing that?");
					break;
				}
			}
		}

		return true;
	}

	public static boolean parseResponseData(JSONObject prompt, String q, int len) {
		//Check response data (JSON file) to see if user input q matches any dialog prompts.
		//If true, print chatbot response from JSON file. If false return false. 
		String response = "";
		boolean val = false;
		JSONObject intent = (JSONObject) prompt.get("intent");
		JSONArray prompts = (JSONArray) intent.get("prompts");
		val = checkPrompts(prompts, q);

		if (val == true) {
			response += (String) intent.get("response");
			System.out.println("CUSTOMER SERVICE: "+response);
		}
		return val;

	}

	public static boolean checkPrompts(JSONArray promptsArr, String q) {
		//Check JSON array of prompts to see if user input q matches anyone of them, return true or false
		for (Object x : promptsArr) {
			String p = (String) x;
			if (p.equals(q)) {
				return true;
			}
		}
		return false;
	}

	boolean isFarewell() {
		// checks to see if this should end the conversation
		for (int i = 0; i < farewellData.length; i++) {
			if (listen.equals(farewellData[i])) {
				return true;
			}
		}
		return false;
	}

}
