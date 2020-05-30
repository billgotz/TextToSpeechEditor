package view;

import commands.CommandsFactory;
import commands.ReplayManager;
import encodingstrategies.EncodingStrategy;
import model.Document;
import text2speechapis.TextToSpeechAPI;
import text2speechapis.TextToSpeechAPIFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Text2SpeechEditorView extends JFrame implements ActionListener {

	/* Fields
	 */
	private static final long serialVersionUID = 1L;
	static Text2SpeechEditorView singletonView = new Text2SpeechEditorView();
	
	private final JFrame mainFrame;
	private final JTextArea txtArea;
	private final JMenuBar mBar;
	private final JMenu documentMenu;
	private final JMenu txt2speechMenu;

	private final ReplayManager replayManager;
	private TextToSpeechAPI audioManager;
	private EncodingStrategy strategy;

	private Document currentDocument;
	private int chosenLine;

	private Text2SpeechEditorView() 
	{ 
	
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		audioManager = new TextToSpeechAPIFactory().createTTSAPI("freeTTS");
		replayManager = new ReplayManager();

		mainFrame = new JFrame("Text to speech editor");
		txtArea   = new JTextArea("Create or open a document");
		txtArea.setEditable(false);

		// Add text area to scroller, so we can scroll the editor
		JScrollPane scroller = new JScrollPane(txtArea);

		/*
		 * Create menu bar
		 */
		mBar = new JMenuBar();

		/*
		 * DOCUMENT MENU
		 */
		documentMenu = new JMenu("Document");

		/*
		 * Create document menu items
		 */
		JMenuItem newCommand = new JMenuItem("New");
		JMenuItem openCommand = new JMenuItem("Open"); 
		JMenuItem editCommand = new JMenuItem("Edit"); 
		JMenuItem saveCommand = new JMenuItem("Save");
		JMenuItem replayCommand = new JMenuItem("Replay");
		//replayCommand.setEnabled(false);
		JMenuItem closeCommand = new JMenuItem("Exit Application");

		/*
		 * Add the action listeners for menu items
		 */
		newCommand.addActionListener(this);
		openCommand.addActionListener(this);
		editCommand.addActionListener(this);
		saveCommand.addActionListener(this);
		replayCommand.addActionListener(this);
		closeCommand.addActionListener(this);

		/*
		 * Add items to the menu
		 */
		documentMenu.add(newCommand);
		documentMenu.add(openCommand);
		documentMenu.add(editCommand);
		documentMenu.add(saveCommand);
		documentMenu.add(replayCommand);
		documentMenu.addSeparator();
		documentMenu.add(closeCommand);

		/*
		 * TEXT 2 SPEECH MENU
		 */
		txt2speechMenu = new JMenu("Text 2 Speech");

		/*
		 * Create text 2 speech menu items
		 */
		JMenuItem t2sDocument = new JMenuItem("Transform document");
		JMenuItem t2sDocumentReversed = new JMenuItem("Transform document in reverse");
		JMenuItem t2sDocumentEncodeFirst = new JMenuItem("Transform document encoded");
		JMenuItem chooseLine = new JMenuItem("Choose line...");
		JMenuItem t2sLine = new JMenuItem("Transform line");
		JMenuItem t2sLineReversed = new JMenuItem("Transform line in reverse");
		JMenuItem t2sLineEncodeFirst = new JMenuItem("Transform line encoded");
		JMenuItem t2sTuneEncodingStrategy = new JMenuItem("Tune Encoding Strategy...");
		JMenuItem t2sTuneAudio = new JMenuItem("Tune Audio Parameters...");

		/*
		 * Add action listeners to text 2 speech commands
		 */
		t2sDocument.addActionListener(this);
		chooseLine.addActionListener(this);
		t2sLine.addActionListener(this);
		t2sDocumentReversed.addActionListener(this);
		t2sLineReversed.addActionListener(this);
		t2sDocumentEncodeFirst.addActionListener(this);
		t2sLineEncodeFirst.addActionListener(this);
		t2sTuneEncodingStrategy.addActionListener(this);
		t2sTuneAudio.addActionListener(this);

		/*
		 * Add items to menu
		 */
		txt2speechMenu.add(t2sDocument);
		txt2speechMenu.add(t2sDocumentReversed);
		txt2speechMenu.add(t2sDocumentEncodeFirst);
		txt2speechMenu.addSeparator();
		txt2speechMenu.add(chooseLine);
		txt2speechMenu.add(t2sLine);
		txt2speechMenu.add(t2sLineReversed);
		txt2speechMenu.add(t2sLineEncodeFirst);
		txt2speechMenu.addSeparator();
		txt2speechMenu.add(t2sTuneEncodingStrategy);
		txt2speechMenu.add(t2sTuneAudio);

		mBar.add(documentMenu);
		mBar.add(txt2speechMenu);

		mainFrame.setJMenuBar(mBar);
		mainFrame.add(scroller);
		mainFrame.setSize(800, 450);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		// Copied code from stack overflow to set the position of window to center of screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - getMainFrame().getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - getMainFrame().getHeight()) / 2);
		mainFrame.setLocation(x,y);
		setLocationRelativeTo(null);	
		mainFrame.setVisible(true);

	}

	// when a menu command is pressed
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Exit Application"))
		{
			System.exit(1);
		}

		ActionListener command = new CommandsFactory().createCommand(e.getActionCommand());
		command.actionPerformed(e);
		
		if (!e.getActionCommand().equals("Replay")) {
			replayManager.addCommand(command);
		}

		if (currentDocument != null && currentDocument.getContents() != null) {
			System.out.println("from singleton: " + currentDocument.getTitle());
			System.out.println("from singleton: " + currentDocument.getAuthor());
			System.out.println("from singleton: " + currentDocument.getCreationDate());
			System.out.println("from singleton: " + currentDocument.getSaveDate());
		}
		
	} 
	
	public static Text2SpeechEditorView getSingletonView() {
		return singletonView;
	}
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	
	public void setCurrentDocument(Document document) {
		currentDocument = document;
	}

	public JTextArea getTxtArea() {
		return txtArea;
	}
	
	public JFrame getMainFrame() {
		return mainFrame;
	}

	public EncodingStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(EncodingStrategy strategy) {
		this.strategy = strategy;
	}

	public ReplayManager getReplayManager() {
		return replayManager;
	}

	public TextToSpeechAPI getAudioManager() {
		return this.audioManager;
	}

	public void setAudioManager(TextToSpeechAPI audioManager) {
		this.audioManager = audioManager;
	}

	public float getVolume() {
		return audioManager.getVolume();
	}

	public float getRate() {
		return audioManager.getRate();
	}

	public float getPitch() {
		return audioManager.getPitch();
	}

	public int getChosenLine() {
		return chosenLine;
	}

	public void setChosenLine(int line) {
		chosenLine = line;
	}

	public JMenuBar getMBar () {
		return this.mBar;
	}

	public JMenu getDocumentMenu () {
		return this.documentMenu;
	}

	public JMenu getTxt2speechMenu () {
		return this.txt2speechMenu;
	}

	// Main class
	public static void main(String[] args)
	{
	}
}
