/*******************************************************************************************************************
  * Veterinary Medication Manager
  * Author: Jocelyn Smith
  ******************************************************************************************************************/
import java.util.Scanner;
public class Vet_Med_Manager {
 
  static Scanner keyboard = new Scanner(System.in);
 
  public static void main(String[] args)  {
    //Use global constant to declare size of array
    final int MED_NAME = 14;
    final int MED_DESCRIPTION = 2;
    String[][] MEDICINE_NAMES_AND_DESCRIPTIONS = new String[MED_NAME][MED_DESCRIPTION];
    MEDICINE_NAMES_AND_DESCRIPTIONS[0][0] = "Abilify";
    MEDICINE_NAMES_AND_DESCRIPTIONS[0][1] = "an antipsychotic drug";
    MEDICINE_NAMES_AND_DESCRIPTIONS[1][0] = "Ambien";
    MEDICINE_NAMES_AND_DESCRIPTIONS[1][1] = "to treat insomnia";
    MEDICINE_NAMES_AND_DESCRIPTIONS[2][0] = "Amoxi";
    MEDICINE_NAMES_AND_DESCRIPTIONS[2][1] = "an antibiotic";
    MEDICINE_NAMES_AND_DESCRIPTIONS[3][0] = "Aspirin";
    MEDICINE_NAMES_AND_DESCRIPTIONS[3][1] = "a pain reliever";
    MEDICINE_NAMES_AND_DESCRIPTIONS[4][0] = "Crestor";
    MEDICINE_NAMES_AND_DESCRIPTIONS[4][1] = "a cholesterol-lowering statin drug";
    MEDICINE_NAMES_AND_DESCRIPTIONS[5][0] = "PlaceboTec";
    MEDICINE_NAMES_AND_DESCRIPTIONS[5][1] = "to treat hypochondriacs";
    MEDICINE_NAMES_AND_DESCRIPTIONS[6][0] = "Xanax";
    MEDICINE_NAMES_AND_DESCRIPTIONS[6][1] = "to treat anxiety disorders";
    MEDICINE_NAMES_AND_DESCRIPTIONS[7][0] = "Norvasc";
    MEDICINE_NAMES_AND_DESCRIPTIONS[7][1] = "a high blood pressure drug";
    MEDICINE_NAMES_AND_DESCRIPTIONS[8][0] = "Penicillin";
    MEDICINE_NAMES_AND_DESCRIPTIONS[8][1] = "an antibiotic";
    MEDICINE_NAMES_AND_DESCRIPTIONS[9][0] = "Plavix";
    MEDICINE_NAMES_AND_DESCRIPTIONS[9][1] = "a blood thinner";
    MEDICINE_NAMES_AND_DESCRIPTIONS[10][0] = "Prilosec";
    MEDICINE_NAMES_AND_DESCRIPTIONS[10][1] = "to treat reflux disease";
    MEDICINE_NAMES_AND_DESCRIPTIONS[11][0] = "Wellbe";
    MEDICINE_NAMES_AND_DESCRIPTIONS[11][1] = "an antidepressant";
    MEDICINE_NAMES_AND_DESCRIPTIONS[12][0] = "Nexim";
    MEDICINE_NAMES_AND_DESCRIPTIONS[12][1] = "an antacid drug";
    MEDICINE_NAMES_AND_DESCRIPTIONS[13][0] = "Zoloft";
    MEDICINE_NAMES_AND_DESCRIPTIONS[13][1] = "an antidepressant";
   
    //Declare variables to check user input against choices
    final int LOG_IN = 1;
    final int DISPLAY_MEDS = 2;
    final int PRESCRIBE_MEDS = 3;
    final int RESET_MEDS = 4;
    final int EXIT = 5;
    int userInput = -1; //set variable to number that won't interrupt loop before input is received
    int prescribeMedicine = -1;
    //Declare boolean array to hold medicines that are prescribed, set array amount to max number of medications available
    boolean [] medicinePrescribed = new boolean [MED_NAME];
   
    //Display menu options to user, and prompt for sign-in before moving on
   
    displayLogIn(LOG_IN, DISPLAY_MEDS, PRESCRIBE_MEDS, RESET_MEDS, EXIT);
    userInput = getInteger("Please enter a menu option.");
    while (userInput != LOG_IN) {
      System.out.println("Invalid entry. You must sign in first, doctor.");
      userInput = getInteger("Please enter a menu option.");
    }
   
    String doctorsName = getString ("What is your name, doctor?");
   
    //Display menu options for doctor to select        
    do {
      displayMenu(doctorsName, LOG_IN, DISPLAY_MEDS, PRESCRIBE_MEDS, RESET_MEDS, EXIT);
      userInput = getInteger ("Please enter a menu option.");
     
      //Create if-statement for options
      if (userInput == LOG_IN) {
        doctorsName = getString ("What is your name, doctor?");
      } else if (userInput == DISPLAY_MEDS) {
        displayPrescriptions(MEDICINE_NAMES_AND_DESCRIPTIONS, medicinePrescribed);
      } else if (userInput == PRESCRIBE_MEDS) {
        medicinePrescribed(prescribeMedicine, MEDICINE_NAMES_AND_DESCRIPTIONS, medicinePrescribed);
      }
      else if (userInput == RESET_MEDS) {
        if (getYorN("\nAre you sure you want to reset the prescription history? (y/n)")) {
          medicinePrescribed = new boolean [MED_NAME];
          System.out.println("The prescription history has been reset.");
        }
      }
     
     
    } while (userInput != EXIT);
    displayGoodbyeMessage(doctorsName);
  }  
  //Display module to display menu options  
  private static void displayLogIn(int LOG_IN, int DISPLAY_MEDS, int PRESCRIBE_MEDS, int RESET_MEDS, int EXIT) {
    System.out.println("");
    System.out.println("                             **********************************************");
    System.out.println("                              Welcome to your Animal Prescription's History");
    System.out.println("                             **********************************************");
    System.out.println("Menu Options:");
    System.out.println(LOG_IN + ". Sign in as the animal's doctor");
    System.out.println(DISPLAY_MEDS + ". Display the Animal's Prescription History");
    System.out.println(PRESCRIBE_MEDS + ". Prescribe medicine");
    System.out.println(RESET_MEDS + ". Reset Animal's Prescription History");
    System.out.println(EXIT + ". Exit the program.");
  }//end module
 
