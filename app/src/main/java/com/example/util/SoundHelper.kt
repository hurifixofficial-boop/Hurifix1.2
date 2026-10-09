package com.example.util

import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log

/**
 * Lightweight, safe Sound Effects (SFX) helper for Android UI interactions
 * using Android's built-in ToneGenerator.
 * Supports: 'click', 'success' (chime), and 'error' (alert).
 */
object SoundHelper {
    private var toneGenerator: ToneGenerator? = null

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 80)
        } catch (e: Exception) {
            Log.e("SoundHelper", "Failed to initialize ToneGenerator: ${e.message}")
        }
    }

    fun playSFX(type: String) {
        try {
            when (type.lowercase()) {
                "click" -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 40)
                }
                "success", "chime" -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_DTMF_1, 100)
                }
                "error", "alert" -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_SUP_ERROR, 150)
                }
                else -> {
                    toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 40)
                }
            }
        } catch (e: Exception) {
            Log.e("SoundHelper", "Play SFX error: ${e.message}")
        }
    }
}
