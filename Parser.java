public class Parser 
{
	private String theStmt;
	private int pos; //where am I in the theStmt string
	private static final String legalVariableCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ "; 
	private static final String legalLiteralCharacter = "0123456789 ";
	private static final String legalSymbolCharacters = Parser.legalVariableCharacters + Parser.legalLiteralCharacter;
	private static final String legalOpCharacters = "+-*/% ";
	private VarDefStatement theSytaxTree;
	public VariableEnvironment[] = new VariableEnvironment[10];
	private VarName VarName;
	private Value Value;
	
	public Parser(String theStmt)
	{
		this.theStmt = theStmt;
		this.theSytaxTree = null;
		this.pos = 0;
	}
	
	public VarDefStatement getTheSytaxTree() {
		return theSytaxTree;
	}

	void parse()
	{
		this.theSytaxTree = this.parse_stmt();
	}
	
	private String getNextToken(char c)
	{
		while(pos < this.theStmt.length())
		{
			if(this.theStmt.charAt(pos) == c)
			{
				pos++;
				break;
			}
			pos++;
		}
		return "" + c;
	}
	
	private String getNextToken(String legalChars)
	{
		String token = "";
		while(pos < this.theStmt.length())
		{
			if(legalChars.indexOf(this.theStmt.charAt(pos)) != -1)
			{
				token += this.theStmt.charAt(pos);
			}
			else
			{
				//this means we are at the end of the token
				//We are always trimming leading and trailing spaces
				//move forward one
				break;
			}
			pos++;
		}
		return token.trim();
	}
	private VariableEnviornment VE(char VarName, Int Value)
	{
		this.VarName = VarName;
		this.value = Value
	}
	private VarDefStatement parse_stmt()
	{
		//Print each time it reads something like:
		// Read: VarName = a
		String varName = this.getNextToken(Parser.legalVariableCharacters);
		System.out.println("Read VarName: " + varName);
		VarExpression theVE = new VarExpression(varName);
		
		//burn past the =
		this.getNextToken('=');
		System.out.println("Burned =");
		
		// Reading: Math-Expr
		MathExpression theME = this.parse_math_expr();
		System.out.println("The Right Side Math is: " + theME.doMath());
		
		//burn past the ;
		this.getNextToken(';');
		System.out.println("Burned ;");
		
		return new VarDefStatement(theVE, theME);
	}
	
	private boolean isVarExpression(String symbol)
	{
		for(int i = 0; i < symbol.length(); i++)
		{
			if(Parser.legalVariableCharacters.indexOf(symbol.charAt(i)) == -1 || symbol.charAt(i) == ' ')
			{
				return false;
			}
		}
		return true;
	}
	
	private MathExpression parse_math_expr()
	{
		String symbol = this.getNextToken(Parser.legalSymbolCharacters);
		Expression leftOperand = null;
		Expression rightOperand = null;
		OpExpression theOpExpression = null;
		
		if(symbol.length() == 0)
		{
			//we know that we are at the beginning of a paren-math-expr
			this.getNextToken('(');
			System.out.println("Burned (");
			leftOperand = this.parse_math_expr();
			this.getNextToken(')');
			System.out.println("Burned )");
		}
		else
		{
			//is it a var or a lit expression?
			if(this.isVarExpression(symbol))
			{
				System.out.println("Read VarExpression: " + symbol);
				leftOperand = new VarExpression(symbol);
			}
			else
			{
				System.out.println("Read LitExpression: " + symbol);
				leftOperand = new LitExpression(Integer.parseInt(symbol));
			}
		}
		String op = this.getNextToken(Parser.legalOpCharacters);
		System.out.println("Read Op: " + op);
		theOpExpression = new OpExpression(op.charAt(0));
		
		symbol = this.getNextToken(Parser.legalSymbolCharacters);
		if(symbol.length() == 0)
		{
			//we know that we are at the beginning of a paren-math-expr
			this.getNextToken('(');
			rightOperand = this.parse_math_expr();
			this.getNextToken(')');
		}
		else
		{
			//is it a var or a lit expression?
			if(this.isVarExpression(symbol))
			{
				System.out.println("Read VarExpression: " + symbol);
				rightOperand = new VarExpression(symbol);
			}
			else
			{
				System.out.println("Read LitExpression: " + symbol);
				rightOperand = new LitExpression(Integer.parseInt(symbol));
			}
			else if
			{
				System.out.println("Read VariableEnvironment: " + symbol);
				rightOperand = new VariableEnvironment(Integer.parseInt(symbol));
				
			}
		}
		return new MathExpression(leftOperand, rightOperand, theOpExpression);
	}
}