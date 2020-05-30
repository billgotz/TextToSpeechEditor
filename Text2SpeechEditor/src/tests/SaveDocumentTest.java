package tests;

import commands.CommandsFactory;
import commands.SaveDocument;
import model.Document;
import org.junit.Test;
import view.Text2SpeechEditorView;

import java.io.*;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SaveDocumentTest {

    @Test
    public void testSaveDocument() throws IOException {
        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        SaveDocument saveDoc =  (SaveDocument) new CommandsFactory().createCommand("Save");

        editor.setCurrentDocument(new Document(editor.getAudioManager()));
        editor.getTxtArea().setText("test text for save \ndocument testing");

        saveDoc.actionPerformed(null);

        BufferedReader br = new BufferedReader(new FileReader(editor.getCurrentDocument().getLinkedFile()));

        // Ignore title, author, creation date, last save date
        br.readLine();
        br.readLine();
        br.readLine();
        br.readLine();

        assertEquals(editor.getCurrentDocument().getContents().get(0).getWords(), asList(br.readLine().split(" ")));
        assertEquals(editor.getCurrentDocument().getContents().get(1).getWords(), asList(br.readLine().split(" ")));
        
        br.close();
    }
}
