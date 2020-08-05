package lexer;

import common.AlphabetHelper;
import common.PeekIterator;
import java.util.ArrayList;
import java.util.stream.Stream;

// 关于整个词法分析器的定义
public class Lexer {
    //把代码当作字符流传进来，返回ArrayList，应该返回Token的流，这里为了简便返回arraylist
    public ArrayList<Token> analyse(Stream source) throws LexicalException {

        var tokens = new ArrayList<Token>();
        var it = new PeekIterator<Character>(source, (char)0);

        while(it.hasNext()) {
            char c = it.next();

            if (c == 0) {
                break;
            }

            char lookahead = it.peek();

            if (c ==' ' || c == '\n') {
                continue;
            }

            // 删除注释
            if (c == '/') {
                if (lookahead == '/') {
                    while (it.hasNext() && (c = it.next()) != '\n');
                }
                else if (lookahead == '*') {
                    boolean valid = false;
                    while (it.hasNext()) {
                        char p = it.next();
                        if (p == '*' && it.peek() == '/') {
                            it.next();
                            valid = true;
                            break;
                        }
                    }
                    if (!valid) {
                        throw new LexicalException("comments not match");
                    }
                    continue;
                }
            }

            if (c == '{' || c == '}' || c == '(' || c == ')') {
                tokens.add(new Token(TokenType.BRACKET, c+""));
                continue;
            }

            if (c == '"' || c == '\'') {
                it.putBack();
                tokens.add(Token.makeString(it));
                continue;
            }

            if (AlphabetHelper.isLetter(c)) {
                it.putBack();
                tokens.add(Token.makeVarOrKeyword(it));
                continue;
            }

            if (AlphabetHelper.isNumber(c)) {
                it.putBack();
                tokens.add(Token.makeNumber(it));
                continue;
            }
            // + - .
            // +-: 3+5, +5, 3 * -5
            if ((c == '+' || c =='-' || c =='.') && AlphabetHelper.isNumber(lookahead)) {
                var lastToken = tokens.size() == 0 ? null : tokens.get(tokens.size() - 1);
                if (lastToken == null || !lastToken.isNumber() || lastToken.isOperator()) {
                    it.putBack();
                    tokens.add(Token.makeNumber((it)));
                    continue;
                }
            }
            if (AlphabetHelper.isOperator(c)) {
                it.putBack();
                tokens.add(Token.makeOp(it));
                continue;
            }
            throw new LexicalException(c);
        }// end while
        return tokens;
    }
}
