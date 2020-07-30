package lexer;

import common.AlphabetHelper;
import common.PeekIterator;

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

    public String getValue() {
        return _value;
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

    /**
     * 提取变量或关键字
     * @param it
     * @return
     */
    public static Token makeVarOrKeyword(PeekIterator<Character> it) {

        String s = "";
        while (it.hasNext()) {
            var lookahead = it.peek();
            if (AlphabetHelper.isLiteral(lookahead)) {
                s += lookahead;
            }
            else {
                break;
            }
            it.next();
            // 循环不变式
        }

        // 判断关键词OR变量
        if (Keywords.isKeyword(s)) {
            return new Token(TokenType.KEYWORD, s);
        }
        // s是String类型，要用equals，用==比较比的是地址
        if (s.equals("true") || s.equals("false")) {
            return new Token(TokenType.BOOLEAN, s);
        }

        return new Token(TokenType.VARIABLE, s);

    }
}
