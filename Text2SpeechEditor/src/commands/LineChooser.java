package commands;

import model.Document;
import view.Text2SpeechEditorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LineChooser implements ActionListener {

    private int line;

    public LineChooser() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Text2SpeechEditorView editorView = Text2SpeechEditorView.getSingletonView();
        Document currentDocument = editorView.getCurrentDocument();

        if (currentDocument == null) {
            JOptionPane.showMessageDialog(null, "Open or Create a document first");
        }

        else if (currentDocument.getContents().isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no contents to transform");

        }

        else {
            JFrame lineChooserFrame = new JFrame("Choose line");
            SpinnerNumberModel model = new SpinnerNumberModel(1, 1, currentDocument.getContents().size(), 1);
            JSpinner lineChooser = new JSpinner(model);
            lineChooser.setBounds(100, 50, 50, 30);

            JLabel label = new JLabel("Line: 1");
            JButton confirmButton = new JButton("Confirm");

            JPanel panel = new JPanel();
            panel.add(label);
            panel.add(confirmButton);

            confirmButton.setSize(50, 25);

            lineChooserFrame.add(lineChooser);
            lineChooserFrame.add(panel);
            lineChooserFrame.setSize(260, 130);
            lineChooserFrame.setLocationRelativeTo(null);
            lineChooserFrame.setVisible(true);

            lineChooser.addChangeListener(e12 -> {
                label.setText("Line : " + lineChooser.getValue());
                line = ((int) lineChooser.getValue() - 1);
            });

            confirmButton.addActionListener(e1 -> {
                editorView.setChosenLine(line);
                lineChooserFrame.dispose();
            });
        }
    }
}
