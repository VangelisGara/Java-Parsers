# Generated Parsers

Implementing a parser with the help of &nbsp;*JFLEX*&nbsp;  for the lexer and  &nbsp;*CUP*&nbsp; for the parsing. The parser will get as input for a language that  supports the concatenation operator over strings, function definitions and calls, conditionals (if-else i.e, every "if" must be followed by an "else"), and the following logical expressions:

    is-prefix-of (string1 prefix string2): Whether string1 is a prefix of string2.
    is-suffix-of (string1 suffix string2): Whether string1 is a suffix of string2.

All values in the language are strings.

The parser, based on a context-free grammar, will translate the input language into Java that can be compiled and executed with JDK.

### Example
##### Input
&nbsp;
    
    name()  {
        "John"
    }
    
    surname() {
        "Doe"
    }
    
    fullname(first_name, sep, last_name) {
        first_name + sep + last_name
    }

        name()
        surname()
        fullname(name(), " ", surname())


##### Output
&nbsp;

    public class Main {
        public static void main(String[] args) {
            System.out.println(name());
            System.out.println(surname());
            System.out.println(fullname(name(), " ", surname()));
        }
    
        public static String name() {
            return "John";
        }
    
        public static String surname() {
            return "Doe";
        }
    
        public static String fullname(String firstname, String sep, String last_name) {
            return first_name + sep + last_name;
        }
    }
    
    
### To compile
> make compile


### To execute

> make execute < *INPUT FILE* > *OUTPUT FILE*

**The output file should be in another folder !**



