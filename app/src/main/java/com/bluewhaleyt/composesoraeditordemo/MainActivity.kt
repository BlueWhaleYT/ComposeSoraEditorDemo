package com.bluewhaleyt.composesoraeditordemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.bluewhaleyt.composesoraeditordemo.preference.PreferenceManager
import com.bluewhaleyt.composesoraeditordemo.ui.theme.AppTheme
import me.zhanghai.compose.preference.ProvidePreferenceFlow
import me.zhanghai.compose.preference.ProvidePreferenceLocals
import me.zhanghai.compose.preference.ProvidePreferenceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvidePreferenceLocals {
                AppTheme(
                    darkTheme = PreferenceManager.getThemeMode(),
                    dynamicColor = PreferenceManager.isDynamicColorEnabled()
                ) {
                    App()
                }
            }
        }
    }
}