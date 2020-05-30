package encodingstrategies;

public class AtBashEncoding extends TemplateEncoding {

	String alphaArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public String encode(String value) {
		return super.encode(value);
	}
	
	@Override
	protected char mapCharacter(char c) {

		for (int i = 0; i < alphaArray.length(); i++) {
			if (Character.isLowerCase(c)) {
				if (Character.toUpperCase(c) == alphaArray.charAt(i))
					return Character.toLowerCase(alphaArray.charAt(alphaArray.length()-1 -i));
			}
			else if (Character.isUpperCase(c))
			{
				if (c == alphaArray.charAt(i))
					return (alphaArray.charAt(alphaArray.length()-1 -i));
			}
		}
		return c;
	}

	public String toString() {
		return "atbash";
	}
}
