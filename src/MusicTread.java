import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MusicTread implements Runnable{
    private String name;
    public volatile boolean exit = false;
    private FileInputStream fileInputStream = null;

    public MusicTread(String name) {
        this.name = name;
    }

    public void run() {
        try {
            fileInputStream = new FileInputStream(new File("wav/back_Music.wav"));
            AudioStream as2 = new AudioStream(fileInputStream);
            AudioPlayer.player.start(as2);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}