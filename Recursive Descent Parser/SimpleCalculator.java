import java.io.InputStream;
import java.io.IOException;

public class SimpleCalculator{
  private int lookaheadToken; // the lookahead token of our parser
  private InputStream in; // input given

  // Constructor
  public SimpleCalculator(InputStream in) throws IOException{
      this.in = in ; // initiallize input
      lookaheadToken = in.read(); // initiallize loak ahead token
  }

  // Consume desirable symbol
  private void consume(int symbol) throws IOException, ParseError {
      if (lookaheadToken != symbol)
          throw new ParseError();
      lookaheadToken = in.read();
  }

  // Digit Converter
  private int intToCharacter(int digit){
      return digit - '0';
  }

  // Start Parsing and Evaluating the answer
  public int StartEvaluation() throws IOException, ParseError {
      int result = Goal(); // Get first rule
      if (lookaheadToken != '\n' && lookaheadToken != -1)
          throw new ParseError(); // there is more input left
      return result;
  }

  // Production Rule : Goal -> Expr
  private int Goal() throws IOException,ParseError{
      return Expr();
  }

  // Production Rule : Expr -> Term Expr2
  private int Expr() throws IOException,ParseError {
      int temp = Term();
      return Expr2(temp);
  }

  // Production Rule : Expr2 -> ^ Term Expr2 | empty
  private int Expr2(int cond) throws IOException,ParseError{
      // RestExpr -> + Term RestExpr | - Term RestExpr | empty
      if (lookaheadToken == '\n' || lookaheadToken == -1)
          return cond;
      if ( lookaheadToken == '^'){
          consume('^');
          int cond1 = Term();
          return cond ^ Expr2(cond1);
      }
      return cond;
  }

  // Production Rule : Term -> Factor Term2
  private int Term() throws IOException,ParseError{
      int temp = Factor();
      return Term2(temp);
  }

  // Production Rule : Term2 -> & Factor Term2 | empty
  private int Term2(int cond) throws IOException,ParseError{
      if (lookaheadToken == '\n' || lookaheadToken == -1)
          return cond;
      if ( lookaheadToken == '&'){
          consume('&');
          int cond1 = Factor();
          return cond & Term2(cond1);
      }
      return cond;
  }

  // Production Rule: Factor -> ( Expr ) | Num
  private int Factor() throws IOException,ParseError{
      int cond;
      if (lookaheadToken == '(') { // move into parenthesis
          consume(lookaheadToken);
          cond = Expr();
          if (lookaheadToken == ')') {
              consume(lookaheadToken);
              return cond;
          } else {
              throw new ParseError();
          }
      }
      cond = Num();
      return cond;
  }

  // Production Rule: Num -> 0|1|2|3|4|5|6|7|8|9
  public int Num() throws IOException,ParseError {
      if ( lookaheadToken < '0' || lookaheadToken >'9') // accept only single digit numbers
        throw new ParseError();
      int num = intToCharacter(lookaheadToken);
      consume(lookaheadToken);
      return num;
  }

  // Our main function
  public static void main(String[] args) {
    System.out.println("Ctrl + Z to exit...");
    try {
        while(true){
          System.out.println("Please give input");
          SimpleCalculator SuperDuperCalculator = new SimpleCalculator(System.in);
          int EvaluationResult = SuperDuperCalculator.StartEvaluation();
          System.out.println("Answer = " + EvaluationResult + "\n\n");
        }
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
    catch(ParseError err){
      System.err.println(err.getMessage());
    }
  }
}
