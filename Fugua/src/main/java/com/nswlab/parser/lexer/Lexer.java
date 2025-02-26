package com.nswlab.parser.lexer;

import com.nswlab.parser.lexer.Token.*;
import java.util.*;

/*
Fugue lexer part.
 */
public class Lexer {
    // инпут
    private final String input;
    // список токенов
    private final List<Token> tokens = new ArrayList<>();
    private final HashMap<String, TokenType> keywords = new HashMap<>(){{
        put("if", TokenType.IF);
        put("else", TokenType.ELSE);
    }};
    // текущая позиция
    private int current = 0;
    private int line = 0;

    // конструктор
    public Lexer(String input) {
        this.input = input;
    }

    // возвращает список токенов
    public List<Token> scan(){
        while (!isAtEnd()) {
            char c = advance();
            switch (c) {
                case '+': addToken(TokenType.ADD, "+"); break;
                case '-': addToken(TokenType.SUB, "-"); break;
                case '*': addToken(TokenType.MUL, "*"); break;
                case '/': addToken(TokenType.DIV, "/"); break;
                case ',': addToken(TokenType.COMMA, ","); break;
                case '.': addToken(TokenType.DOT, "."); break;
                case '(': addToken(TokenType.LEFT_PAREN, "("); break;
                case ')': addToken(TokenType.RIGHT_PAREN, ")"); break;
                case '{': addToken(TokenType.LEFT_BRACE, "{"); break;
                case '}': addToken(TokenType.RIGHT_BRACE, "}"); break;
                case '\t' | '\r': break;
                case '\n': line += 1; break;
                default: {
                    if (Character.isDigit(c)) {
                        addToken(parseNumber(c));
                    } else if (Character.isLetter(c)) {
                        addToken(parseIdentifier(c));
                    } else {
                        throw new RuntimeException(
                                "Unexpected character at line: "+ line + " -> " + c
                        );
                    }
                }
            }
        }
        return tokens;
    }

    // парсинг идентификатора
    private Token parseIdentifier(char start) {
        StringBuilder text = new StringBuilder(start);
        // !TODO добавить проверку на две точки в числе
        while (Character.isAlphabetic(peek()) || peek() == '_') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            text.append(advance());
            if (isAtEnd()) {
                break;
            }
        }
        // продолжение
        TokenType type = keywords.getOrDefault(text.toString(), TokenType.IDENTIFIER);
        return new Token(type, text.toString(), line);
    }

    // парсинг строки
    private Token parseString(char start) {
        StringBuilder text = new StringBuilder(String.valueOf(start));
        // !TODO добавить проверку на две точки в числе
        while (peek() != '"') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            text.append(advance());
            if (isAtEnd()) {
                break;
            }
        }
        advance();
        // продолжение
        return new Token(TokenType.STRING, text.toString(), line);
    }

    // парсинг строки
    private Token parseNumber(char start) {
        StringBuilder text = new StringBuilder(start);
        // !TODO добавить проверку на две точки в числе
        while (Character.isDigit(peek()) || peek() == '.') {
            if (match('\n')) {
                line += 1;
                continue;
            }
            text.append(advance());
            if (isAtEnd()) {
                break;
            }
        }
        // продолжение
        return new Token(TokenType.NUMBER, text.toString(), line);
    }

    // заглядываем и возвращаем символ
    private char peek() {
        if (isAtEnd()) return '\0';
        else {
            return input.charAt(current);
        }
    }

    // забираем символ
    private char advance() {
        return input.charAt(current++);
    }

    // токен на следующей позиции
    private char next() {
        if (isAtEnd()) return '\0';
        return input.charAt(current+1);
    }

    // проверка на символ
    private boolean match(char forMatch) {
        if (isAtEnd()) return false;
        if (input.charAt(current) == forMatch) {
            current++;
            return true;
        } else {
            return false;
        }
    }

    // в конце ли кода
    private boolean isAtEnd() {
        return current >= input.length();
    }

    // добавление токена
    private void addToken(TokenType type, String value) {
        tokens.add(new Token(type, value, line));
    }

    // добавление токена
    private void addToken(Token tk) {
        tokens.add(tk);
    }
}
