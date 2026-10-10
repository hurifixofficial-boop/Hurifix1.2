package com.example.util

/**
 * Lightweight, safe Sound Effects helper for Android UI interactions.
 * Routes all calls to [SoundManager] to provide modern, subtle, clean sound effects.
 */
object SoundHelper {
    fun playSFX(type: String) {
        SoundManager.playSFX(type)
    }

    fun playClick() = SoundManager.playClick()
    fun playSuccess() = SoundManager.playSuccess()
    fun playError() = SoundManager.playError()
}
