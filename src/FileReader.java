import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class FileReader {
    private String fileName;
    private ArrayList<Person> people = new ArrayList<>();
    ArrayList<String> quarterNames;
    ArrayList<String> expectedTotals;
    int quarterCount;

    public FileReader(String fileName){
        this.fileName = fileName;
        quarterCount = -1;
        quarterNames = new ArrayList<>();
        expectedTotals = new ArrayList<>();
    }

    public void parseFile() throws FileNotFoundException {
        String line;
        String currentToken;
        Scanner scan = new Scanner(new File(fileName));
        String name;
        while (scan.hasNextLine()){
            name = "";
            currentToken = scan.next();
            //Sets the current quarter and expected total
            if (currentToken.contains("Quarter: ")){
                quarterNames.add(scan.next());
                quarterCount++;
                scan.next();
                expectedTotals.add(scan.next());
            }

            else {
                //ignore first token if it has numbers
                if (hasNumbers(currentToken)){
                    currentToken = scan.next();
                }
                //time to start building the names
                while (!hasNumbers(currentToken)){
                    name = name + currentToken.toUpperCase(Locale.ROOT) + " ";
                    currentToken = scan.next();
                }
                //exits while loop so the current token must be the wage,
                //check if person already exists
                if (hasPerson(name)){
                    //now need to check if they already have a value in
                    Person tempReference = findPerson(name);
                    //adds wage to what they already have
                    if (tempReference.getWage(quarterCount) != null){
                        tempReference.addToExistingWage(currentToken, quarterCount);
                    }
                    //if they dont have a value in, then they do now :)
                    else{
                        tempReference.addWage(currentToken);
                    }
                }

            }
            scan.nextLine();
        }
    }

    public boolean hasNumbers(String input){
        String numbers = "1234567890";
        for (int i = 0; i < input.length(); i++){
            for (int j = 0; j < numbers.length(); j++){
                if (numbers.charAt(i) == input.charAt(j)){
                    return true;
                }
            }
        }
        return false;
    }

    public String cleanWage(String input){
        //need to remove , and $
        input = input.replace("$","");
        input = input.replace(",","");
        return input;
    }

    //check arraylist if someone alrdy exists
    public boolean hasPerson(String name){
        for (Person p : people){
            if (p.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    //return person with a certain name
    public Person findPerson(String name){
        for (Person p : people){
            if (p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
}
