package parser;

public class Scalar extends Factor {
    public Scalar(ASTNode _parent, ASTNodeTypes _type, String _label) {
        super(_parent, ASTNodeTypes.SCALAR, null);
    }
}
