# Recursive Descent Parser

Implementing a hand-written recursive descent parser and evaluator of a simple bitwise AND(&), XOR(^) calculator .

### The Grammar , LL(1)
**\#**  **Production Rule** <br/><br/>
1       &nbsp;&nbsp;&nbsp;goal -> expr
2       &nbsp;&nbsp;&nbsp;expr -> term expr2
3       &nbsp;&nbsp;&nbsp;expr2 -> ^ term expr2
4       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;ε
5       &nbsp;&nbsp;&nbsp;term -> factor term2
6       &nbsp;&nbsp;&nbsp;term2 -> & factor term2
7       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;ε
8       &nbsp;&nbsp;&nbsp;factor -> num
9       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;| ( expr )
10 &nbsp;&nbsp;&nbsp; num -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10


### First+ Sets
FIRST+(goal) = { (,0,1,...,9 } <br/><br/>
FIRST+(expr) = { (,0,1,...,9 } <br/><br/>
FIRST+(term) = { (,0,1,...,9 } <br/><br/>
FIRST+(factor) = { (,0,1,...,9 } <br/><br/>
FIRST+(epxr2) = { ^,),EOF } <br/><br/>
FIRST(term2) = { &,^,),EOF } <br/><br/>
FIRST+(num) = { 0,1,...,9 } <br/><br/>

### Follow Sets
FOLLOW (goal) = { EOF } <br/><br/>
FOLLOW (expr) = { ), EOF } <br/><br/>
FOLLOW (epxr2) = { ), EOF } <br/><br/>
FOLLOW (term) = { ^,), EOF } <br/><br/>
FOLLOW (term2) = { &,^,), EOF } <br/><br/>
FOLLOW (factor) = { &,^,), EOF } <br/><br/>
FOLLOW (num) = {  &,^,), EOF } <br/><br/>

### Lookahead Table
|        | '0-9' | ^  | &  | (  | )  | $   |
|--------|-------|----|----|----|----|-----|
| Goal   | #1    |    |    | #1 |    |     |
| Expr   | #2    |    |    | #2 |    |     |
| Expr2  |       | #3 |    |    | #4 | #4  |
| Term   | #5    |    |    | #5 |    |     |
| Term2  |       | #7 | #6 |    | #7 | #7  |
| Factor | #8    |    |    | #9 |    |     |
| Num    | #10   |    |    |    |    |     |

### To Compile & Execute
>javac SimpleCalculator.java
>java SimpleCalculator

### Inputs
On the fly or from test cases file
