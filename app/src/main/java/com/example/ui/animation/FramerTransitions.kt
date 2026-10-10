package com.example.ui.animation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Framer-motion inspired transition specifications:
 * - Tab & Screen Switching: subtle fade-in and slight slide-up animation
 *   (initial={{ opacity: 0, y: 8 }}, animate={{ opacity: 1, y: 0 }},
 *    exit={{ opacity: 0, y: -8 }}, transition={{ duration: 0.2, ease: "easeOut" }})
 * - Modals & Overlays: bottom-sheet slide up effect
 *   (initial={{ y: "100%" }}, animate={{ y: 0 }}, exit={{ y: "100%" }},
 *    transition={{ duration: 0.28, ease: "easeOut" }})
 */

/**
 * Creates a ContentTransform matching Framer Motion's tab/screen transition spec:
 * initial={ opacity: 0, y: 8 } -> animate={ opacity: 1, y: 0 } -> exit={ opacity: 0, y: -8 }
 * duration: 200ms, easing: FastOutSlowInEasing (easeOut equivalent)
 */
fun framerTabTransitionSpec(offsetDp: Dp = 8.dp, density: Float): ContentTransform {
    val offsetPx = (offsetDp.value * density).toInt()
    val enter = fadeIn(
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    ) + slideInVertically(
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        initialOffsetY = { offsetPx }
    )
    val exit = fadeOut(
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    ) + slideOutVertically(
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        targetOffsetY = { -offsetPx }
    )
    return enter.togetherWith(exit)
}

/**
 * High-level Composable wrapper for Tab & Screen switching using the Framer Motion transition spec.
 */
@Composable
fun <T> FramerAnimatedScreen(
    targetState: T,
    modifier: Modifier = Modifier,
    offsetDp: Dp = 8.dp,
    label: String = "FramerScreenTransition",
    content: @Composable (T) -> Unit
) {
    val density = LocalDensity.current.density
    AnimatedContent(
        targetState = targetState,
        modifier = modifier,
        transitionSpec = { framerTabTransitionSpec(offsetDp = offsetDp, density = density) },
        label = label
    ) { state ->
        content(state)
    }
}

/**
 * High-level Full-screen Modal Dialog wrapper featuring bottom-sheet slide up & slide down animation:
 * - Enter: slide up from y: "100%" to y: 0 with fade in (280ms duration)
 * - Exit: slide down from y: 0 to y: "100%" with fade out (240ms duration)
 *
 * Provides [dismissWithAnimation] lambda so back buttons and save actions can exit smoothly
 * without breaking state or event propagation.
 */
@Composable
fun SlideUpModalDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (dismissWithAnimation: () -> Unit) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    val dismissWithAnimation: () -> Unit = {
        coroutineScope.launch {
            isVisible = false
            delay(240) // Allow exit animation to finish smoothly
            onDismissRequest()
        }
    }

    Dialog(
        onDismissRequest = dismissWithAnimation,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        )
    ) {
        BackHandler { dismissWithAnimation() }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = if (isVisible) 0.5f else 0.0f))
        ) {
            AnimatedVisibility(
                visible = isVisible,
                enter = slideInVertically(
                    animationSpec = tween(durationMillis = 280, easing = FastOutSlowInEasing),
                    initialOffsetY = { fullHeight -> fullHeight }
                ) + fadeIn(animationSpec = tween(durationMillis = 200)),
                exit = slideOutVertically(
                    animationSpec = tween(durationMillis = 240, easing = FastOutSlowInEasing),
                    targetOffsetY = { fullHeight -> fullHeight }
                ) + fadeOut(animationSpec = tween(durationMillis = 180)),
                modifier = modifier.fillMaxSize()
            ) {
                content(dismissWithAnimation)
            }
        }
    }
}
