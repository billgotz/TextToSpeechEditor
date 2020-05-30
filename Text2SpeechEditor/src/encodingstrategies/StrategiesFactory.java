package encodingstrategies;

public class StrategiesFactory {

	public EncodingStrategy createStrategy(String strategy)
	{
		if (strategy.equals("rot13"))
			return new Rot13Encoding();
		else if (strategy.equals("atbash"))
			return new AtBashEncoding();

		System.out.println(strategy + ": Wrong argument for StrategiesFactory");
		return null;
	}
	
}
