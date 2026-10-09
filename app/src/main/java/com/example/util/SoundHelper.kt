package com.example.util

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool

object SoundHelper {
    private var soundPool: SoundPool? = null
    private val soundMap = mutableMapOf<String, Int>()

    fun init(context: Context) {
        if (soundPool != null) return
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(5)
            .setAudioAttributes(audioAttributes)
            .build()
    }

    fun playSFX(type: String) {
        val pool = soundPool ?: return
        val soundId = soundMap[type] ?: return
        pool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f)
    }
}
