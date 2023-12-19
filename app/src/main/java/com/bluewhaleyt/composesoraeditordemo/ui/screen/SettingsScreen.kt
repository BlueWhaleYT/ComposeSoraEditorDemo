package com.bluewhaleyt.composesoraeditordemo.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.bluewhaleyt.composesoraeditordemo.preference.PreferenceEnum
import com.bluewhaleyt.composesoraeditordemo.preference.PreferenceKey
import me.zhanghai.compose.preference.listPreference
import me.zhanghai.compose.preference.preferenceCategory
import me.zhanghai.compose.preference.sliderPreference
import me.zhanghai.compose.preference.switchPreference
import me.zhanghai.compose.preference.textFieldPreference

@Composable
fun SettingsScreen(
    navController: NavController
) {
    ScreenContainer(
        title = "Settings",
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
            }
        }
    ) {
        SettingsContent()
    }
}

@Composable
fun SettingsContent() {
    LazyColumn {
        preferenceCategory(key = "category_app", title = { Text(text = "Application") })
        AppSettings()

        preferenceCategory(key = "category_editor", title = { Text(text = "Editor") })
        EditorSettings()
    }
}

fun LazyListScope.AppSettings() {
    listPreference(
        key = PreferenceKey.AppThemeMode.keyName,
        defaultValue = PreferenceKey.AppThemeMode.defaultValue,
        values = PreferenceEnum.Theme.entries.map { it.name },
        title = { Text(text = "Theme mode") }
    )
    switchPreference(
        key = PreferenceKey.AppDynamicColor.keyName,
        defaultValue = PreferenceKey.AppDynamicColor.defaultValue as Boolean,
        title = { Text(text = "Dynamic color") }
    )
}

fun LazyListScope.EditorSettings() {
    val fontSize = PreferenceKey.EditorFontSize.defaultValue as Float
    textFieldPreference(
        key = PreferenceKey.EditorFontSize.keyName,
        defaultValue = fontSize,
        title = { Text(text = "Font size") },
        summary = { Text(text = it.toString()) },
        textToValue = { it }
    )
}