  //Display menu for new menu that includes doctor's name once entered
  private static void displayMenu(String doctorsName, int LOG_IN, int DISPLAY_MEDS, int PRESCRIBE_MEDS, int RESET_MEDS, int EXIT) {
    System.out.println("");                        
    System.out.println("                             **********************************************");
    System.out.println("                              Welcome to your Animal Prescription's History");
    System.out.println("                             **********************************************");
    System.out.println("Menu Options for Dr. " + doctorsName + ":");
    System.out.println(LOG_IN + ". Sign in as the animal's doctor");
    System.out.println(DISPLAY_MEDS + ". Display the Animal's Prescription History");
    System.out.println(PRESCRIBE_MEDS + ". Prescribe medicine");
    System.out.println(RESET_MEDS + ". Reset Animal's Prescription History");
    System.out.println(EXIT + ". Exit the program.");
  }//end module
 
  // Create module to display medications and if they have already been prescribed
  private static void displayPrescriptions(String[][] MEDICINE_NAMES_AND_DESCRIPTIONS, boolean[] medicinePrescribed) {
    for (int i = 0; i < MEDICINE_NAMES_AND_DESCRIPTIONS.length; i++) {
      System.out.print("\nMedicine #" + (i+1) + ":");
      for (int j = 0; j < MEDICINE_NAMES_AND_DESCRIPTIONS[0].length; j++) {
        System.out.print(MEDICINE_NAMES_AND_DESCRIPTIONS[i][j] + "\t");
      }
      if (medicinePrescribed[i]) {
        System.out.print(" --- already prescribed");
      }
    }
   
  }// end module
 
