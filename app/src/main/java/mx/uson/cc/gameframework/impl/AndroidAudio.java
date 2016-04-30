package mx.uson.cc.gameframework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

import mx.uson.cc.gameframework.Audio;
import mx.uson.cc.gameframework.Music;
import mx.uson.cc.gameframework.Sound;

/**
 * Created by ernesto on 2/11/15.
 */
public class AndroidAudio implements Audio{
    AssetManager assets;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        this.assets=activity.getAssets();
        this.soundPool= new SoundPool(20,AudioManager.STREAM_MUSIC,0);
    }

    public Music newMusic(String fileName){
        try{
            AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
            return new AndroidMusic(assetFileDescriptor);
        }catch(IOException e){
            throw new RuntimeException("No se pudo");
        }
    }

    public Sound newSound(String fileName){
        try{
            AssetFileDescriptor assetFileDescriptor = assets.openFd(fileName);
            int soundId = soundPool.load(assetFileDescriptor,0);
            return new AndroidSound(soundPool,soundId);
        } catch (IOException e){
            throw new RuntimeException("No se pudo");
        }
    }
}
