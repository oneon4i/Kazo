package com.nswlab.parser.lexer.Token;

public class Token {
    public TokenType type; // type of token
    public String lexeme;  // типа KEYWORD(lexeme)

    public Token() {}

    public Token(TokenType type, String lexeme){
        this.type = type;
        this.lexeme = lexeme;
    }

    public Token(TokenType type) {
        this.type = type;
    }


    // Getters & Setters
    public TokenType getType(){
        return this.type;
    }

    public void setType(TokenType type){
        this.type = type;
    }

    public String getLexeme(){
        return this.lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return type + " " + lexeme + " ";
    }
}
