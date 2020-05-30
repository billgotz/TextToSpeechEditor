package commands;

import encodingstrategies.EncodingStrategy;
import model.Document;
import view.Text2SpeechEditorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineToSpeechEncoded implements ActionListener {

    public LineToSpeechEncoded() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        Document currentDocument = editor.getCurrentDocument();
        EncodingStrategy encodingStrategy = editor.getStrategy();

        if (encodingStrategy == null)
            JOptionPane.showMessageDialog(null, "Select an encoding strategy first");

        else {
            if (currentDocument == null) {
                JOptionPane.showMessageDialog(null, "Open or Create a document first");
            }

            else if (currentDocument.getContents().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no contents to transform");
            }

            else if (editor.getChosenLine() >= currentDocument.getContents().size()) {
                currentDocument.playEncodedLine(0);
            }

            else {
                currentDocument.playEncodedLine(editor.getChosenLine());
            }
        }
    }
}
