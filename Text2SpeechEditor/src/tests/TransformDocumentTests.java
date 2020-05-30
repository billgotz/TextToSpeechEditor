package tests;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.DocumentToSpeechEncoded;
import commands.DocumentToSpeechReverse;
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

public class TransformDocumentTests {

    Document doc;
    FakeTextToSpeech fTTS;
    CommandsFactory commandsFactory;
    Text2SpeechEditorView editor;

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
    }

    @After
    public void clearDocument() {
        assertEquals(doc.getContents().get(0).getWords().size() + doc.getContents().get(1).getWords().size() , fTTS.getPlays().size());
        fTTS.getPlays().clear();
        System.out.println("----------------------------");
    }

    @Test
    public void testDocumentToSpeech() {
        DocumentToSpeech doc2speech =  (DocumentToSpeech) commandsFactory.createCommand("Transform document");

        doc2speech.actionPerformed(null);

        assertEquals(doc.getContents().get(0).getWords().get(0), fTTS.getPlays().get(0));
        assertEquals(doc.getContents().get(0).getWords().get(1), fTTS.getPlays().get(1));
        assertEquals(doc.getContents().get(0).getWords().get(2), fTTS.getPlays().get(2));
        assertEquals(doc.getContents().get(1).getWords().get(0), fTTS.getPlays().get(3));
        assertEquals(doc.getContents().get(1).getWords().get(1), fTTS.getPlays().get(4));
        assertEquals(doc.getContents().get(1).getWords().get(2), fTTS.getPlays().get(5));
    }

    @Test
    public void testDocumentToSpeechReverse() {
        DocumentToSpeechReverse doc2speechReverse =  (DocumentToSpeechReverse) commandsFactory.createCommand("Transform document in reverse");

        doc2speechReverse.actionPerformed(null);

        assertEquals(doc.getContents().get(0).getWords().get(0), fTTS.getPlays().get(5));
        assertEquals(doc.getContents().get(0).getWords().get(1), fTTS.getPlays().get(4));
        assertEquals(doc.getContents().get(0).getWords().get(2), fTTS.getPlays().get(3));
        assertEquals(doc.getContents().get(1).getWords().get(0), fTTS.getPlays().get(2));
        assertEquals(doc.getContents().get(1).getWords().get(1), fTTS.getPlays().get(1));
        assertEquals(doc.getContents().get(1).getWords().get(2), fTTS.getPlays().get(0));
    }


    @Test
    public void testDocumentToSpeechEncoded() {
        DocumentToSpeechEncoded doc2speechEncoded =  (DocumentToSpeechEncoded) commandsFactory.createCommand("Transform document encoded");
        Rot13Encoding rot13Encoding = new Rot13Encoding();

        EncodingStrategy strategy = new StrategiesFactory().createStrategy("rot13");
        editor.setStrategy(strategy);
        doc.tuneEncodingStrategy(strategy);

        doc2speechEncoded.actionPerformed(null);

        assertEquals(rot13Encoding.encode(doc.getContents().get(0).getWords().get(0)), fTTS.getPlays().get(0));
        assertEquals(rot13Encoding.encode(doc.getContents().get(0).getWords().get(1)), fTTS.getPlays().get(1));
        assertEquals(rot13Encoding.encode(doc.getContents().get(0).getWords().get(2)), fTTS.getPlays().get(2));
        assertEquals(rot13Encoding.encode(doc.getContents().get(1).getWords().get(0)), fTTS.getPlays().get(3));
        assertEquals(rot13Encoding.encode(doc.getContents().get(1).getWords().get(1)), fTTS.getPlays().get(4));
        assertEquals(rot13Encoding.encode(doc.getContents().get(1).getWords().get(2)), fTTS.getPlays().get(5));
    }

}
