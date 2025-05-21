package com.example.mohamed_achek.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Typography
import androidx.compose.material3.Shapes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    onPrimary = Color.Black,
    primaryContainer = PurpleGrey80,
    onPrimaryContainer = Color.Black,
    secondary = PurpleGrey80,
    onSecondary = Color.Black,
    secondaryContainer = PurpleGrey40,
    onSecondaryContainer = Color.Black,
    tertiary = Pink80,
    onTertiary = Color.Black,
    background = Color(0xFF181A20),
    onBackground = Color.White,
    surface = Color(0xFF23252B),
    onSurface = Color.White,
    error = Color(0xFFFFB4AB),
    onError = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = Color.White,
    primaryContainer = PurpleGrey40,
    onPrimaryContainer = Color.White,
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    secondaryContainer = PurpleGrey80,
    onSecondaryContainer = Color.White,
    tertiary = Pink40,
    onTertiary = Color.White,
    background = Color(0xFFF5F5F5),
    onBackground = Color.Black,
    surface = Color(0xFFFFFFFF),
    onSurface = Color.Black,
    error = Color(0xFFB3261E),
    onError = Color.White
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)

private val AppTypography = Typography(
    displayLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    titleLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    bodyLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    )
)

@Composable
fun Mohamed_AchekTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
