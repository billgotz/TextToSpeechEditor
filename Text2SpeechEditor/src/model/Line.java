package model;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;
import java.util.ArrayList;

public class Line {
	
	private ArrayList<String> words;
	private final TextToSpeechAPI audioManager;
	private EncodingStrategy strategy;

	public Line (TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}

    public void playLine() {
		for (String word : words) {
			audioManager.play(word);
		}
    }

	public void playReverseLine() {
		for (int j = words.size() - 1; j >= 0; j--) {
			audioManager.play(words.get(j));
		}
	}

	public void playEncodedLine() {
		for (String word : words) {
			audioManager.play(strategy.encode(word));
		}
	}

	public void tuneEncodingStrategy(EncodingStrategy es) {
		strategy = es;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

}
