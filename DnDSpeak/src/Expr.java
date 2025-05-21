public abstract class Expr {
    //set up the three types of expressions in an interface
    //Visitor interface
    //R is a generic return type, can be String, int, etc. Basically var
    //TODO: set up these classes later
    public interface Visitor<R>{
        R visitBinaryExpr(Binary expr);
        R visitLiteralExpr(Literal expr);
        R visitVariableExpr(Variable expr);
    }

    //define accept method to be used for each variable 
    public abstract <R> R accept(Visitor<R> visitor);

    public static class Binary extends Expr{
        //set up variables for mathmematical expressions (ex: 1+3)
        public final Expr left_half;
        public final Expr right_half;
        public final Token operator;

        public Binary(Expr left_half, Expr right_half, Token operator){
            this.left_half = left_half;
            this.right_half = right_half;
            this.operator = operator;
        }

        @Override
        public <R> R accept(Visitor<R> visitor){
            return visitor.visitBinaryExpr(this);
        }
    }

    public static class Literal extends Expr{
        //set up String Literals
        public final String str;

        public Literal(String str){
            this.str = str;
        }

        @Override
        public <R> R accept(Visitor<R> visitor){
            return visitor.visitLiteralExpr(this);
        }
    }
    public static class Variable extends Expr{
        public final String vari;

        public Variable(String vari){
            this.vari = vari;
        }

        @Override
        public <R> R accept(Visitor<R> visitor){
            return visitor.visitVariableExpr(this);
        }
    }
}
