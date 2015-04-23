
public class Parser 
{
	private String theStmt;
	private int pos;  //where am I in the theStmt string

	public Parser(String theStmt)
	{
		this.theStmt = theStmt;
		this.pos = 0;
	}
	
	void parse()
	{
		this.parse_stmt();
	}
	
	private void parse_stmt()
	{
		for(i = 0; i < this.theStmt.length(); i++)
		{
			if(this.theStmt.charAt(i) == "a" || "b")
			{
				System.out.println("Left variable: " + this.theStmt.charAt(i));
			}
			else if(this.theStmt.charAt(i) == "a" || "b" || "c")
			{
				System.out.println("Right variable:" + this.theStmt.charAt(i));
			}
		}
	
		//Print each time it reads something like:
		// Read: VarName = a
		// Reading: Math-Expr
		
		//read a var name
		//read a math_expr
	}
	
	private void parse_math_expr()
	{
		for(i = 0; i < this.theStmt.length(); i++)
		{
			if(this.theStmt.charAt(i) == "+" )
			{
				System.out.println("Math Expression: +");
			}
			else if(this.theStmt.charAt(i) == "-")
			{
				System.out.println("Math Expression: -");
			}
			else if(this.theStmt.charAt(i) == "*")
			{
				System.out.println("Math Expression: *");
			}
			else if(this.theStmt.charAt(i) == "/")
			{
				System.out.println("Math Expression: /");
			}
		//Display
		//Reading Left:
		//Read OP = *
		//Reading Right
		
		//read left
		//read op
		//read right
	}
}
