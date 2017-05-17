/**
 * Created by erikn
 * CODE BY ERIK NAKAMURA
 * BOSTON, MA
 * MIT LICENSE
 */

import java.io.*;
import java.util.Scanner;

/**
 * TERMINAL APPLICATION!
 * The following program takes a line-delineated list of words and edits it to get rid of either proper nouns, plurals, or both.
 * It accepts an input file and WILL EDIT THE FILE, it DOES NOT CREATE A COPY.
 */
public class LexicSquareListEditing {

    public static void main(String[] args){
        /*
        User enters what actions they want preformed on their file.
         */
        System.out.println("Welcome to the lexic square list editor! The current tools are available:");
        //Please add tool options here in comment and in code:
        /**
         * Take out proper nouns
         * Take out plurals ('s)
         */
        System.out.println("(1) Plural Remover \n(2) Proper Noun Remover (names, places, companies, etc.)\n(3) Removes all numbers\n(4) Count total words and total letters in list");
        System.out.println("Type in the numbers (not separated) that you want to apply:");
        Scanner scan = new Scanner(System.in);
        String actions = scan.nextLine();
        for(int i=0;i<actions.length();i++){
            int action = Character.getNumericValue(actions.charAt(i));
            if(action == 1){
                System.out.println("Removing possessives will be applied.");
            }
            else if (action == 2){
                System.out.println("Removing proper nouns will be applied.");
            }
            else if (action == 3){
                System.out.println("Removing all numbers will be applied");
            }
            else if (action == 4){
                System.out.println("The list will be counted in total words and total letters (all lines with characters on them will be counted, empty lines will be ignored).");
            }
        }

        /*
        User enters file directory here and confirms actions...
         */
        String fileDirectory = "//";
        boolean goodFile = false;
        while(goodFile == false) {
        System.out.println("Please enter the directory of the file you wish you edit (must be .txt file, line delineated)");
        fileDirectory = scan.nextLine();
        File readFile = new File(fileDirectory);
            if (readFile.exists() && !readFile.isDirectory()) {
                System.out.println("File found. Press enter to make edits. (Remember, file will be permanently changed)");

                if((scan.nextLine()).equals("")){
                    goodFile = true;
                }
                else{
                    goodFile = false;
                }
            } else {
                System.out.println("File not found, please make sure it is entered properly!");
                goodFile = false;
            }
        }

        /*
        Methods are called based on what actions the user wanted.
        Note that this uses the exact same decision making process as the part above that prints what the user wants.
         */

        if(goodFile == true){
            File readFile = new File(fileDirectory);
            for(int i=0;i<actions.length();i++){
                int action = Character.getNumericValue(actions.charAt(i));
                if(action == 1){
                    removePossessive(readFile);
                }
                else if (action == 2){
                    removeProperNouns(readFile);
                }
                else if (action == 3){
                    removeNumbers(readFile);
                }
                else if (action == 4){
                    countWords(readFile);
                }
            }
        }




    }

    public static void removePossessive(File readFile){
        System.out.println("Possessive removal begin");
        try {
            File tempFile = File.createTempFile("fileConvert" , ".temp", readFile.getParentFile());
            PrintWriter writer = new PrintWriter(tempFile, "UTF-8");
            FileReader reader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(reader);
            String line;

            while((line = br.readLine()) != null){
                int c = line.indexOf(39);
                if(c < 0){
                    System.out.println(line);
                    writer.println(line);
                }
            }



            readFile.delete(); // remove the old file
            tempFile.renameTo(readFile); // Rename temp file
            br.close();
        } catch (IOException e){
            System.out.println("Remove Possesives Failed");
            e.printStackTrace();
        }

    }

    public static void removeProperNouns(File readFile){
        System.out.println("Proper noun removal begin");
        try {
            File tempFile = File.createTempFile("fileConvert" , ".temp", readFile.getParentFile());
            PrintWriter writer = new PrintWriter(tempFile, "UTF-8");
            FileReader reader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(reader);
            String line;

            while((line = br.readLine()) != null){
                if(!Character.isUpperCase(line.charAt(0))){
                    writer.println(line);
                    System.out.println(line);
                }
            }


            readFile.delete(); // remove the old file
            tempFile.renameTo(readFile); // Rename temp file
            br.close();
        } catch (IOException e){
            System.out.println("Remove Proper Nouns Failed");
            e.printStackTrace();
        }

    }

    public static void removeNumbers(File readFile){
        System.out.println("Remove numbers begin");
        try {
            File tempFile = File.createTempFile("fileConvert" , ".temp", readFile.getParentFile());
            PrintWriter writer = new PrintWriter(tempFile, "UTF-8");
            FileReader reader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(reader);
            String line;

            while((line = br.readLine()) != null){
                boolean hasNumber = false;
                for(int i=0;i< line.length();i++){
                    char lineChar = line.charAt(i);
                    if(Character.isDigit(lineChar)){
                        hasNumber = true;
                        break;
                    }
                }
                if(!hasNumber){
                    System.out.println(line);
                    writer.println(line);
                }
            }

            readFile.delete(); // remove the old file
            tempFile.renameTo(readFile); // Rename temp file
            br.close();
        } catch (IOException e){
            System.out.println("Remove Numbers Failed");
            e.printStackTrace();
        }
    }

    public static void countWords(File readFile){
        try {
            FileReader reader = new FileReader(readFile);
            BufferedReader br = new BufferedReader(reader);
            String line;
            int wordCount = 0;
            int letterCount = 0;

            while((line = br.readLine()) != null){
                    wordCount++;
                    letterCount += line.length();
            }
            System.out.println("Total Number of words: " + wordCount);
            System.out.println("Total number of letters: " + letterCount);
            System.out.println("Avg. letters per word: " + (float)letterCount/wordCount);
            br.close();
        } catch (IOException e){
            System.out.println("Remove Numbers Failed");
            e.printStackTrace();
        }
    }

}

/*
boolean hasUpperCase = false;
                for(int i=0;i< line.length();i++){
                    char lineChar = line.charAt(i);
                    if(Character.isUpperCase(lineChar)){
                        hasUpperCase = true;
                        break;
                    }
                }
                if(!hasUpperCase){
                    System.out.println(line);
                    writer.println(line);
                }
 */