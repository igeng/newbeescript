package lexer;

public class LexicalException extends Exception {
    private String msg;

    public LexicalException(char c) {
        msg = String.format("Unexcepted character %c", c);
    }

    public LexicalException(String _msg) {
        msg = _msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
