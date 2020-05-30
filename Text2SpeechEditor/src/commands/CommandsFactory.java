package commands;

import view.Text2SpeechEditorView;
import java.awt.event.ActionListener;

public class CommandsFactory {
	public ActionListener createCommand(String command) {
		
		switch (command) {
			case "New":
				return new NewDocument();
			case "Save":
				return new SaveDocument();
			case "Open":
				return new OpenDocument();
			case "Edit":
				return new EditDocument();
			case "Replay":
				return new ReplayCommand(Text2SpeechEditorView.getSingletonView().getReplayManager());
			case "Transform document":
				return new DocumentToSpeech();
			case "Choose line...":
				return new LineChooser();
			case "Transform line":
				return new LineToSpeech();
			case "Transform document in reverse":
				return new DocumentToSpeechReverse();
			case "Transform line in reverse":
				return new LineToSpeechReverse();
			case "Transform document encoded":
				return new DocumentToSpeechEncoded();
			case "Transform line encoded":
				return new LineToSpeechEncoded();
			case "Tune Encoding Strategy...":
				return new TuneEncodingStrategy();
			case "Tune Audio Parameters...":
				return new TuneAudio();
			default:
				System.out.println(command +": Wrong argument to the CommandsFactory");
				return null;
		}

	}

}
