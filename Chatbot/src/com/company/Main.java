
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

public class Main {
	//added the "throws exceptions" because we're reading from a JSON file 
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    	
    	Chatbot bot = new Chatbot();
    	boolean running = true;
    	
    	while(running) {
    		running = bot.listenAndRespond();
    	}
    	
    }
}
