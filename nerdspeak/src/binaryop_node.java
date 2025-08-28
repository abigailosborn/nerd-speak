public class binaryop_node extends ast_node{
    public final ast_node left;
    public final String operator;
    public final ast_node right;

    public binaryop_node(ast_node left, String operator, ast_node right){
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
