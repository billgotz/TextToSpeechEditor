package commands;

import model.Document;
import view.Text2SpeechEditorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineToSpeech implements ActionListener {

    public LineToSpeech() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        Document currentDocument = editor.getCurrentDocument();

        if (currentDocument == null) {
            JOptionPane.showMessageDialog(null, "Open or Create a document first");
        }

        else if (currentDocument.getContents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no contents to transform");
        }

        else if (editor.getChosenLine() >= currentDocument.getContents().size()) {
            currentDocument.playLine(0);
        }

        else {
            currentDocument.playLine(editor.getChosenLine());
        }
    }



}
