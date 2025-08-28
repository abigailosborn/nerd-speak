import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static ArrayList<Token> token_list;
    public static int counter = 0;
    public static ArrayList<ast_node> statements = new ArrayList<ast_node>();
    public static void main(String[] args){
        //Grab the token list from the lexer
        Lexer lex = new Lexer();
        lex.lex_text();
        token_list = lex.tokens;

        for(int i = 0; i < token_list.size(); i++){
            ast_node statement = new ast_node(){};
            statement = parse_statement();
            statements.add(statement);
            System.out.println(statements.get(i));
        }
    }

    public static ast_node parse_statement(){
        //get the current token being worked with and advance to the next one
        Token current = token_list.get(counter);
        System.out.println(current.type);
        //Check if creating a variable or calling a function
        if((current.type).equals(Token.TokenType.IDENTIFIER)){
            counter++;
            current = token_list.get(counter);
            if(current.type.equals(Token.TokenType.EQUAL)){
                ast_node expr = parse_expression();
                 //get the variable name 
                return new assignment_node(token_list.get(counter - 2).value, expr);
            }
            else if(token_list.get(counter).type.equals("LPAREN")){
                //get the parameters for a function if applicable
                ArrayList<ast_node> args = new ArrayList<>();
                if(!token_list.get(counter).type.equals("RPAREN")){
                    args.add(parse_expression());
                    while(token_list.get(counter).type.equals("COMMA")){
                        counter++;
                        args.add(parse_expression());
                    }
                }
                return new function_node(current.value, args);
            }
            else{
                throw new RuntimeException("Expected '=' or '*\\' after identifier");
            }
        }
        else{
            throw new RuntimeException("Expected identifier");
        }
        
    }

    private static ast_node parse_expression(){
        ast_node left = parse_term();
        while(counter < token_list.size() && (token_list.get(counter).type).equals("PLUS")){
            counter+=2;
            ast_node right = parse_term();
            left = new binaryop_node(left, "+", right);
        }
        return left;
    }

    //parse ints, strings, and variables
    private static ast_node parse_term(){
        //get current token and move to next one
        Token current = token_list.get(counter-1);
        counter++;
        switch(current.type){
            case DICE:
                return new dice_node(current.value);
            case LANGUAGE:
                return new language_node(current.value);
            case IDENTIFIER:
                return new variable_node(current.value);
            default:
                throw new RuntimeException("Unexpected token: " + current.type);
        }
    }
}
