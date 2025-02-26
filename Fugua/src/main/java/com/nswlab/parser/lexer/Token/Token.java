package com.nswlab.parser.lexer.Token;

import lombok.Getter;

// !TODO: add annotation processing
@Getter
public class Token {
    private final TokenType type;
    private final String value;
    private final int line;

    public Token(TokenType type, String value, int line) {
        this.type = type;
        this.value = value;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                "value=" + value +
                "line=" + line +
                "}";
    }
}
