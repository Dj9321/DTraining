import java.util.regex.Pattern;

public class MainClassOne {

	public static void main(String[] args) {

		// Following line prints "true" because the whole
		// text "geeksforgeeks" matches pattern
		// "geeksforge*ks"
		System.out.println(Pattern.matches("geeksforge*ks", "geeksforgeeks"));

		// Following line prints "false" because the whole
		// text "geeksfor" doesn't match pattern "g*geeks*"
		System.out.println(Pattern.matches("g*geeks*", "geeksfor"));

//		System.out.println(Pattern.matches("(\\\w+).* \\\"lastName\":\\\(\\\\w+).*", "abc lastName:abc"));
		System.out.println("here: ");
		System.out.println(Pattern.matches("(\\w+)(\\s)(\\w+)(\\s+).*", "ss ds aasdfasdf safd"));
		System.out.println(Pattern.matches(".", " "));
		System.out.println(Pattern.matches("g*geeks*", "geeksfor"));

	}

}
