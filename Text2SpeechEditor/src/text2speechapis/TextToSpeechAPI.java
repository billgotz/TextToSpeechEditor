package text2speechapis;

public interface TextToSpeechAPI {

	void play(String play);
	
	void setVolume(float volumeSize);
	
	void setPitch(float pitchSize);
	
	void setRate(float rateSize);

	float getVolume();

	float getPitch();

	float getRate();
	
}
