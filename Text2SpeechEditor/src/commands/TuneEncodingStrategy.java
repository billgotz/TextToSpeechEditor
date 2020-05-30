package commands;

import encodingstrategies.EncodingStrategy;
import encodingstrategies.StrategiesFactory;
import model.Document;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuneEncodingStrategy implements ActionListener {

    private String encodingStrategy;
    private EncodingStrategy strategy;
    private boolean isTest = false;

    public TuneEncodingStrategy() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Text2SpeechEditorView editor = Text2SpeechEditorView.getSingletonView();
        Document currentDocument = editor.getCurrentDocument();

        if (currentDocument == null)
            JOptionPane.showMessageDialog(null, "Open or Create a document first");

        else {
            JFrame frame = new JFrame("Choose encoding strategy");
            JLabel chooseLabel = new JLabel("Choose encoding strategy");

            JRadioButton rot13Button = new JRadioButton("Rot 13 Encoding");
            JRadioButton atBashButton = new JRadioButton("At Bash Encoding");
            JRadioButton noneButton = new JRadioButton("None");
            JButton completeButton = new JButton("Tune encoding");

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(rot13Button);
            buttonGroup.add(atBashButton);
            buttonGroup.add(noneButton);

            frame.add(chooseLabel);
            frame.add(rot13Button);
            frame.add(atBashButton);
            frame.add(noneButton);
            frame.add(completeButton);

            frame.setVisible(true);
            frame.setLayout(new GridLayout(5,1));
            frame.setSize(400, 125);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            completeButton.addActionListener(e1 -> {
                if (rot13Button.isSelected()) encodingStrategy = "rot13";
                else if (atBashButton.isSelected()) encodingStrategy = "atbash";

                if (encodingStrategy != null) {
                    strategy = new StrategiesFactory().createStrategy(encodingStrategy);
                    editor.setStrategy(strategy);
                    currentDocument.tuneEncodingStrategy(strategy);
                }
                else
                    editor.setStrategy(null);

                frame.dispose();
            });

            if (isTest)
                completeButton.doClick();

        }
    }

    public void setTest(boolean isTest, String encodingStrategy) {
        this.isTest = isTest;
        this.encodingStrategy = encodingStrategy;
    }

}
