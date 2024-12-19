
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileHandler {
    public static final String CUSTOMER_DATA = "Customer.txt";
	public static final String ADMIN_DATA = "AdminDetails.txt";
	
    
    public static final String ELECTRONICS_FILE = "Electronics.txt";
    public static final String SPORTSANDBOOKS_FILE = "SportsAndBooks.txt";
    public static final String HOMEANDFURNITURE_FILE = "HomeAndFurniture.txt";
    public static final String FASHION_FILE = "Fashion.txt";
    public static final String ORDER_FILE = "Order.txt";
    public static final String CART_FILE = "Cart.txt";
    public static final String SPLIT_CEMI = ";";
    public static final String SPLIT_COMMA = ",";

    public static String readFromFile(String filename) {
        String data = "";
        try {
            FileReader inputFile = new FileReader(filename);
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine()) {
                data = data + parser.nextLine() + SPLIT_CEMI;
            }
            inputFile.close();
            return data;
        } catch (FileNotFoundException exception) {
            System.out.println("File does not exist.");
        } catch (IOException exception) {
            System.out.println("Unexpected I/O error occured");
        }
        return data;
    }

        public static void writeToFile(String saveData, String filePath) {
        try {
            PrintWriter outputFile = new PrintWriter(filePath);
            outputFile.println(saveData);
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Sorry, fail to save borrowers' data!");
        }
    }

    public static void appendToFile(String saveData, String filePath){
    	try {
    	    FileWriter fw = new FileWriter(filePath,true); //the true will append the new data
    	    fw.write(saveData);//appends the string to the file
    	    fw.close();
    	}
    	catch(IOException ioe) {
    	    System.err.println("IOException: " + ioe.getMessage());
    	}
    }

    private void exitLibrary() {

    }

}
