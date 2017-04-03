package com.android.components.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;


import com.android.components.R;

import java.io.IOException;

/**
 * PlaySound.java
 * <p/>
 * This class is a common class to play,pause,resume and stop the media player.
 */
public class PlaySound {
    private MediaPlayer mediaPlayer;
    private Uri ringtoneUri;
    private Context mcontext;

    public PlaySound(Context context) {
        mcontext = context;
        ringtoneUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.malare_premam);
    }

    //To start the music player
    public void start() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mcontext, ringtoneUri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // To resume the music player
    public void resume() {
        try {
            if (mediaPlayer != null)
                mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // To pause the music player
    public void pause() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying())
                mediaPlayer.pause();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    // To stop the music player.
    public void stop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
