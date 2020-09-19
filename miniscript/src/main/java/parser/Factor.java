package parser;

public abstract class Factor extends ASTNode {
//    运算符两边的东西
    public Factor(ASTNode _parent, ASTNodeTypes _type, String _label) {
        super(_parent, _type, _label);
    }
}
