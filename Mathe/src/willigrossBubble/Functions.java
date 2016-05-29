package willigrossBubble;


public class Functions {
	
	public static boolean isExpressionValid(String expression) {
		
		if (expression.matches("[x\\d\\s+-\\\\*/()\\\\^\\\\.]+"))
			return true;
		return false;
	}
	
}
