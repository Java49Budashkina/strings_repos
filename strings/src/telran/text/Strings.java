package telran.text;

import java.util.HashMap;
import java.util.function.BinaryOperator;

public class Strings {
	static HashMap<String, BinaryOperator<Double>> mapOperations;
	static {
		mapOperations = new HashMap<>();
		mapOperations.put("-",(a,b) -> a - b);
		mapOperations.put("+",(a,b) -> a + b);
		mapOperations.put("*",(a,b) -> a * b);
		mapOperations.put("/",(a,b) -> a / b);
	}

//	static HashMap<String, Double> mapOperands;
//	static {
//		mapOperands = new HashMap<>();
//		mapOperands.put("a", 100.4);
//		mapOperands.put("b", 22.5);
//		mapOperands.put("c", 3.);
//		mapOperands.put("d", 18.9);
//	}
	
public static String  javaVariableName() {
	return "([a-zA-Z$][\\w$]*|_[\\w$]+)";
}

public static String  zero_300() {
	return "[1-9]\\d?|[1-2]\\d\\d?|300|0";
}

public static String  ipV4octet() {
	return "([0-1]\\d?\\d?|[2-9]\\d?|2[0-4]\\d|25[0-5])";
	
}

public static String  ipV4() {
	return   ipV4octet() + "\\." + ipV4octet() + "\\."+ ipV4octet() + "\\."+ ipV4octet();

}

public static String  ipV4_f() {
	return   String.format("(%1$s\\.){3}%1$s", ipV4octet());

}

public static String  arithm() {

	return "(-)?\\d+\\s*([-+*/]\\s*\\d+){1,}";

}

public static String  arithmExpression() {  // name is not correct
	String operandRE = operand();
	String operatorRE = operator();
	String variableName = javaVariableName();

	return String.format("(-)?(%1$s|%3$s)(%2$s(%1$s|%3$s))*", operandRE, 
			operatorRE, variableName);

}

private static String operator() {
	return "\\s*([-+*/])\\s*";
	
	
}

private static String operand() {
	//return "(\\d+)";
	return "(\\d+(\\.)?\\d*)";
}

public static boolean isArithmeticExpression(String sss){
	 sss = sss.trim();  
//	System.out.print(sss + "\n");
	boolean res = sss.matches(arithmExpression());
	return res;
	
}

public static double computeExpression(String expression) {

	if (!isArithmeticExpression(expression)) {
		throw new IllegalArgumentException("Wrong arithmetic expression");
	}
	expression = expression.replaceAll("\\s+", ""); 
	
	String[] operands = expression.split(operator());
	String [] operators = expression.split(operand());
	Double res = Double.parseDouble(operands[0]);
	for(int i = 1; i < operands.length; i++) {
		double operand = Double.parseDouble(operands[i]);
		res = mapOperations.get(operators[i]).apply(res, operand);
	}
	System.out.print(expression +" = "+ res +"\n");
	return res.doubleValue();
		
}

private static double getValueOperand(String operand, 
		HashMap<String, Double> mapVariables) {
	double res =0;

	if (operand.matches(javaVariableName())){
		if( !mapVariables.containsKey(operand)) {
			throw new IllegalArgumentException("Wrong variable name");
		}
		
		res = mapVariables.get(operand);
	}
	else
	 res = Double.parseDouble(operand);
	
	return res;
	
}
//Update whole code for any numbers (double)
//Update code taking into consideration possible variable names
public static double computeExpression(String expression,
		HashMap<String, Double> mapVariables) {

	if (!isArithmeticExpression(expression)) {
		throw new IllegalArgumentException("Wrong arithmetic expression");
	}
	expression = expression.replaceAll("\\s+", ""); 
	String[] operands = expression.split(operator());
	String regex = String.format("(%s|%s)", operand(), javaVariableName() );
	String [] operators = expression.split(regex);
	
	Double res = getValueOperand(operands[0], mapVariables);
	for(int i = 1; i < operands.length; i++) {
		double operand = getValueOperand(operands[i], mapVariables);
		res = mapOperations.get(operators[i]).apply(res, operand);
	}
	System.out.print(expression +" = "+ res +"\n");
	return res.doubleValue();

}
}
