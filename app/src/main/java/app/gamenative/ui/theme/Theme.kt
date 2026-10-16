package app.gamenative.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.alorma.compose.settings.ui.base.internal.SettingsTileColors
import com.alorma.compose.settings.ui.base.internal.SettingsTileDefaults

@Immutable
data class PluviaColors(
    val statusInstalled: Color,
    val statusDownloading: Color,
    val statusAvailable: Color,
    val statusAway: Color,
    val statusOffline: Color,
    val friendOnline: Color,
    val friendOffline: Color,
    val friendInGame: Color,
    val friendAwayOrSnooze: Color,
    val friendInGameAwayOrSnooze: Color,
    val friendBlocked: Color,
    val accentCyan: Color,
    val accentPurple: Color,
    val accentPink: Color,
    val accentSuccess: Color,
    val accentWarning: Color,
    val accentDanger: Color,
    val surfacePanel: Color,
    val surfaceElevated: Color,
    val borderDefault: Color,
    val textMuted: Color,
    val compatibilityGood: Color,
    val compatibilityGoodBackground: Color,
    val compatibilityPartial: Color,
    val compatibilityPartialBackground: Color,
    val compatibilityUnknown: Color,
    val compatibilityUnknownBackground: Color,
    val compatibilityBad: Color,
    val compatibilityBadBackground: Color,
)

// ==================== AMOLED DARK PALETTE ====================
private val DarkPluviaColors = PluviaColors(
    statusInstalled = NeonWhite,
    statusDownloading = NeonWhiteDim,
    statusAvailable = NeonWhite,
    statusAway = NeonWhiteDim,
    statusOffline = NeonWhiteSubtle,

    friendOnline = NeonWhite,
    friendOffline = NeonWhiteSubtle,
    friendInGame = NeonWhite,
    friendAwayOrSnooze = NeonWhiteDim,
    friendInGameAwayOrSnooze = NeonWhiteDim,
    friendBlocked = NeonError,

    accentCyan = NeonWhite,
    accentPurple = NeonWhiteDim,
    accentPink = NeonWhite,
    accentSuccess = NeonWhite,
    accentWarning = NeonWhiteDim,
    accentDanger = NeonError,

    surfacePanel = AmoledSurface,
    surfaceElevated = AmoledElevated,

    borderDefault = NeonWhiteGlow,
    textMuted = NeonWhiteSubtle,

    compatibilityGood = NeonWhite,
    compatibilityGoodBackground = AmoledSurface,
    compatibilityPartial = NeonWhiteDim,
    compatibilityPartialBackground = AmoledSurface,
    compatibilityUnknown = NeonWhiteSubtle,
    compatibilityUnknownBackground = AmoledSurface,
    compatibilityBad = NeonError,
    compatibilityBadBackground = AmoledSurface,
)

val BrandGradient = listOf(NeonWhite, NeonWhiteDim, NeonWhiteSubtle)

private val LocalPluviaColors = staticCompositionLocalOf { DarkPluviaColors }

// ==================== AMOLED MATERIAL3 SCHEME ====================
private val DarkColorScheme = darkColorScheme(
    primary = NeonWhite,
    onPrimary = AmoledBlack,
    primaryContainer = NeonWhiteFaint,
    onPrimaryContainer = NeonWhite,

    secondary = NeonWhiteDim,
    onSecondary = AmoledBlack,
    secondaryContainer = NeonWhiteFaint,
    onSecondaryContainer = NeonWhite,

    tertiary = NeonWhite,
    onTertiary = AmoledBlack,
    tertiaryContainer = NeonWhiteFaint,
    onTertiaryContainer = NeonWhite,

    background = AmoledBlack,
    onBackground = NeonWhite,

    surface = AmoledBlack,
    onSurface = NeonWhite,
    surfaceVariant = AmoledSurface,
    onSurfaceVariant = NeonWhiteDim,
    surfaceTint = NeonWhite,

    inverseSurface = NeonWhite,
    inverseOnSurface = AmoledBlack,
    inversePrimary = AmoledBlack,

    error = NeonError,
    onError = AmoledBlack,
    errorContainer = NeonError.copy(alpha = 0.2f),
    onErrorContainer = NeonWhite,

    outline = NeonWhiteGlow,
    outlineVariant = NeonWhiteFaint,

    scrim = Color.Black.copy(alpha = 0.8f),
    surfaceBright = AmoledElevated,
    surfaceDim = AmoledBlack,
    surfaceContainer = AmoledSurface,
    surfaceContainerHigh = AmoledElevated,
    surfaceContainerHighest = AmoledElevated,
    surfaceContainerLow = AmoledBlack,
    surfaceContainerLowest = AmoledBlack,
)

@Composable
fun PluviaTheme(
    content: @Composable () -> Unit,
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val window = (view.context as Activity).window
        val insetsController = WindowCompat.getInsetsController(window, view)
        insetsController.isAppearanceLightStatusBars = false
        insetsController.isAppearanceLightNavigationBars = false
    }

    CompositionLocalProvider(LocalPluviaColors provides DarkPluviaColors) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = PluviaTypography,
            content = content,
        )
    }
}

object PluviaTheme {
    val colors: PluviaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPluviaColors.current
}

object DarkColors {
    val statusInstalled = NeonWhite
    val statusDownloading = NeonWhiteDim
    val statusAvailable = NeonWhite
    val statusAway = NeonWhiteDim
    val statusOffline = NeonWhiteSubtle

    val friendOnline = NeonWhite
    val friendOffline = NeonWhiteSubtle
    val friendInGame = NeonWhite
    val friendAwayOrSnooze = NeonWhiteDim
    val friendInGameAwayOrSnooze = NeonWhiteDim
    val friendBlocked = NeonError

    val accentCyan = NeonWhite
    val accentPurple = NeonWhiteDim
    val accentPink = NeonWhite
    val accentSuccess = NeonWhite
    val accentWarning = NeonWhiteDim
    val accentDanger = NeonError

    val surfacePanel = AmoledSurface
    val surfaceElevated = AmoledElevated

    val borderDefault = NeonWhiteGlow
    val textMuted = NeonWhiteSubtle

    val compatibilityGood = NeonWhite
    val compatibilityGoodBackground = AmoledSurface
    val compatibilityPartial = NeonWhiteDim
    val compatibilityPartialBackground = AmoledSurface
    val compatibilityUnknown = NeonWhiteSubtle
    val compatibilityUnknownBackground = AmoledSurface
    val compatibilityBad = NeonError
    val compatibilityBadBackground = AmoledSurface
}

@Composable
fun settingsTileColors(): SettingsTileColors = SettingsTileDefaults.colors(
    titleColor = NeonWhite,
    subtitleColor = NeonWhiteSubtle,
    actionColor = NeonWhite,
)

@Composable
fun settingsTileColorsAlt(): SettingsTileColors = SettingsTileDefaults.colors(
    titleColor = NeonWhite,
    subtitleColor = NeonWhiteSubtle,
)

@Composable
fun settingsTileColorsDebug(): SettingsTileColors = SettingsTileDefaults.colors(
    titleColor = NeonError,
    subtitleColor = NeonWhiteSubtle,
    actionColor = NeonWhite,
)