import java.util.ArrayList;
import java.util.Scanner;

public class Lexer{
    public static void main(String[] args){
        Scanner scr = new Scanner(System.in);
        String current_line = scr.nextLine();
        //break each line into words/characters depending
        String[] words = current_line.split(" ");
        ArrayList<Token> tokens = new ArrayList<Token>();
        while(!current_line.equals("I cast fireball")){
            for(int i = 0; i < words.length; i++){
                //indentify dice
                if(words[i].equals("d4") || words[i].equals("d6") || words[i].equals("d8") || words[i].equals("d10") || words[i].equals("d12") || words[i].equals("d20") || words[i].equals("d100")){
                    //create a token for this variable
                    tokens.add(new Token(Token.TokenType.DICE, words[i]));
                    // get the name of the integer variable
                    i++;
                    tokens.add(new Token(Token.TokenType.IDENTIFIER, words[i]));
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
                else if(words[i].equals("message")){
                    tokens.add(new Token(Token.TokenType.KEYWORD, words[i]));
                }
                //store the nonsense words, there will probably be a lot 
                else{
                    tokens.add(new Token(Token.TokenType.DRIVEL, words[i]));
                }
            }

           //reset current line add words by reading in the next line 
           current_line = scr.nextLine();
           words = current_line.split(" ");
        }

        for(Token token: tokens){
            System.out.println(token.type + " " + token.value);
        }

    }
}
