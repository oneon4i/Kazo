package com.nswlab.parser.lexer.Token;

public class Token {
    public TokenType type; // type of token
    public String input;   // code in our language
    public String lexeme;  // типа KEYWORD(lexeme)

    public Token() {}

    public Token(TokenType type, String input, String lexeme){
        this.type = type;
        this.input = input;
        this.lexeme = lexeme;
    }

    public Token(TokenType type, String input) {
        this.type = type;
        this.input = input;
    }


    // Getters & Setters
    public TokenType getType(){
        return this.type;
    }

    public void setType(TokenType type){
        this.type = type;
    }

    public String getInput(){
        return this.input;
    }

    public void setInput(String input){
        this.input = input;
    }

    public String getLexeme(){
        return this.lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    @Override
    public String toString() {
        return type + " " + input + " " + lexeme;
    }
}
