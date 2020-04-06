

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Chatbot implements ActionListener {
	Object obj;
	Scanner in;
	String listen;
	static String response;

	String custName;
	String[] farewellData = { "bye", "goodbye"};
	
	String userInput;
	JTextField userInputTF;
	JTextArea mainTA;
	JButton replyButton;
	JFrame frame;
	JPanel replyPanel;
	JPanel mainPanel;
	JLabel enterTextLabel;

	public Chatbot() {
		frame = new JFrame("Chatbot");//create UI frame
		initialiseComponents();//initialise UI components.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closing the fram, ends the application
		frame.setSize(700, 500);//set the frame size
		
		mainTA.setText("CUSTOMER SERVICE: Hi there! How may I help you?\n");//set initial bot output
		
		frame.setVisible(true);//launch frame

	}
	
	public void initialiseComponents() {//setup GUI components
		replyPanel = new JPanel(); 
		enterTextLabel = new JLabel("Enter Text");
		userInputTF = new JTextField(40); 
		replyButton = new JButton("Reply");
		
		//add button, text field and label to the bottom panel
		replyPanel.add(enterTextLabel);
		replyPanel.add(userInputTF);
		replyPanel.add(replyButton);
		
		replyButton.addActionListener(this);
		
		// Text Area at the Center
		mainTA = new JTextArea(25, 50);
		JPanel mainPanel = new JPanel();
		JScrollPane scroll = new JScrollPane(mainTA, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainPanel.add(scroll);

		// Adding Components to the frame.
		frame.getContentPane().add(BorderLayout.SOUTH, replyPanel);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		userInput = userInputTF.getText();
		append(mainTA, "CUSTOMER: " + userInput + "\n");
		userInputTF.setText("");
		try {
			listenAndRespond(userInput);//listen for user input and get bot response
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		append(mainTA, "CUSTOMER SERVICE: " + response + "\n");//print bot response in GUI
	}

	public static void append(JTextArea area, String newText) {
		area.setText(area.getText() + newText);
	}

	public void listenAndRespond(String listen) throws FileNotFoundException, IOException, ParseException {//catch exceptions because a file is being read from
		// get user input
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
				if (isFarewell(listen)) {
					//if all the prompts have been parsed and the input is goodbye
					response = "Goodbye! Come back soon!";
				
				} else {
					//if all the prompts have been parsed and the input is not goodbye, tell the user to rephrase their input.
					//break out of the loop to wait for another user input
					response = "I'm sorry, I do not understand. Do you mind rephrasing that?";
					break;
				}
			}
		}
	}

	public static boolean parseResponseData(JSONObject prompt, String q, int len) {
		//Check response data (JSON file) to see if user input q matches any dialog prompts.
		//If true, print chatbot response from JSON file. 

		boolean val = false;
		JSONObject intent = (JSONObject) prompt.get("intent");
		JSONArray prompts = (JSONArray) intent.get("prompts");
		val = checkPrompts(prompts, q);

		if (val == true) {
			response = (String) intent.get("response");
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

	boolean isFarewell(String listen) {
		// checks to see if this should end the conversation
		for (int i = 0; i < farewellData.length; i++) {
			if (listen.equals(farewellData[i])) {
				return true;
			}
		}
		return false;
	}

}
