package encodingstrategies;

public class Rot13Encoding extends TemplateEncoding {

	final String alphaArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public Rot13Encoding() {

	}

	public String encode(String value) {
		return super.encode(value);
	}
	
	@Override
	protected char mapCharacter(char c) {

		for (int i = 0; i < alphaArray.length(); i++) {
			if (Character.isLowerCase(c)) {
				if (Character.toUpperCase(c) == alphaArray.charAt(i)) {
					if ( (i+14) > alphaArray.length()) {
						int position = 13 - (alphaArray.length() - i);
						return Character.toLowerCase(alphaArray.charAt(position));
					}
					else
						return Character.toLowerCase(alphaArray.charAt(i + 13));
				}
			}
			else if (Character.isUpperCase(c)) {
				if (c == alphaArray.charAt(i)) {
					if ( (i+14) > alphaArray.length()) {
						int position = 13 - (alphaArray.length() - i);
						return (alphaArray.charAt(position));
					}
					else
						return (alphaArray.charAt(i + 13));
				}
			}
		}
		return c;
	}

	public String toString() {
		return "rot13";
	}
}
