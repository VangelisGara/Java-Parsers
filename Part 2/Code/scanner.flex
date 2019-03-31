import java_cup.runtime.*;

%%
/* ----------------- Options and Declarations Section ----------------- */

// The Yylex class will be named Scanner
%class Scanner

// Current line and number
%line
%column

// Enable compatibility with CUP parser
%cup
%unicode

// Copy to lexer class
%{
    StringBuffer string = new StringBuffer(); // to get string tokens
    // create java_cup.runtime.Symbol objects
    private Symbol symbol(int type) {
       return new Symbol(type, yyline, yycolumn);
    }
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

// The regular expressions
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]
Identifier = [A-Za-z_$] [A-Za-z_$0-9]*

// state declarations
%state STRING

%%
/* ------------------------ Lexical Rules Section ---------------------- */
<YYINITIAL> {
 "+"            { return symbol(sym.PLUS); }
 ","            { return symbol(sym.COMMA);}
 "("            { return symbol(sym.LPAREN); }
 ")"            { return symbol(sym.RPAREN); }
 "{"            { return symbol(sym.LBRACK); }
 "}"            { return symbol(sym.RBRACK); }
 "prefix"       { return symbol(sym.PREFIX); }
 "suffix"       { return symbol(sym.SUFFIX); }
 "if"           { return symbol(sym.IF); }
 "else"         { return symbol(sym.ELSE); }
 \"             { string.setLength(0); yybegin(STRING); }
 {WhiteSpace}   { /* Ignore WhiteSpace */ }
 {Identifier}   { return symbol(sym.IDENT,new String(yytext())); }
}

<STRING> {
 \"                             { yybegin(YYINITIAL);
                                  return symbol(sym.STRING_LITERAL,
                                  string.toString()); }
 [^\n\r\"\\]+                   { string.append( yytext() ); }
 \\t                            { string.append('\t'); }
 \\n                            { string.append('\n'); }

 \\r                            { string.append('\r'); }
 \\\"                           { string.append('\"'); }
 \\                             { string.append('\\'); }
}

/* No token was found for the input so throw an error.  Print out an
   Illegal character message with the illegal character that was found. */
[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
