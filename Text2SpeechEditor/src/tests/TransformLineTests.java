package tests;

import commands.*;
import encodingstrategies.EncodingStrategy;
import encodingstrategies.Rot13Encoding;
import encodingstrategies.StrategiesFactory;
import model.Document;
import model.Line;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import text2speechapis.FakeTextToSpeech;
import text2speechapis.TextToSpeechAPIFactory;
import view.Text2SpeechEditorView;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TransformLineTests {

    Document doc;
    FakeTextToSpeech fTTS;
    CommandsFactory commandsFactory;
    Text2SpeechEditorView editor;

    final int line = 1;

    @Before
    public void setUpDocument() {
        editor = Text2SpeechEditorView.getSingletonView();
        commandsFactory = new CommandsFactory();

        fTTS = (FakeTextToSpeech) new TextToSpeechAPIFactory().createTTSAPI("fakeTTS");
        editor.setAudioManager(fTTS);

        doc = new Document(editor.getAudioManager());

        ArrayList<String> words1 = new ArrayList<>();
        words1.add("Testing");
        words1.add("transform");
        words1.add("document");

        ArrayList<String> words2 = new ArrayList<>();
        words2.add("second");
        words2.add("text");
        words2.add("line");

        Line l1 = new Line(editor.getAudioManager());
        l1.setWords(words1);

        Line l2 = new Line(editor.getAudioManager());
        l2.setWords(words2);

        ArrayList<Line> contents = new ArrayList<>();
        contents.add(l1);
        contents.add(l2);

        doc.setContents(contents);
        editor.setCurrentDocument(doc);
        editor.setChosenLine(line);
    }

    @After
    public void clearDocument() {
        assertEquals(doc.getContents().get(line).getWords().size(), fTTS.getPlays().size());
        fTTS.getPlays().clear();
        System.out.println("----------------------------");
    }

    @Test
    public void testLineToSpeech() {
        LineToSpeech line2speech =  (LineToSpeech) commandsFactory.createCommand("Transform line");

        line2speech.actionPerformed(null);

        assertEquals(doc.getContents().get(line).getWords().get(0), fTTS.getPlays().get(0));
        assertEquals(doc.getContents().get(line).getWords().get(1), fTTS.getPlays().get(1));
        assertEquals(doc.getContents().get(line).getWords().get(2), fTTS.getPlays().get(2));

    }

    @Test
    public void testLineToSpeechReverse() {
        LineToSpeechReverse line2speechReverse =  (LineToSpeechReverse) commandsFactory.createCommand("Transform line in reverse");

        line2speechReverse.actionPerformed(null);

        assertEquals(doc.getContents().get(line).getWords().get(0), fTTS.getPlays().get(2));
        assertEquals(doc.getContents().get(line).getWords().get(1), fTTS.getPlays().get(1));
        assertEquals(doc.getContents().get(line).getWords().get(2), fTTS.getPlays().get(0));
    }


    @Test
    public void testLineToSpeechEncoded() {
        LineToSpeechEncoded line2speechEncoded =  (LineToSpeechEncoded) commandsFactory.createCommand("Transform line encoded");
        Rot13Encoding rot13Encoding = new Rot13Encoding();

        EncodingStrategy strategy = new StrategiesFactory().createStrategy("rot13");
        editor.setStrategy(strategy);
        doc.tuneEncodingStrategy(strategy);

        line2speechEncoded.actionPerformed(null);

        assertEquals(rot13Encoding.encode(doc.getContents().get(line).getWords().get(0)), fTTS.getPlays().get(0));
        assertEquals(rot13Encoding.encode(doc.getContents().get(line).getWords().get(1)), fTTS.getPlays().get(1));
        assertEquals(rot13Encoding.encode(doc.getContents().get(line).getWords().get(2)), fTTS.getPlays().get(2));

    }

}
