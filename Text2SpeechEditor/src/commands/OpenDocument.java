package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Document;
import model.Line;
import view.Text2SpeechEditorView;

public class OpenDocument implements ActionListener {

	OpenDocument() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
		Document currentDocument = new Document(editor.getAudioManager());

		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\testfiles");
		int result = fileChooser.showOpenDialog(null);

		// If the user selects a file 
		if (result == JFileChooser.APPROVE_OPTION) {
			currentDocument.setLinkedFile(new File(fileChooser.getSelectedFile().getAbsolutePath()));

			try {
				// String
				String nextLine = "";
				StringBuilder fileLine;
				// File reader 
				FileReader fr = new FileReader(currentDocument.getLinkedFile());
				// Buffered reader 
				BufferedReader br = new BufferedReader(fr);

				fileLine = new StringBuilder();
				// Take the input from the file
				while ((nextLine = br.readLine()) != null) {

					// Get File title
					if (nextLine.contains("Title: ")) {
						//System.out.println(nextLine);
						String[] splitTitle = nextLine.split(":");
						currentDocument.setTitle(splitTitle[1]);
					}

					// Get File author
					else if (nextLine.contains("Author: ")) {
						//System.out.println(nextLine);
						String[] splitAuthor = nextLine.split(":");
						currentDocument.setAuthor(splitAuthor[1]);
					}

					//Get Creation Date
					else if (nextLine.contains("Creation Date: ")) {
						//System.out.println(nextLine);
						String[] splitFileCreation = nextLine.split(":", 2);
						if (splitFileCreation[1].trim().equals("null")) {
							currentDocument.setCreationDate(null);
						}else
							currentDocument.setCreationDate(LocalDateTime.parse(splitFileCreation[1].trim()));
					}

					//Get Last Save Date
					else if (nextLine.contains("Last save Date: ")) {
						//System.out.println(nextLine);
						String[] splitFileLastSave = nextLine.split(":",2);
						if (splitFileLastSave[1].trim().equals("null")) {
							currentDocument.setSaveDate(null);
						}else
							currentDocument.setSaveDate(LocalDateTime.parse(splitFileLastSave[1].trim()));
					}

					// Append the text to the String Builder if no data present in the start of file
					else
						fileLine.append(nextLine).append("\n");

				}


				editor.getMainFrame().setTitle(currentDocument.getLinkedFile().getName());
				editor.getTxtArea().setText(String.valueOf(fileLine));
				editor.getTxtArea().setEditable(false);

				String[] splitLines = editor.getTxtArea().getText().split("\n");
				ArrayList<String> words;
				Line line;
				ArrayList<Line> contents = new ArrayList<>();
				for (String s: splitLines) {
					words = new ArrayList<>(Arrays.asList(s.split(" ")));
					line = new Line(editor.getAudioManager());
					line.setWords(words);
					contents.add(line);
				}
				currentDocument.setContents(contents);
				editor.setCurrentDocument(currentDocument);
				
				br.close();
			}
			catch (Exception evt) { 
				JOptionPane.showMessageDialog(fileChooser, evt.getMessage());
			} 
		} 
		// If the user cancelled the operation 
		else
			JOptionPane.showMessageDialog(fileChooser, "Open Document operation cancelled!");
		
	}


}
