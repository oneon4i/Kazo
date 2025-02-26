package com.nswlab.parser.lexer.Token;

public enum TokenType {
    KEYWORD,       // if, else
    IDENTIFIER,    // var1
    PLUS,          // +
    MINUS,         // -
    MUL,           // *
    DIV,           // /
    EQUAL,         // ==
    NOT_EQUAL,     // !=
    LESS,          // <
    LESS_EQUAL,    // <=
    GREATER,       // >
    GREATER_EQUAL, // >=
    ASSIGN,        // =
    DOT,           // .
    PRINT,         // пока до стд либ
    NLITERAL,      // 5
    STRLITERAL,    // "строки"
    FLTLITERAL,    // 3.14
    SEMICOLON,     // ;
    LPAREN,        // (
    RPAREN,        // )
    LBRACE,        // {
    RBRACE,        // }
    EOF,           // EOF
}