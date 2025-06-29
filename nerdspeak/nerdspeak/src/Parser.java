import java.util.ArrayList;

public class Parser {
    public static ArrayList<Token> token_list;
    public static Token current_token;
    public static int counter = 0;
    public static ArrayList<Token> the_stack_tm;
    public static void main(String[] args){
        //Grab the token list from the lexer
        Lexer lex = new Lexer();
        lex.lex_text();
        token_list = lex.tokens;
        //variable to save the token that is currently being looked at
        current_token = token_list.get(counter);

    }

    public static void advance(){
        counter++;
    }
    public static Token previous(){
        return token_list.get(counter - 1);
    }
}
