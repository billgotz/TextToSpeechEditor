package tests;

import commands.CommandsFactory;
import commands.EditDocument;
import model.Document;
import org.junit.Test;
import view.Text2SpeechEditorView;

import static org.junit.Assert.assertEquals;

public class EditDocumentTest {

    @Test
    public void testEditDocument() {
        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        EditDocument editDoc =  (EditDocument) new CommandsFactory().createCommand("Edit");

        editor.setCurrentDocument(new Document(editor.getAudioManager()));
        editor.getTxtArea().setText("Test\nText\nfor the edit\ndocument\ncommand");

        editDoc.setTest(true);
        editDoc.actionPerformed(null);

        assertEquals(editor.getCurrentDocument().getContents().get(0).getWords().get(0) , "Test");
        assertEquals(editor.getCurrentDocument().getContents().get(1).getWords().get(0) , "Text");
        assertEquals(editor.getCurrentDocument().getContents().get(2).getWords().get(0) , "for");
        assertEquals(editor.getCurrentDocument().getContents().get(2).getWords().get(1) , "the");
        assertEquals(editor.getCurrentDocument().getContents().get(2).getWords().get(2) , "edit");
        assertEquals(editor.getCurrentDocument().getContents().get(3).getWords().get(0) , "document");
        assertEquals(editor.getCurrentDocument().getContents().get(4).getWords().get(0) , "command");

    }

}
