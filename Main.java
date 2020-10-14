// Aiden Onyejiaka

package com.company;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;
import java.lang.String;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class Main
{
    static Scanner myScanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        String name;                                                //Initializing variables
        name = "[BLANK]";
        String year;
        year = "[BLANK]";
        Double gpa;
        gpa = 0.0;
        int choice;
        choice = 0;


        menu();                                                     //Calls Menu
        System.out.println("Enter a number (1-8): ");
        choice= getIntVal();

        while(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7 && choice != 8) {
            System.out.println("Error, please enter an integer 1-8: ");
            choice = myScanner.nextInt();
        }


        while(choice != 8){                                         //While user choice is != 5 since 5 = exit
            if (choice == 1) {
                System.out.println("Students name: ");
                name = myScanner.next();
            }
            if (choice == 2) {
                System.out.println("Students Academic Year (ALL CAPS): ");
                year = myScanner.next();
                //yearCheck(year, myScanner);
                while(!year.equals("FRESHMAN") && !year.equals("SOPHOMORE") && !year.equals("JUNIOR") && !year.equals("SENIOR"))
                {
                    System.out.println("INVALID FORMAT TRY AGAIN");
                    System.out.println("Students Academic Year (ALL CAPS): ");
                    year = myScanner.next();
                }
            }
            if (choice == 3) {
                System.out.println("Students GPA: ");
                gpa = myScanner.nextDouble();
                while(gpa < 0.0 || gpa > 4.0)
                {
                    System.out.println("GPA ranges from 0.0 - 4.0");
                    System.out.println("Please try again: ");
                    gpa = myScanner.nextDouble();
                }
            }
            if (choice == 4) {
                System.out.println("");
                System.out.println("------Student Information------");
                System.out.println("Name: " + name);
                System.out.println("Academic Year: " + year);
                System.out.println("GPA: " + gpa);
                System.out.println("");
            }
            if(choice == 5) {
                if(!name.equals("[BLANK]")) {                                  //Tests if fields are at their defaults
                }
                if(!year.equals("[BLANK]")){
                }
                if(gpa != 0.0){
                }
                else{
                    System.out.println("Must populate all fields before writing data to file.");
                    System.out.println("");
                }
                writeData(name, year, gpa, myScanner);
            }
            if(choice == 6){
                readData(myScanner);
            }
            if(choice == 7){
                System.out.println("Enter name of student you wish to search for: ");
                String searchName = myScanner.next();
                String result = search(0, searchName);
                System.out.println(result);

            }

            System.out.println("---------------------------------");
            menu();
            System.out.println("Enter a number (1-7): ");
            choice = myScanner.nextInt();
        }
    }


    public static void writeData(String name, String year, Double gpa, Scanner myScanner) throws IOException{
        File myFile = new File("database.csv");
        try {

            FileWriter x = new FileWriter(myFile, true);




            PrintWriter myFileWriter = new PrintWriter(x);
            if (myFile.createNewFile()) {                               //Tests if the file already exists
                System.out.println("File created");
            } else {
                System.out.println("This file already exists");
            }
            myFileWriter.println(name+", "+year+", "+gpa);              //write to file
            myFileWriter.close();                                       //close file
        }catch (IOException e){
            System.out.println("Error occurred");
            e.printStackTrace();
        }
    }


    public static void readData(Scanner myScanner) throws IOException {
        File myFile = new File("database.csv");
        String line;
        try{
            Scanner reader = new Scanner(myFile);
            while(reader.hasNextLine()){
                line = reader.nextLine();
                System.out.println(line);
            }
        } catch(Exception myEx) {
            System.out.println("Error!");
        }
    }


    public static int getIntVal() {                                     //Validates menu choice
        while (true){
            try {
                return myScanner.nextInt();
            }
            catch (Exception e) {                                       //catches any errors
                myScanner.next();
                System.out.println("Error, must enter an integer 1-7");
            }
        }
    }


    public static String search(int row, String searchName) throws IOException {
        String resultRow = null;
        BufferedReader br = new BufferedReader(new FileReader("database.csv"));             //creates bufferedreader
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[row].equals(searchName)) {
                resultRow = line;
                //break;
            }
        }
        br.close();                                         //close file
        return resultRow;
    }


    static void menu() {
        System.out.println("1. Enter the Students name");
        System.out.println("2. Enter the Students Academic Year");
        System.out.println("3. Enter the Students GPA");
        System.out.println("4. Display Students Info");
        System.out.println("5. Write Data To File");
        System.out.println("6. Read Data From File");
        System.out.println("7. Search");
        System.out.println("8. Exit");
    }
}



