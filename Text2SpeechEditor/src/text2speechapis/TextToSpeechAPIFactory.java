package text2speechapis;

public class TextToSpeechAPIFactory {

	public TextToSpeechAPI createTTSAPI(String tts)
	{
		TextToSpeechAPI ttsAPI;
		switch (tts) {
			case("freeTTS"):
				ttsAPI = new FreeTTSAdapter();
				break;
			case ("fakeTTS"):
				ttsAPI = new FakeTextToSpeech();
				break;
			default:
				System.out.println(tts +": Wrong argument to the TTSAPIFactory");
				throw new IllegalStateException("Unexpected value: " + tts);
		}
		return ttsAPI;
	}

}
