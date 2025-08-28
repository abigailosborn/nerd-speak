import java.util.List;

public class function_node extends ast_node{
    public final String function_name;
    public final List<ast_node> arguments;

    public function_node(String function_name, List<ast_node> arguments){
        this.function_name = function_name;
        this.arguments = arguments;
    }
}
