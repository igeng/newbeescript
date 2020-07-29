package lexer;

public class Token {
    TokenType _type;
    String _value;

    public Token(TokenType type, String value) {
        this._type = type;
        this._value = value;
    }

    public TokenType getType() {
        return _type;
    }
    // 返回类型和对应的值
    @Override
    public String toString() {
        return String.format("type %s, value %s", _type, _value);
    }
    // 判断是否是变量
    public boolean isVariable() {
        return _type == TokenType.VARIABLE;
    }
    // 判断是否是值类型
    public boolean isScalar() {
        return _type == TokenType.INTEGER || _type == TokenType.FLOAT ||
                _type == TokenType.STRING || _type == TokenType.BOOLEAN;
    }
}
