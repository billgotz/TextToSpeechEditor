package tests;

import commands.CommandsFactory;
import commands.TuneAudio;
import model.Document;
import org.junit.Test;
import text2speechapis.FakeTextToSpeech;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;

import static org.junit.Assert.*;

public class TuneAudioTest {

    @Test
    public void testTuneAudio() {

        Text2SpeechEditorView editorView = Text2SpeechEditorView.getSingletonView();

        FakeTextToSpeech ft2s = (FakeTextToSpeech) new TextToSpeechAPIFactory().createTTSAPI("fakeTTS");
        ft2s.setVolume((float) 0.7);
        ft2s.setRate((float) 55.4);
        ft2s.setPitch((float) 28.9);

        editorView.setCurrentDocument(new Document(ft2s));

        TuneAudio tuneAudio = (TuneAudio) new CommandsFactory().createCommand("Tune Audio Parameters...");
        tuneAudio.setTest(true, ft2s.getVolume(), ft2s.getPitch(), ft2s.getRate());
        tuneAudio.actionPerformed(null);

        assertEquals(editorView.getAudioManager().getVolume(), ft2s.getVolume(), 0);
        assertEquals(editorView.getAudioManager().getRate(), ft2s.getRate(), 0);
        assertEquals(editorView.getAudioManager().getPitch(), ft2s.getPitch(), 0);

    }
}