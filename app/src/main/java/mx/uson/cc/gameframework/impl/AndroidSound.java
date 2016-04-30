package mx.uson.cc.gameframework.impl;

import android.media.SoundPool;

import mx.uson.cc.gameframework.Sound;

/**
 * Created by ernesto on 2/11/15.
 */
public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool,int soundId){
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    public void play(float volume){
        soundPool.play(soundId,volume,volume,0,0,1);
    }

    public void dispose(){
        soundPool.unload(soundId);
    }
}
