package commands;

import model.Document;
import model.Line;
import view.Text2SpeechEditorView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NewDocument implements ActionListener {

	private boolean isTest = false;

	NewDocument() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();

		JFrame frame = new JFrame("Create a new document");
		JLabel title = new JLabel("Give a title: ");
		JTextField titleField = new JTextField();
		JLabel author = new JLabel("Give an author: ");
		JTextField authorField = new JTextField();

		JButton confirmButton = new JButton("Confirm");

		frame.add(title);
		frame.add(titleField);
		frame.add(author);
		frame.add(authorField);
		frame.add(confirmButton);

		frame.setLayout(new GridLayout(3,1));
	    frame.setVisible(true);
	    frame.setSize(500, 125);
	    frame.setLocationRelativeTo(null);

		confirmButton.addActionListener(e1 -> {

			Document currentDocument = new Document(editor.getAudioManager());

			if (titleField.getText().equals("")) currentDocument.setTitle("Untitled");
			else currentDocument.setTitle(titleField.getText());

			if (authorField.getText().equals("")) currentDocument.setAuthor("No author");
			else currentDocument.setAuthor(authorField.getText());

			currentDocument.setCreationDate(LocalDateTime.now());
			currentDocument.setContents(new ArrayList<Line>());

			editor.getMainFrame().setTitle(currentDocument.getTitle());
			editor.getTxtArea().setText("");
			editor.getTxtArea().setEditable(false);
			editor.setCurrentDocument(currentDocument);

			frame.dispose();
		});

		if (isTest)
			confirmButton.doClick();

	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}
}
