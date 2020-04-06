NENMAN GOMWALK,
LUKE SPARG,
MARK BEHNKE,
ABDELMUIZZ YUSUF
# Chatbot
This is a chatbot application developed in java. The main method can be found at master/Chatbot/src/com/company/Main.java

Most of the code can be found in the Chatbot class which is initialized in the main method.

dialog intent objects for the chatbot can be found in the prompts.json file. Each intent object contains an array of possible user inputted prompts, and a string response to those prompts. 

DEPENDENCIES:
(Jar files can be found in the jars file)

A major dependency of this project is the json.simple java library. It must be installed before the code can be compiled and run. It was used in other for the chatbot to read the JSON file, parse the JSON file object and catch errors that partain to the handling of the JSON file.

Another dependancy is tdebbaty's java-string-similarity used for spell checking. 

Implemented Features:
-Simple GUI

-Spell Checking:
  When the user makes errors in their typing the bot is less likely to react in an erratic way
  CUSTOMER SERVICE: "Hi there! How may I help you?"
  CUSTOMER: "How od I place an orderr?"
  CUSTOMER SERVICE: "To order, create an account on our website first or just type 'i want to purchase something'. You can then fill                          your basket with items and check out. if you have any special instructions/requests you can enter them there                            aswell."


-Multiple messages when you the bot does not understand the input:
  Helps make the conversation sound more natural if the bot does not understand the input
  CUSTOMER: "What did the chicken say?"
  CUSTOMER SERVICE: "I'm sorry. I'm not sure what you are saying."
  CUSTOMER: "I'm talking about the chicken"
  CUSTOMER SERVICE: "I'm sorry, I do not understand. Do you mind rephrasing that?"

-New topic for bot:
  Added pizza delivery topic ontop of the gocery delivery topic to expand the range of conversations the bot can have.
  CUSTOMER SERVICE: "Hi there! How may I help you?"
  CUSTOMER: "pizza"
  CUSTOMER SERVICE: "Sure! What size pizza do you want?"
  CUSTOMER: "large"
  CUSTOMER SERVICE: "Got it. What toppings do you want?"


