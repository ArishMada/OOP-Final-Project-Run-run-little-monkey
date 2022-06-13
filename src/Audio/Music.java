package Audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Music {
        Clip clip;
        URL soundURL[] = new URL[30];

        public Music() {
            soundURL[0] = getClass().getResource("/res/bgMusic.wav");
            soundURL[1] = getClass().getResource("/res/menuMusic.wav");
        }

        public void setFile(int i) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[i]);
                clip = AudioSystem.getClip();
                clip.open(audio);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void play() {
            clip.start();
        }

        public void loop() {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        public void stop() {
            clip.stop();
        }
    }

