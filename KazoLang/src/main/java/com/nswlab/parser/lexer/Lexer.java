package com.nswlab.parser.lexer;

import com.nswlab.parser.lexer.Token.*;
import java.util.*;

public class Lexer {
    private final String input;       // input ( program )
    private final List<Token> tokens; // Tokens array
    private int pos;                  // position in input
    private int length;               // number of chars in input
    private int line;                 // line number
    private static final String OPERATOR_CHARS = "+-*/()=";
    private static final TokenType[] OPERATOR_TOKENS = {
            TokenType.PLUS, TokenType.MINUS,
            TokenType.MUL, TokenType.DIV,
            TokenType.LPAREN, TokenType.RPAREN,
            TokenType.EQUAL,
    };

    public Lexer(String input) {
        this.input = input;
        this.length = input.length();
        tokens = new ArrayList<>();
    }

    public List<Token> tokenize(){
        while (pos < length) {
            final char current  = peek(0);
            if (Character.isDigit(current)) tokenizeNumber();
            else if (Character.isLetter(current)) tokenizeWord();
            else if (OPERATOR_CHARS.indexOf(current) != -1) {
                tokenizeOperator();
            } else {
                //  пробел
                next();
            }
        }
        return tokens;
    }

    private void tokenizeOperator() {
        final int position = OPERATOR_CHARS.indexOf(peek(0));
        addToken(OPERATOR_TOKENS[position]);
        next();
    }

    private void tokenizeNumber() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true) {
            if (current == '.') {
                if (buffer.indexOf(".") != -1) throw new RuntimeException("Invalid float number");
            } else if (!Character.isDigit(current)) {
                break;
            }
            buffer.append(current);
            current = next();
        }
        if (buffer.toString().equals("print")){
            addToken(TokenType.PRINT);
        }else {
            addToken(TokenType.NLITERAL, buffer.toString());
        }
    }

    private void tokenizeWord() {
        final StringBuilder buffer = new StringBuilder();
        char current = peek(0);
        while (true) {
            if (!Character.isLetterOrDigit(current) && (current != '_')  && (current != '$')) {
                break;
            }
            buffer.append(current);
            current = next();
        }
        addToken(TokenType.STRLITERAL, buffer.toString());
    }


    private char peek(int relativePosition) {
        final int position = pos + relativePosition;
        if (position >= length) return '\0';
        return input.charAt(position);
    }

    private char next() {
        pos++;
        return peek(0);
    }

    private void addToken(TokenType type) {
        addToken(type," ");
    }

    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme));
    }
}
