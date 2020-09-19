package parser;

public enum ASTNodeTypes {
    BLOCK, // 代码块
    BINARY_EXPR, // 1+1
    UNARY_EXPR, // ++i
    VARIABLE,
    SCALAR, // 1.0, true
    IF_STMT,
    WHILE_STMT,
    FOR_STMT,
    ASSIGN_STMT,
    FUNCTION_DECLARE_STMT,
    DECLARE_STMT;
}
