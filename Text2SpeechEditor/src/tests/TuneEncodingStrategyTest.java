package tests;

import commands.CommandsFactory;
import commands.TuneEncodingStrategy;
import model.Document;
import org.junit.Test;
import view.Text2SpeechEditorView;

import static org.junit.Assert.assertEquals;

public class TuneEncodingStrategyTest {

    @Test
    public void testTuneEncodingStrategy() {

        Text2SpeechEditorView editorView = Text2SpeechEditorView.getSingletonView();

        editorView.setCurrentDocument(new Document(editorView.getAudioManager()));

        TuneEncodingStrategy tuneEncodingStrategy  = (TuneEncodingStrategy) new CommandsFactory().createCommand("Tune Encoding Strategy...");

        String encodingStrategy = "rot13";

        tuneEncodingStrategy.setTest(true, encodingStrategy);
        tuneEncodingStrategy.actionPerformed(null);

        assertEquals(editorView.getStrategy().toString(), encodingStrategy);

    }
}