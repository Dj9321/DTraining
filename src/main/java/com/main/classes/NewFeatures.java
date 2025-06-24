package com.main.classes;

public class NewFeatures {
	
	public static void main(String[] args) {
		NewFeatures n = new NewFeatures();
		n.textBlocksExample("Dheeraj");
	}
	
	public void textBlocksExample(String name) {
		var sentence = "hello";
		var textBlockSentence = """
				this is a textblock 
				sentence with %s space in between
				""";
		var textBlockSentenceWithIndentChange = """
				this is a textblock
				sentence with %s space in between
			""";
		
		var multiline = """
				 Replacing name: %s
				 in a multiline string
				 """.formatted(name);
		System.out.println(textBlockSentence);
		System.out.println(textBlockSentenceWithIndentChange);
		System.out.println(multiline);
	}

}
