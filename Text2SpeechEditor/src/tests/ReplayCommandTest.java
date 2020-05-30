package tests;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Test;
import view.Text2SpeechEditorView;

public class ReplayCommandTest {

	@Test
	public void testReplayCommand() {

		Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
		
		
		editor.actionPerformed(new ActionEvent(new Object(), 0, "Open"));
		editor.actionPerformed(new ActionEvent(new Object(), 0, "Save"));
		editor.actionPerformed(new ActionEvent(new Object(), 0, "Edit"));
		editor.actionPerformed(new ActionEvent(new Object(), 0, "Replay"));
		
		assertEquals(editor.getReplayManager().getHistory().size(), 3);
	}

}
