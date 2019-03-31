# Recursive Descent Parser

Implementing a hand-written recursive descent parser and evaluator of a simple bitwise AND(&), XOR(^) calculator .

### The Grammar , LL(1)
**\#**  **Production Rule** tl;dr
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
FIRST+(goal) = { (,0,1,...,9 }
FIRST+(expr) = { (,0,1,...,9 }
FIRST+(term) = { (,0,1,...,9 }
FIRST+(factor) = { (,0,1,...,9 }
FIRST+(epxr2) = { ^,),EOF }
FIRST(term2) = { &,^,),EOF }
FIRST+(num) = { 0,1,...,9 }

### Follow Sets
FOLLOW (goal) = { EOF }
FOLLOW (expr) = { ), EOF }
FOLLOW (epxr2) = { ), EOF }
FOLLOW (term) = { ^,), EOF }
FOLLOW (term2) = { &,^,), EOF }
FOLLOW (factor) = { &,^,), EOF }
FOLLOW (num) = {  &,^,), EOF }

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
