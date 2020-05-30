package commands;

import model.Document;
import view.Text2SpeechEditorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentToSpeechReverse implements ActionListener {

    public DocumentToSpeechReverse(){

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

        else {
            currentDocument.playReverseContents();
        }
    }
}
