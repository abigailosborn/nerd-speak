public class assignment_node extends ast_node{
    public final String variable_name;
    public final ast_node expression;

    public assignment_node(String variable_name, ast_node expression){
        this.variable_name = variable_name;
        this.expression = expression;
    }
}
