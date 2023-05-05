package net.magnusfrater.ArpaSound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.*;

public class TestSound {

    private final int BUFFER_SIZE = 128000;

    AudioInputStream concat;

    public void concatWav(ArrayList<String> paths){
        try{

            InputStream in;
            AudioInputStream clip1 = null, clip2;

            for (String path : paths){

                in = getClass().getResourceAsStream(path); //get wav path

                if (in == null){ //for first path
                    clip1 = AudioSystem.getAudioInputStream(new BufferedInputStream(in));
                    continue;
                }

                clip2 = AudioSystem.getAudioInputStream(new BufferedInputStream(in));

                AudioInputStream clipConcat = new AudioInputStream(new SequenceInputStream(clip1, clip2), clip1.getFormat(), clip1.getFrameLength() + clip2.getFrameLength());

                clip1 = clipConcat;
            }

            concat = clip1;

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //new File(path)
    //getClass().getClassLoader().getResourceAsStream(path)
    //"src/net.magnusfrater.ArpaSound.res/arpasounds/concatWav.wav"

    public void playConcat(){

        AudioInputStream ais;
        AudioFormat af;
        SourceDataLine sdl;

        try {
            ais = AudioSystem.getAudioInputStream(concat);
        } catch (Exception e){
            e.printStackTrace();
            return;
        }

        af = ais.getFormat();

        DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
        try {
            sdl = (SourceDataLine) AudioSystem.getLine(info);
            sdl.open(af);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        sdl.start();

        int nBytesRead = 0;
        byte[] abData = new byte[BUFFER_SIZE];
        while (nBytesRead != -1) {
            try {
                nBytesRead = ais.read(abData, 0, abData.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (nBytesRead >= 0) {
                @SuppressWarnings("unused")
                int nBytesWritten = sdl.write(abData, 0, nBytesRead);
            }
        }

        sdl.drain();
        sdl.close();
    }

    public void playWavs(ArrayList<String> paths){
        for (String path : paths){
            try {
                InputStream in = new FileInputStream(path);
                AudioStream audio = new AudioStream(in);
                AudioPlayer.player.start(audio);

                Thread.sleep(100);
            } catch (Exception e){
                System.out.println("Exception: "+ e);
            }
        }
    }
}
