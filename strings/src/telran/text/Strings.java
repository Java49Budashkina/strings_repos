package telran.text;

public class Strings {
	
public static String  javaVariableName() {
	return "[a-zA-Z$][\\w$]*|_[\\w$]+";
}

public static String  zero_300() {
	return "[1-9]\\d?|[1-2]\\d\\d?|300|0";
}

public static String  ipV4octet() {
	return "([0-1]\\d?\\d?|[2-9]\\d?|2[0-4]\\d|25[0-5])";
	
}

public static String  ipV4() {
	return   ipV4octet() + "\\." + ipV4octet() + "."+ ipV4octet() + "."+ ipV4octet();

}

}
