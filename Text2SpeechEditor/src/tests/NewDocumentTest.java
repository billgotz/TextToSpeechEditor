package tests;

import commands.CommandsFactory;
import commands.NewDocument;
import org.junit.Test;
import view.Text2SpeechEditorView;
import static org.junit.Assert.assertTrue;

public class NewDocumentTest {

    @Test
    public void testNewDocument() {

        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        NewDocument newDocument = (NewDocument) new CommandsFactory().createCommand("New");

        newDocument.setTest(true);
        newDocument.actionPerformed(null);

        assertTrue(editor.getCurrentDocument().getContents().isEmpty());

    }
}
