package MediaClass;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayNewMedia {
    private Media media;
    private MediaPlayer mediaPlayer;
    private String soundPath;

    public PlayNewMedia(String soundPath) {
        this.soundPath = soundPath;
    }

    public void run() {
        try {
            // Get the resource URL
            URL resourceUrl = getClass().getResource(soundPath);

            if (resourceUrl == null) {
                System.err.println("Sound file not found: " + soundPath);
                return;
            }

            // Convert to external form for Media
            String mediaUrl = resourceUrl.toExternalForm();

            // Create media and media player
            media = new Media(mediaUrl);
            mediaPlayer = new MediaPlayer(media);

            // Play the sound
            mediaPlayer.play();

            // Dispose after playing
            mediaPlayer.setOnEndOfMedia(() -> {
                dispose();
            });

            // Handle errors
            mediaPlayer.setOnError(() -> {
                System.err.println("MediaPlayer error: " + mediaPlayer.getError());
                dispose();
            });

        } catch (Exception e) {
            System.err.println("Error playing sound: " + soundPath);
            e.printStackTrace();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void dispose() {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
        if (media != null) {
            media = null;
        }
    }
}
