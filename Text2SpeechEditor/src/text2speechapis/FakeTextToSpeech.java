package text2speechapis;

import java.util.ArrayList;

public class FakeTextToSpeech implements TextToSpeechAPI {

	private ArrayList<String> plays;
	private float volume;
	private float rate;
	private float pitch;

	FakeTextToSpeech() {
		plays = new ArrayList<>();
		this.volume = 1;
		this.rate = 150;
		this.pitch = 100;
	}

	@Override
	public void play(String play) {
		plays.add(play);
		System.out.println(play);
	}

	@Override
	public void setVolume(float volumeSize) {
		this.volume = volumeSize;
		// System.out.println("Volume: " + volumeSize);
	}

	@Override
	public void setPitch(float pitchSize) {
		this.pitch = pitchSize;
		// System.out.println("Pitch: " + pitchSize);

	}

	@Override
	public void setRate(float rateSize) {
		this.rate = rateSize;
		// System.out.println("Rate: " + rateSize);

	}

	@Override
	public float getVolume() {
		return this.volume;
	}

	@Override
	public float getPitch() {
		return this.pitch;
	}

	@Override
	public float getRate() {
		return this.rate;
	}

	public ArrayList<String> getPlays() {
		return plays;
	}
}
