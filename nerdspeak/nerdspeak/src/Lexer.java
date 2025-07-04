import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer{
    public static String file_extension = "";
    public static String[] words;
    public static String current_line;
    public static Scanner file_scan;
    public static final String FILE_NAME = "hi.dnd";
    public static ArrayList<Token> tokens = new ArrayList<Token>();
    public static void lex_text(){
        //read in the file for the lexer
        get_file_extension(FILE_NAME);
        if(file_extension.equals("dnd")){
            //read in the file
            try{
                File dnd_file = new File(FILE_NAME);

                file_scan = new Scanner(dnd_file);
                current_line = file_scan.nextLine();
                //break each line into words/characters depending
                words = current_line.split(" ");

            }
            catch(Exception e){
                System.out.println("File could not be found");
            }
        }
        else{
            System.out.println("Incorrect file extension");
        }

        while(!current_line.equals("I cast fireball")){
            for(int i = 0; i < words.length; i++){
                //indentify dice
                if(words[i].equals("d4") || words[i].equals("d6") || words[i].equals("d8") || words[i].equals("d10") || words[i].equals("d12") || words[i].equals("d20") || words[i].equals("d100")){
                    //create a token for this variable
                    tokens.add(new Token(Token.TokenType.DICE, words[i]));
                }
                else if(words[i].equals("*")){
                    String current_word = words[i + 1];
                    String str = "";
                    int counter = 0;
                    //TODO: there is an asterix at the end for some reason still you need to fix that
                    //while there is not another asterix add each word to the value of the Token
                    while(!current_word.equals("*")){
                        str += words[i + 1];
                        counter++;
                        i++;
                        //add in spaces after each word
                        if(counter > 0 || !words[i].equals("*")){
                            str += " ";
                        }
                        current_word = words[i];
                    }
                    tokens.add(new Token(Token.TokenType.LANGUAGE, str));
                }
                //silvery barbs for subtraction
                else if(words[i].equals("silvery-barbs")){
                    tokens.add(new Token(Token.TokenType.MINUS, "-"));
                }
                //bless for addition
                else if(words[i].equals("bless")){
                    tokens.add(new Token(Token.TokenType.PLUS, "+"));
                }
                //mirror image for multiplication
                else if(words[i].equals("mirror-image")){
                    tokens.add(new Token(Token.TokenType.MULTIPLY, "x"));
                }
                //decompose for divide
                else if(words[i].equals("decompose")){
                    tokens.add(new Token(Token.TokenType.DIVIDE, "/"));
                }
                else if(words[i].equals("DC")){
                    tokens.add(new Token(Token.TokenType.EQUAL, "="));
                }
                //this is the equivalent of (), it is for FUNCTION CALLS
                else if(words[i].equals("/*")){
                    tokens.add(new Token(Token.TokenType.PAREN, "/*"));
                }
                //store other words, the parser will handle this later 
                else{
                    tokens.add(new Token(Token.TokenType.IDENTIFIER, words[i]));
                }
            }

           //reset current line add words by reading in the next line 
           current_line = file_scan.nextLine();
           words = current_line.split(" ");
        }

        for(Token token: tokens){
            System.out.println(token.type + " " + token.value);
        }

    }
    public static void read_input(String file_name){
        String file_extension = "";
        Scanner scr = new Scanner(System.in);

        //get file extension
        get_file_extension(file_name);
    }

    
    //get all commands from the file and place tham on the stack
    //public 

    //get the extension of the file 
    public static void get_file_extension(String file_name){
        String[] temp = file_name.split("\\.");
        file_extension = temp[1];
    }
}
