public class Expr {
    //set up the three types of expressions in an interface
    //Visitor interface
    //TODO: set up these classes later
    public interface Visitor<R>{
        R visitBinaryExpr(Binary expr);
        R visitLiteralExpr(Literal expr);
        R visitVariableExpr(Variable expr);
    }

}
