package com.example.util

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.util.Log
import android.view.SoundEffectConstants

/**
 * Central SoundManager providing clean, modern UI audio feedback
 * using standard Android ToneGenerator and system click sounds.
 *
 * Designed to eliminate harsh/odd legacy sounds (such as DTMF dialing beeps or loud supervisor tones)
 * and replace them with subtle, crisp, polished feedback:
 *
 * - Button Clicks / Actions: Light, crisp tap sound.
 * - Success Actions (Login Success, Order Assign, Profile Update): Smooth, pleasant success tone.
 * - Error Actions (Validation Fail, Connection Loss): Gentle error/warning tone.
 */
object SoundManager {
    private const val TAG = "SoundManager"

    private var toneGenerator: ToneGenerator? = null
    private var appContext: Context? = null

    init {
        initToneGenerator()
    }

    private fun safeLog(message: String) {
        try {
            Log.w(TAG, message)
        } catch (_: Throwable) {}
    }

    private fun initToneGenerator() {
        try {
            // STREAM_MUSIC at comfortable volume (45) provides smooth, pleasant audio without harsh peaks
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 45)
        } catch (e: Throwable) {
            safeLog("ToneGenerator STREAM_MUSIC init note: ${e.message}")
            try {
                toneGenerator = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 40)
            } catch (ex: Throwable) {
                safeLog("ToneGenerator fallback init error: ${ex.message}")
            }
        }
    }

    fun initialize(context: Context) {
        appContext = context.applicationContext
    }

    /**
     * Button Clicks / Actions: Light, crisp tap sound.
     * Uses Android's native system click sound effect, falling back to a subtle 25ms micro-tap.
     */
    fun playClick() {
        try {
            val audioManager = appContext?.getSystemService(Context.AUDIO_SERVICE) as? AudioManager
            if (audioManager != null) {
                audioManager.playSoundEffect(SoundEffectConstants.CLICK, 0.4f)
            } else {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 25)
            }
        } catch (_: Throwable) {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP2, 25)
            } catch (_: Throwable) {}
        }
    }

    /**
     * Success Actions (Login Success, Order Assign, Profile Update):
     * Smooth, pleasant success tone.
     * Uses ToneGenerator's built-in positive acknowledgment (TONE_PROP_ACK) chime.
     */
    fun playSuccess() {
        try {
            // TONE_PROP_ACK is Android's standard positive acknowledgment chime (smooth & pleasant)
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_ACK, 90)
        } catch (e: Throwable) {
            safeLog("Error playing success tone: ${e.message}")
        }
    }

    /**
     * Error Actions (Validation Fail, Connection Loss):
     * Gentle, non-jarring error/warning tone.
     * Uses ToneGenerator's negative acknowledgment (TONE_PROP_NACK) or soft error lite.
     */
    fun playError() {
        try {
            // TONE_PROP_NACK is a gentle, polite negative acknowledgment double-tap
            toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 75)
        } catch (_: Throwable) {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE, 75)
            } catch (ex: Throwable) {
                safeLog("Error playing error tone: ${ex.message}")
            }
        }
    }

    /**
     * Compatibility router for existing callers using sound type keys.
     */
    fun playSFX(type: String) {
        when (type.lowercase()) {
            "click", "tap" -> playClick()
            "success", "chime", "assign", "login_success", "profile_update" -> playSuccess()
            "error", "alert", "fail", "warning", "disconnect" -> playError()
            else -> playClick()
        }
    }
}
