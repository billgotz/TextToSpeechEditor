package encodingstrategies;

public abstract class TemplateEncoding implements EncodingStrategy {

	@Override
	public String encode(String value) {
		StringBuilder retBuilder = new StringBuilder();
		for (char c : value.toCharArray()) {
			c = mapCharacter(c);
			retBuilder.append(c);
		}
		return retBuilder.toString();
	}
	
	protected abstract char mapCharacter(char c);

	public abstract String toString();
}
