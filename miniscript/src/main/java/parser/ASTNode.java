package parser;

import lexer.Token;

import java.util.ArrayList;
import java.util.List;

public abstract class ASTNode {
    /* tree */
    protected ArrayList<ASTNode> children;
    protected ASTNode parent;

    /* 关键信息 */
    protected Token lexeme; // 词法单元
    protected String label; // 备注 标签

    public ASTNode(ASTNode _parent) {
        this.parent = _parent;
    }

    public ASTNode getChild(int index) {
        return this.children.get(index);
    }

    public void addChild(ASTNode node) {
        children.add(node);
    }

    public Token getLexeme() {
        return lexeme;
    }

    public List<ASTNode> getChildren() {
        return children;
    }
}
