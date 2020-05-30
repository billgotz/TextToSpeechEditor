package tests;

import commands.CommandsFactory;
import commands.OpenDocument;
import org.junit.Test;
import view.Text2SpeechEditorView;

import java.io.BufferedReader;
import java.io.FileReader;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class OpenDocumentTest {

    @Test
    public void testOpenDocument() {
        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        OpenDocument openDocument =  (OpenDocument) new CommandsFactory().createCommand("Open");

        openDocument.actionPerformed(null);

        try {
            BufferedReader br = new BufferedReader(new FileReader(editor.getCurrentDocument().getLinkedFile()));
            br.readLine();
            br.readLine();
            br.readLine();
            br.readLine();

            assertEquals(editor.getCurrentDocument().getContents().get(0).getWords(), asList(br.readLine().split(" ")));
            assertEquals(editor.getCurrentDocument().getContents().get(1).getWords(), asList(br.readLine().split(" ")));
            
            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

