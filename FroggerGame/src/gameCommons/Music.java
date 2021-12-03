package gameCommons;

import frog.Frog;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {
    AudioInputStream audioStream;
    Clip clip = null;
    /**
     * @param filename the name of the file that is going to be played
     */
    public void playSound(URL filename){
        //File file = new File(String.valueOf(getClass().getResource(String.valueOf(filename))));
        try {
            audioStream = AudioSystem.getAudioInputStream(filename);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
             clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public void stopSound(){
        clip.stop();
        clip.flush();
        clip.close();

    }
}