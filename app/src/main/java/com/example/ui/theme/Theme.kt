package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = Color(0xFF1A1200),
    primaryContainer = Color(0xFF4A3400),
    onPrimaryContainer = GoldLight,
    secondary = GoldSecondary,
    onSecondary = Color(0xFF1C1300),
    secondaryContainer = Color(0xFF3B2A08),
    onSecondaryContainer = Color(0xFFFFDF9E),
    tertiary = GoldAccent,
    onTertiary = Color.Black,
    background = DarkBackground,
    onBackground = TextLight,
    surface = DarkSurface,
    onSurface = TextLight,
    surfaceVariant = DarkSurfaceElevated,
    onSurfaceVariant = Color(0xFFD4D8E2),
    outline = DarkBorder,
    outlineVariant = DarkBorderLight
)

private val LightColorScheme = lightColorScheme(
    primary = GoldDark,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFECC0),
    onPrimaryContainer = Color(0xFF332000),
    secondary = GoldSecondary,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFFF0D4),
    onSecondaryContainer = Color(0xFF3D2600),
    tertiary = GoldAccent,
    onTertiary = Color.Black,
    background = LightBackground,
    onBackground = TextDark,
    surface = LightSurface,
    onSurface = TextDark,
    surfaceVariant = LightSurfaceElevated,
    onSurfaceVariant = Color(0xFF474C5A),
    outline = LightBorder,
    outlineVariant = Color(0xFFCBD1DE)
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep branded luxury gold/black theme consistent
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
