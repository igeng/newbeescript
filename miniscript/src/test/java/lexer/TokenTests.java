package lexer;

import common.PeekIterator;
import common.PeekIteratorTests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTests {

    @Test
    public void test_varOrKeyword() {
        var it1 = new PeekIterator<Character>("if abc".chars().mapToObj(x -> (char)x));
        var it2 = new PeekIterator<Character>("true abc".chars().mapToObj(x -> (char)x));
        var token1 = Token.makeVarOrKeyword(it1);
        var token2 = Token.makeVarOrKeyword(it2);

        assertToken(token1, "if", TokenType.KEYWORD);
        assertToken(token2, "true", TokenType.BOOLEAN);
        it1.next();
        var token3 = Token.makeVarOrKeyword(it1);
        assertToken(token3, "abc", TokenType.VARIABLE);
    }

    void assertToken(Token token, String value, TokenType type) {
        assertEquals(type, token.getType());
        assertEquals(value, token.getValue());
    }

}
