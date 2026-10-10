package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ui.DispatchViewModel
import com.example.ui.DispatchViewModelFactory
import com.example.ui.animation.MotionTransitions
import com.example.ui.components.GlobalLoadingOverlay
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.SplashScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.util.GlobalLoadingManager
import com.example.util.SessionManager

enum class AppDestination {
    SPLASH,
    AUTH,
    HOME
}

class MainActivity : ComponentActivity() {

    private val viewModel: DispatchViewModel by viewModels {
        val app = application as SevaMitraApplication
        DispatchViewModelFactory(app.repository)
    }

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by remember { mutableStateOf(sessionManager.isDarkModeEnabled()) }

            MyApplicationTheme(darkTheme = isDarkMode) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        var currentDestination by remember { mutableStateOf(AppDestination.SPLASH) }

                        AnimatedContent(
                            targetState = currentDestination,
                            transitionSpec = {
                                val forward = targetState.ordinal >= initialState.ordinal
                                MotionTransitions.pageTransition(forward)
                            },
                            label = "PageDestinationTransition"
                        ) { destination ->
                            when (destination) {
                                AppDestination.SPLASH -> {
                                    SplashScreen(
                                        onSplashFinished = {
                                            if (sessionManager.isLoggedIn() && sessionManager.getUserPhone().isNotBlank()) {
                                                viewModel.onUserLoggedIn(sessionManager.getUserPhone())
                                                currentDestination = AppDestination.HOME
                                            } else {
                                                viewModel.onUserLoggedOut()
                                                currentDestination = AppDestination.AUTH
                                            }
                                        }
                                    )
                                }

                                AppDestination.AUTH -> {
                                    AuthScreen(
                                        sessionManager = sessionManager,
                                        onLoginSuccess = {
                                            viewModel.onUserLoggedIn(sessionManager.getUserPhone())
                                            currentDestination = AppDestination.HOME
                                        }
                                    )
                                }

                                AppDestination.HOME -> {
                                    HomeScreen(
                                        viewModel = viewModel,
                                        sessionManager = sessionManager,
                                        isDarkMode = isDarkMode,
                                        onToggleDarkMode = { newMode ->
                                            isDarkMode = newMode
                                            sessionManager.setDarkModeEnabled(newMode)
                                        },
                                        onLogout = {
                                            viewModel.onUserLoggedOut()
                                            currentDestination = AppDestination.AUTH
                                        }
                                    )
                                }
                            }
                        }
                    }

                    val isGlobalLoading by GlobalLoadingManager.isLoading.collectAsState()
                    val globalLoadingMsg by GlobalLoadingManager.loadingMessage.collectAsState()
                    GlobalLoadingOverlay(
                        isLoading = isGlobalLoading,
                        message = globalLoadingMsg
                    )
                }
            }
        }
    }
}
