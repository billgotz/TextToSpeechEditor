package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Document;
import model.Line;
import view.Text2SpeechEditorView;

public class SaveDocument implements ActionListener {

	Text2SpeechEditorView editor;
	Document currentDocument;

	public SaveDocument() {
		editor  = Text2SpeechEditorView.getSingletonView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		currentDocument  = editor.getCurrentDocument();

		if (currentDocument != null)
		{
			JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir") + "\\src\\testfiles");

			int result = fileChooser.showSaveDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {

				File sFile = fileChooser.getSelectedFile();
				if (!sFile.getName().toLowerCase().endsWith(".txt"))
					sFile = new File(sFile.getParentFile(), sFile.getName() + ".txt");

				currentDocument.setLinkedFile(sFile);
				File linkedFile = currentDocument.getLinkedFile();

				try {
					if (linkedFile.createNewFile()) {
						createFile(linkedFile);
					}
					else {
						Object[] options = { "OK", "CANCEL" };
						int option = JOptionPane.showOptionDialog(null, "File " +sFile +" already exists. "
										+ "Are you sure you want to overwrite?", "File already exists",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
								null, options, options[0]);

						if (option == 0) {
							createFile(linkedFile);
						}else
							System.out.println("Cancel clicked");
					}

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(fileChooser, "Save Document operation cancelled!");
		}
		else
			JOptionPane.showMessageDialog(null, "Open or Create a document first");
	}
	
	private void createFile(File sFile) throws IOException {
		System.out.println("OK clicked");

		LocalDateTime currentTime = LocalDateTime.now();
		currentDocument.setSaveDate(currentTime);

		// Create a file writer 
		FileWriter wr = new FileWriter(sFile, false); 
		// Create buffered writer to write 
		BufferedWriter w = new BufferedWriter(wr); 
		// Write 
		w.write("Title: " + currentDocument.getTitle() + '\n');
		w.write("Author: " + currentDocument.getAuthor() + '\n');
		w.write("Creation Date: " + currentDocument.getCreationDate() + '\n');
		w.write("Last save Date: " + currentDocument.getSaveDate() + '\n');
		w.write(editor.getTxtArea().getText());
		w.flush();
		w.close();

		String[] splitLines = editor.getTxtArea().getText().split("\n");
		ArrayList<Line> contents = new ArrayList<>();

		for (String s: splitLines) {
			ArrayList<String> words = new ArrayList<>(Arrays.asList(s.split(" ")));
			Line line = new Line(editor.getAudioManager());
			line.setWords(words);
			contents.add(line);
		}

		currentDocument.setContents(contents);
		editor.getMainFrame().setTitle(sFile.getName());
		editor.setCurrentDocument(currentDocument);
		editor.getTxtArea().setEditable(false);
	}
	
}
