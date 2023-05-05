import java.util.ArrayList;

/**
 * Class CDPlayer
 */
public class CDPlayer extends MusicPlayer {

    private int deviceID;
    private int thisTrack;

    /**
     * Constructor for CD-Player
     */
    public CDPlayer(int id) {
        this.deviceID = id;
    }

    /**
     * Over-ride Method: turnOn
     */
    public void turnOn() {
        super.turnOn();
    }

    /**
     * Over-ride Method: turnOff
     */
    public void turnOff() {
        super.turnOff();
        this.thisTrack = 0;
    }

    /**
     * Method to play next track in the playlist by
     * printing it to stdout and changing current
     */
    public void nextTrack() {
        this.thisTrack = (thisTrack + 1 > this.playlist.size() - 1)? 0 : thisTrack + 1;
        play();
    }

    /**
     * Method to play previous track in the playlist by
     * printing it to stdout and changing current
     */
    public void previousTrack() {
        this.thisTrack = (thisTrack - 1 < 0)? 0 : thisTrack - 1;
        play();
    }

    /**
     * Method to play current track
     */
    public void play() {
        super.play(thisTrack);
    }

}
