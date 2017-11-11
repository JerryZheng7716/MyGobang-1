import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlaySound {
    private FileInputStream fileInputStream = null;
    public  static boolean isPlaySound=true,isPlayMusic=true;
    public void PlaySound(String soundName)
    {
        try {
            if (soundName=="chess" && isPlaySound){
                fileInputStream = new FileInputStream(new File("wav/chess_Sound.wav"));
                AudioStream as1 = new AudioStream(fileInputStream);
                AudioPlayer.player.start(as1);
            }else if (soundName=="button" && isPlaySound){
                fileInputStream = new FileInputStream(new File("wav/button_Sound.wav"));
                AudioStream as2 = new AudioStream(fileInputStream);
                AudioPlayer.player.start(as2);
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
//    public void stopSound(boolean isPlay){
//        isPlaySound=false;
//    }
//    public void stopMusic(boolean isPlay){
//        isPlayMusic=false;
//    }
}
