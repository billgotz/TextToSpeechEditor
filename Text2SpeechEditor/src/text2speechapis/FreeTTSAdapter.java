package text2speechapis;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAdapter implements TextToSpeechAPI {

	private VoiceManager vm;
	private Voice voice;
	private float volume;
	private float rate;
	private float pitch;

	public FreeTTSAdapter() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		vm = VoiceManager.getInstance();
		voice = vm.getVoice("kevin16");

		volume = voice.getVolume();
		rate = voice.getRate();
		pitch = voice.getPitch();
	}
	
	@Override
	public void play(String play) {
		voice.allocate();
		voice.speak(play);
	}

	@Override
	public void setVolume(float volumeSize) {
		volume = volumeSize;
		voice.setVolume(volumeSize);
	}

	@Override
	public void setPitch(float pitchSize) {
		pitch = pitchSize;
		voice.setPitch(pitchSize);
	}

	@Override
	public void setRate(float rateSize) {
		rate = rateSize;
		voice.setRate(rateSize);
	}

	public float getVolume() {
		return volume;
	}

	public float getPitch() {
		return pitch;

	}

	public float getRate() {
		return rate;

	}

}
