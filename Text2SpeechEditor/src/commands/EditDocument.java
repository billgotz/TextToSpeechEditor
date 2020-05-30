package commands;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import model.Line;
import view.Text2SpeechEditorView;

import javax.swing.*;

public class EditDocument implements ActionListener {

	private boolean isTest;

	public EditDocument() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
		JMenu stopEdit = new JMenu("Stop Editing");

		if (editor.getCurrentDocument() != null) {
			System.out.println("Editing...");

			editor.getTxtArea().setEditable(true);
			editor.getMBar().add(stopEdit);
			editor.getDocumentMenu().setEnabled(false);
			editor.getTxt2speechMenu().setEnabled(false);
			editor.getMainFrame().revalidate();

			stopEdit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					System.out.println("Stopped...");
					setContentsFromTextArea();
					editor.getMBar().remove(stopEdit);
					editor.getDocumentMenu().setEnabled(true);
					editor.getTxt2speechMenu().setEnabled(true);
					editor.getTxtArea().setEditable(false);
					editor.getMainFrame().repaint();
				}
			});

			if (isTest)
				setContentsFromTextArea();
		}
		else
			JOptionPane.showMessageDialog(null, "Open or Create a document first");
	}


	private void setContentsFromTextArea() {
		Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
		String[] splitLines = editor.getTxtArea().getText().split("\n");
		ArrayList<Line> contents = new ArrayList<>();

		for (String s: splitLines) {
			ArrayList<String> words = new ArrayList<>(Arrays.asList(s.split(" ")));
			Line line = new Line(editor.getAudioManager());
			line.setWords(words);
			contents.add(line);
		}

		editor.getCurrentDocument().setContents(contents);

	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

}
