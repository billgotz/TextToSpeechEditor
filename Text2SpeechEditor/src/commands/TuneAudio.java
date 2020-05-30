package commands;

import text2speechapis.TextToSpeechAPI;
import view.Text2SpeechEditorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TuneAudio implements ActionListener {
	
	Text2SpeechEditorView editor;
    TextToSpeechAPI audioManager;
    JSlider volumeSlider;
    JSlider pitchSlider;
    JSlider rateSlider;
    private boolean isTest = false;

    public TuneAudio() {
    	editor  = Text2SpeechEditorView.getSingletonView();
    	audioManager  = editor.getAudioManager();
    	volumeSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, (int) (audioManager.getVolume()*10));
        pitchSlider  = new JSlider(SwingConstants.HORIZONTAL, 0, 1000, (int) (audioManager.getPitch()*10));
        rateSlider   = new JSlider(SwingConstants.HORIZONTAL, 0, 1500, (int) (audioManager.getRate()*10));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor.getCurrentDocument() == null)
            JOptionPane.showMessageDialog(null, "Open or Create a document first");

        else {
            JFrame tuneAudioFrame = new JFrame("Tune audio parameters");

            JLabel vol = new JLabel("Volume: " + audioManager.getVolume());
            volumeSlider.setMajorTickSpacing(1);


            JLabel pitchL = new JLabel("Pitch: " + audioManager.getPitch());
            pitchSlider.setMajorTickSpacing(1);

            JLabel rateL = new JLabel("Rate: " + audioManager.getRate());

            rateSlider.setMajorTickSpacing(1);

            JButton confirmButton = new JButton("Confirm");
            JButton resetButton = new JButton("Reset to defaults");

            volumeSlider.addChangeListener(e12 -> vol.setText(("Volume: " + (float) volumeSlider.getValue() / 10)));
            pitchSlider.addChangeListener(e12 -> pitchL.setText(("Pitch: " + (float) pitchSlider.getValue() / 10)));
            rateSlider.addChangeListener(e12 -> rateL.setText(("Rate: " + (float) rateSlider.getValue() / 10)));

            JPanel actionPanel = new JPanel();
            JPanel volumePanel = new JPanel();
            JPanel pitchPanel = new JPanel();
            JPanel ratePanel = new JPanel();

            actionPanel.add(confirmButton);
            actionPanel.add(resetButton);

            volumePanel.add(vol);
            volumePanel.add(volumeSlider);

            pitchPanel.add(pitchL);
            pitchPanel.add(pitchSlider);

            ratePanel.add(rateL);
            ratePanel.add(rateSlider);

            tuneAudioFrame.add(actionPanel, BorderLayout.CENTER);
            tuneAudioFrame.add(volumePanel, BorderLayout.CENTER);
            tuneAudioFrame.add(pitchPanel, BorderLayout.CENTER);
            tuneAudioFrame.add(ratePanel, BorderLayout.CENTER);

            tuneAudioFrame.setLayout(new GridLayout(4, 1));
            tuneAudioFrame.setSize(500, 300);
            tuneAudioFrame.setResizable(true);
            tuneAudioFrame.setLocationRelativeTo(null);
            tuneAudioFrame.setVisible(true);


            confirmButton.addActionListener(e16 -> {
                float volume = (float) volumeSlider.getValue()/10;
                float pitch  = (float) pitchSlider.getValue()/10;
                float rate   = (float) rateSlider.getValue()/10;

                audioManager.setVolume(volume);
                audioManager.setPitch(pitch);
                audioManager.setRate(rate);

                System.out.println(audioManager.getVolume());
                System.out.println(audioManager.getPitch());
                System.out.println(audioManager.getRate());

                tuneAudioFrame.dispose();
            });

            if (isTest)
                confirmButton.doClick();

            resetButton.addActionListener(e1 -> {
                volumeSlider.setValue(10);
                pitchSlider.setValue(1000);
                rateSlider.setValue(1500);

            });
        }
    }

    public void setTest (boolean isTest, float volume, float pitch, float rate) {
        this.isTest = isTest;
        volumeSlider.setValue((int) (volume*10));
        pitchSlider.setValue((int) (pitch*10));
        rateSlider.setValue((int) (rate*10));
    }

}
