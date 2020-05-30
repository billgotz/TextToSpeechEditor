package model;

import encodingstrategies.EncodingStrategy;
import text2speechapis.TextToSpeechAPI;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Document {

	private String title;
	private String author;
	private LocalDateTime creationDate;
	private LocalDateTime saveDate;
	private Line line;
	private File linkedFile;
	private ArrayList<Line> contents;
	private final TextToSpeechAPI audioManager;
	private EncodingStrategy strategy;

	public Document(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}

	public void playContents() {
		for (Line line : contents) {
			for (String words : line.getWords()) {
				audioManager.play(words);
			}
		}
	}
	
	public void playReverseContents() {
		for (int i = contents.size() -1; i >= 0; i--) {
			ArrayList<String> words = contents.get(i).getWords();
			for (int j = words.size() - 1; j >= 0; j--) {
				audioManager.play(words.get(j));
			}
		}
	}

	public void playLine(int line) {
		this.line = contents.get(line);
		this.line.playLine();
	}

	public void playReverseLine(int line) {
		this.line = contents.get(line);
		this.line.playReverseLine();
	}

	public void playEncodedContents() {
		for (Line line : contents) {
			for (String words : line.getWords()) {
				audioManager.play(strategy.encode(words));
			}
		}
	}

	public void playEncodedLine(int line) {
		this.line = contents.get(line);
		this.line.tuneEncodingStrategy(strategy);
		this.line.playEncodedLine();
	}

	public void tuneEncodingStrategy(EncodingStrategy es) {
		strategy = es;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public ArrayList<Line> getContents() {
		return contents;
	}

	public void setContents(ArrayList<Line> contents) {
		this.contents = contents;
	}

	public File getLinkedFile() {
		return linkedFile;
	}

	public void setLinkedFile(File linkedFile) {
		this.linkedFile = linkedFile;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(LocalDateTime saveDate) {
		this.saveDate = saveDate;
	}


}
