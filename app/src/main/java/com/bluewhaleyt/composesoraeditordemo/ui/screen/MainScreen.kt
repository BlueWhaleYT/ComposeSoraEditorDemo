package com.bluewhaleyt.composesoraeditordemo.ui.screen

import android.graphics.Typeface
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bluewhaleyt.composesoraeditordemo.ScreenRoute
import com.bluewhaleyt.composesoraeditordemo.preference.PreferenceManager
import com.bluewhaleyt.composesoraeditordemo.ui.component.CodeEditor
import com.bluewhaleyt.composesoraeditordemo.ui.scheme.DynamicEditorColorScheme
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navController: NavController
) {
    ScreenContainer(
        title = "ComposeSoraEditorDemo",
        actions = {
            IconButton(onClick = { navController.navigate(ScreenRoute.Settings.name) }) {
                Icon(Icons.Default.Settings, null)
            }
        }
    ) {
        val fontSize = PreferenceManager.getEditorFontSize()
        CodeEditor(
            colorScheme = DynamicEditorColorScheme(
                colorScheme = MaterialTheme.colorScheme,
                handleColor = LocalTextSelectionColors.current.handleColor,
                selectionBackgroundColor = LocalTextSelectionColors.current.backgroundColor
            ),
            properties = {
                it.apply {
                    val font = Typeface.createFromAsset(context.assets, "fonts/JetBrainsMono.ttf")
                    typefaceText = font
                    typefaceLineNumber = font

                    setDividerMargin(30f, 0f)
                    lineNumberMarginLeft = 30f

                    setTextSize(fontSize.toFloat())
                }
            }
        )
    }
}