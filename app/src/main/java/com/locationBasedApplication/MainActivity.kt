package com.locationBasedApplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.locationBasedApplication.ui.theme.OrangeLocationTheme
import com.locationBasedApplication.ui.nav.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrangeLocationTheme {
                NavGraph()
            }
        }
    }
}
