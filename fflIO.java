import java.io.*;
import java.util.*;

public class fflIO {
		/**
    * Attempts to open the stock.txt file. The function will throw an exception if the file doesn't exist, and returns the file if it does
    * Function originally by M. Lutchman (2015)-- Procedural Programming II (Oct '15 PT)
    *@param fileName The name of the file to be opened
    *@return The opened file
    *@exception FileNotFoundException If the specified file is not found
    */ 
    public static Scanner openFile( String fileName) throws FileNotFoundException{
        Scanner fileInput = null;
        try{
            fileInput = new Scanner(new File(fileName));
        }
        catch ( FileNotFoundException fnfex){
            ArrayList<String> output = new ArrayList<String>(Arrays.asList("The specified file (%s) cannot be opened.%n", fileName));
            printOutput(2, output);
        }
        return fileInput;
    }

    /**
    * Responsible for all screen printing, both formatted and unformatted
    *@param mode Specifies the procedure's mode; mode 1 facilitates the printing of unformatted text from an ArrayList, while mode 2 allows for the printing of formatted text and also gets its variables from an ArrayList
    *@param output The output to be printed to the screen
    */
    public static void printOutput (int mode, ArrayList<String> output){
        if (mode==1){
            for (int i=0; i<output.size(); i++){
                System.out.printf(output.get(i));
            }
        } else {
            for (int i=0; i<output.size(); i+=2){
                System.out.printf(output.get(i), output.get(i+1));
            }
        }
    }

    /**
    * Responsible for capturing all input for the application. The user prompt is taken as a variable, and the user input is returned to the caller as a String, which is later formatted to the required type
    *@param prompt The text prompt to be printed to the screen
    *@return input The user input as a String; this will be formatted (as necessary), by the calling procedure 
    */
    public static String getUserInput(String prompt){
        String input="";
        Scanner keyboard = new Scanner(System.in);
        System.out.printf(prompt);
        input= keyboard.next();
        return input;
    }

    /**
     * updates the source txt file with current stock levels
     * @param  products    an ArrayList containing all the products that comprise the inventory
     * @throws IOException Required declaration of IOException. If the file doesn't exist, it's created; the exception isn't actually thrown
     */
    public static void updateStockFile(ArrayList<Product> products)throws IOException{
        String file = "stock3.txt";
        String line, code, label, count, cost= "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i=0; i<products.size(); i++){
            code= products.get(i).giveProdCode()+" ";
            label= products.get(i).giveDescription()+" ";
            count= products.get(i).giveAsString("stock")+" ";
            cost= products.get(i).giveAsString("price");
            line= code+label+count+cost;
            try{
                writer.write(line);
                writer.newLine();            
            } catch (IOException x) {
                System.err.format("IOException: %s%n", x);
            }
        }
        writer.close();        
    }
}