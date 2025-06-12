package com.example.wearablellm.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.wearablellm.presentation.theme.WearableLlmTheme
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices
import com.example.wearablellm.R
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)
        setContent {
            WearableLlmTheme {
                WearAppWithButton("Android")
            }
        }
    }
}

@Composable
fun WearAppWithButton(greetingName: String) {
        HoldButtonExample()
}

@Composable
fun Greeting(greetingName: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
        text = stringResource(R.string.hello_world, greetingName)
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HoldButtonExample() {
    var isPressed by remember { mutableStateOf(false) }
    val backgroundColor = if (isPressed) Color.Red else Color.Black
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { /* no-op */ },
            modifier = Modifier
                .size(80.dp)
                .pointerInteropFilter {
                    when (it.action) {
                        android.view.MotionEvent.ACTION_DOWN -> {
                            isPressed = true
                            true
                        }
                        android.view.MotionEvent.ACTION_UP,
                        android.view.MotionEvent.ACTION_CANCEL -> {
                            isPressed = false
                            true
                        }
                        else -> false
                    }
                },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            shape = CircleShape,
        ) {
            Text(text = "Hold me", color = Color.White)
        }
    }
}

@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
@Composable
fun PreviewWearAppWithButton() {
    WearableLlmTheme {
        WearAppWithButton("Preview Android")
    }
}