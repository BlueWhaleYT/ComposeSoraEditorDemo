package com.bluewhaleyt.composesoraeditordemo.ui.theme

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme()
        else -> lightColorScheme()
    }.animate()

    BetterSystemBarAppearance(colorScheme = colorScheme, darkTheme = darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
internal fun BetterSystemBarAppearance(
    colorScheme: ColorScheme,
    darkTheme: Boolean
) {
    val context = LocalContext.current
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (context as Activity).window
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val windowBackgroundColor = colorScheme.background.toArgb()
            window.setBackgroundDrawable(ColorDrawable(windowBackgroundColor))
            val insetsController = WindowCompat.getInsetsController(window, view)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = Color.Transparent.toArgb()
                insetsController.isAppearanceLightStatusBars = !darkTheme
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
                window.navigationBarColor = Color.Transparent.toArgb()
                insetsController.isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }
}

@Composable
fun ColorScheme.animate(
    animationSpec: AnimationSpec<Color> = spring(stiffness = Spring.StiffnessLow)
): ColorScheme {
    val colorScheme = this
    return colorScheme.copy(
        primary = animateColorAsState(colorScheme.primary, animationSpec).value,
        primaryContainer = animateColorAsState(colorScheme.primaryContainer, animationSpec).value,
        secondary = animateColorAsState(colorScheme.secondary, animationSpec).value,
        secondaryContainer = animateColorAsState(colorScheme.secondaryContainer, animationSpec).value,
        tertiary = animateColorAsState(colorScheme.tertiary, animationSpec).value,
        tertiaryContainer = animateColorAsState(colorScheme.tertiaryContainer, animationSpec).value,
        background = animateColorAsState(colorScheme.background, animationSpec).value,
        surface = animateColorAsState(colorScheme.surface, animationSpec).value,
        error = animateColorAsState(colorScheme.error, animationSpec).value,
        onPrimary = animateColorAsState(colorScheme.onPrimary, animationSpec).value,
        onSecondary = animateColorAsState(colorScheme.onSecondary, animationSpec).value,
        onTertiary = animateColorAsState(colorScheme.onTertiary, animationSpec).value,
        onBackground = animateColorAsState(colorScheme.onBackground, animationSpec).value,
        onSurface = animateColorAsState(colorScheme.onSurface, animationSpec).value,
        onError = animateColorAsState(colorScheme.onError, animationSpec).value,
    )
}