  //Module to search if a medication is a valid option, and adds to prescription list if so
  public static void medicinePrescribed (int prescribeMedicine, String[][] MEDICINE_NAMES_AND_DESCRIPTIONS, boolean [] medicinePrescribed){
     if (isMedicationFull(medicinePrescribed)) {
        System.out.println("All available medications have been prescribed. Returning to menu.");
      }
     else {
    prescribeMedicine = getInteger("Please enter the medicine number you wish to prescribe. (Press 0 for menu.)");
   
    while (prescribeMedicine != 0 && (!isMedicationFull(medicinePrescribed))) {
       if (prescribeMedicine > medicinePrescribed.length) {
        System.out.println("Invalid entry. Try again.");
      }
      else if (medicinePrescribed[prescribeMedicine-1]) {
        System.out.println("You have already prescribed " + MEDICINE_NAMES_AND_DESCRIPTIONS[prescribeMedicine-1][0] + ", " + MEDICINE_NAMES_AND_DESCRIPTIONS[prescribeMedicine-1][1] + ".");
      }
      else {
        System.out.println("You have prescribed " + MEDICINE_NAMES_AND_DESCRIPTIONS[prescribeMedicine-1][0] + ", " + MEDICINE_NAMES_AND_DESCRIPTIONS[prescribeMedicine-1][1] + ".");
        medicinePrescribed[prescribeMedicine-1] = true;
      }//end if-else statement
      prescribeMedicine = getInteger("Please enter the medicine number you wish to prescribe. (Press 0 for menu.)");
      if (isMedicationFull(medicinePrescribed)) {
        System.out.println("All available medications have been prescribed. Returning to menu.");
      }
    }//end while
     }
  }//end function
  //Function to decide if the prescribed medication array is full
    public static boolean isMedicationFull (boolean [] medicinePrescribed) {
    for (int i = 0; i < medicinePrescribed.length; i++) {
      if (!medicinePrescribed[i]) return false;
    }
    return true;
  }//end function
 
  //Display module to display goodbye message
  private static void displayGoodbyeMessage(String doctorsName) {
    System.out.println("Signing out Dr. " + doctorsName + ".");
    System.out.println("Have a nice day!");
    System.out.println("----------- Program Terminating -----------");
  }//end module
 
  //Function to get integer input
   public static int getInteger(String msg) {
      System.out.println(msg);
      while (!keyboard.hasNextInt()) {
         keyboard.nextLine();
         System.err.println("Invalid integer. Try again.");
      }
      int number = keyboard.nextInt();
      keyboard.nextLine(); //flushes the buffer
      return number;
   }//end function
   
   //Function to get string input
   public static String getString(String msg) {
      String answer = "";
      System.out.println(msg);
      try {
         answer = keyboard.nextLine(); 
      }
      catch (Exception e) {
         System.err.println("Error reading input from user. Ending program.");
         System.exit(-1);
      } 
      
      while (answer.replaceAll(" ", "").equals("")) {
         System.err.println("Error: Missing input.");
         try {
            System.out.println(msg);
            answer = keyboard.nextLine(); 
         }
         catch (Exception e) {
            System.err.println("Error reading input from user. Ending program.");
            System.exit(-1);
         } 
      }
      return answer;            
   }//end function
   
   //Function to get Yes or No input. Calls getString to reject null and spaces
   public static boolean getYorN(String msg) {
      String answer = getString(msg);
      
      while (answer.compareToIgnoreCase("y")   != 0 
          && answer.compareToIgnoreCase("yes") != 0 
          && answer.compareToIgnoreCase("n")   != 0 
          && answer.compareToIgnoreCase("no")  != 0) {
         
         if (answer.replaceAll(" ", "").equals("")) {
            System.err.println("Error: Missing y/n input.");
         } else {
            if (answer.compareToIgnoreCase("y")   != 0 
             && answer.compareToIgnoreCase("yes") != 0 
             && answer.compareToIgnoreCase("n")   != 0 
             && answer.compareToIgnoreCase("no")  != 0) {
               System.err.println("Error: Unexpected input.");
            }
         }
         answer = getString(msg);
      } 
      
      if  (answer.compareToIgnoreCase("y")   == 0  
        || answer.compareToIgnoreCase("yes") == 0) {
         return true;
      } 
      else {
         return false;
      }
   }//end function
   
}//end class