import java.util.ArrayList;

public class Parser {
    public static ArrayList<Token> token_list;
    public static void main(String[] args){
        Lexer lex = new Lexer();
        lex.lex_text();
        token_list = lex.tokens;

        for(Token t : token_list){
            System.out.println(t);
        }
    }
}
