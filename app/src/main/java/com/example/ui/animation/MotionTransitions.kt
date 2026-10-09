package com.example.ui.animation

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

/**
 * Lightweight, ultra-smooth motion curves and transition specs
 * modeled after Framer Motion's physics and spring/cubic-bezier easing.
 *
 * All transitions run strictly within 200ms - 280ms duration
 * to ensure 60fps/120fps fluid response without blocking user interaction or state.
 */
object MotionTransitions {

    // Framer Motion standard cubic-bezier curve (easeOut / spring-like deceleration)
    val FramerEase = CubicBezierEasing(0.22f, 1.0f, 0.36f, 1.0f)
    val FramerEaseInOut = CubicBezierEasing(0.25f, 0.1f, 0.25f, 1.0f)

    const val PAGE_DURATION_MS = 250
    const val TAB_DURATION_MS = 220
    const val ELEMENT_DURATION_MS = 200

    /**
     * Animation spec for Pager programmatic scrolls (e.g. clicking tabs)
     */
    val PagerScrollSpec: AnimationSpec<Float> = tween(
        durationMillis = TAB_DURATION_MS,
        easing = FastOutSlowInEasing
    )

    /**
     * Ultra-smooth full-page destination transition (Splash -> Auth -> Home).
     * Combines subtle parallax horizontal slide, micro-scale (0.97 -> 1.0), and opacity fade.
     */
    fun pageTransition(forward: Boolean = true): ContentTransform {
        val slideFraction = 0.12f
        val enter = fadeIn(
            animationSpec = tween(PAGE_DURATION_MS, easing = FramerEase)
        ) + slideInHorizontally(
            animationSpec = tween(PAGE_DURATION_MS, easing = FramerEase),
            initialOffsetX = { fullWidth ->
                if (forward) (fullWidth * slideFraction).toInt() else (-fullWidth * slideFraction).toInt()
            }
        ) + scaleIn(
            animationSpec = tween(PAGE_DURATION_MS, easing = FramerEase),
            initialScale = 0.98f
        )

        val exit = fadeOut(
            animationSpec = tween(PAGE_DURATION_MS - 40, easing = FramerEase)
        ) + slideOutHorizontally(
            animationSpec = tween(PAGE_DURATION_MS - 40, easing = FramerEase),
            targetOffsetX = { fullWidth ->
                if (forward) (-fullWidth * slideFraction).toInt() else (fullWidth * slideFraction).toInt()
            }
        ) + scaleOut(
            animationSpec = tween(PAGE_DURATION_MS - 40, easing = FramerEase),
            targetScale = 0.99f
        )

        return enter.togetherWith(exit)
    }

    /**
     * Ultra-smooth Tab transition (Customer Orders <-> Experts).
     * Snappy 220ms directional slide + fade.
     */
    fun tabTransition(forward: Boolean = true): ContentTransform {
        val enter = fadeIn(
            animationSpec = tween(TAB_DURATION_MS, easing = FastOutSlowInEasing)
        ) + slideInHorizontally(
            animationSpec = tween(TAB_DURATION_MS, easing = FastOutSlowInEasing),
            initialOffsetX = { fullWidth -> if (forward) (fullWidth / 6) else (-fullWidth / 6) }
        )

        val exit = fadeOut(
            animationSpec = tween(TAB_DURATION_MS - 30, easing = FastOutSlowInEasing)
        ) + slideOutHorizontally(
            animationSpec = tween(TAB_DURATION_MS - 30, easing = FastOutSlowInEasing),
            targetOffsetX = { fullWidth -> if (forward) (-fullWidth / 6) else (fullWidth / 6) }
        )

        return enter.togetherWith(exit)
    }

    /**
     * Vertical slide + fade for switching input modes (Password Mode <-> OTP Mode).
     */
    fun verticalModeTransition(downward: Boolean = true): ContentTransform {
        val enter = fadeIn(
            animationSpec = tween(ELEMENT_DURATION_MS, easing = FramerEase)
        ) + slideInVertically(
            animationSpec = tween(ELEMENT_DURATION_MS, easing = FramerEase),
            initialOffsetY = { h -> if (downward) (h / 4) else (-h / 4) }
        )

        val exit = fadeOut(
            animationSpec = tween(ELEMENT_DURATION_MS - 30, easing = FramerEase)
        ) + slideOutVertically(
            animationSpec = tween(ELEMENT_DURATION_MS - 30, easing = FramerEase),
            targetOffsetY = { h -> if (downward) (-h / 4) else (h / 4) }
        )

        return enter.togetherWith(exit)
    }
}
