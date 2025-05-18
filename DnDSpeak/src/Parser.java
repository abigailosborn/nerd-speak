import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Parser {
    static String file_name;
    static String file_extension;

    //create an array list to hold all of the command words 
    static ArrayList<String> commands = new ArrayList<String>();

    //static Queue<String> the_stack_tm = new Queue<String>();

    //create an Arraylist to hold all file info
    static ArrayList<String> file_lines = new ArrayList<String>();
    

    public Parser(String file_name, String file_extension){
        this.file_name = file_name;
        this.file_extension = file_extension;
    }
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        //read in the file if it has the correct file extension
        System.out.println("What is your file name: ");
        file_name = scr.nextLine();

        //get file extension
        get_file_extension(file_name);
        
        //check file extension is correct
        if(file_extension.equals("dnd")){
            //read in the file
            try{
                File dnd_file = new File (file_name);

                Scanner file_scan = new Scanner(dnd_file);

                while(file_scan.hasNextLine()){
                    file_lines.add(file_scan.nextLine());
                }
            } 
            catch(Exception e){
                System.out.println("File could not be found");
            }
        }
        else{
            System.out.println("Incorrect file extension");
        }

        //Start dealing with commands
        add_commands();

        }

    
    //get all commands from the file and place tham on the stack
    //public 

    //get the extension of the file 
    public static void get_file_extension(String file_name){
        String[] temp = file_name.split("\\.");
        file_extension = temp[1];
    }

    //add command words to the array
    public static void add_commands(){
        //the message command will be used to print to screen
        commands.add("message");
        //comment out something 
        commands.add("illusory script");
        //darkvision makes comments print to screen maybe
        commands.add ("darkvision");
        //fireball ends the program
        commands.add("fireball");
    }
}
