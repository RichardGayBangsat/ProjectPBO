/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sound;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
/**
 *
 * @author USER
 */
public class Sound {
    private Clip clip;
    private URL soundURL;

    public Sound(String path) {
        soundURL = getClass().getResource(path);
        setFile();
    }

    private void setFile() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Reset to the beginning
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.setFramePosition(0); // Reset to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    
    
}
