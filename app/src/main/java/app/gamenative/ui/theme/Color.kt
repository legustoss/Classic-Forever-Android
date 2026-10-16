package app.gamenative.ui.theme

import androidx.compose.ui.graphics.Color

// ==================== AMOLED NEON PALETTE ====================
val AmoledBlack = Color(0xFF000000)        // чистый чёрный
val AmoledSurface = Color(0xFF050505)      // почти чёрный для карточек
val AmoledElevated = Color(0xFF0A0A0A)     // чуть светлее для выделенных элементов

val NeonWhite = Color(0xFFFFFFFF)          // основной акцент
val NeonWhiteDim = Color(0xFFB0B0B0)       // вторичный текст
val NeonWhiteGlow = Color(0x66FFFFFF)      // 40% белый для неоновой обводки
val NeonWhiteSubtle = Color(0xFF606060)    // третичный текст
val NeonWhiteFaint = Color(0x1AFFFFFF)     // 10% белый для фоновых акцентов

val NeonError = Color(0xFFFF4444)          // красный только для ошибок

// ==================== ГРАДИЕНТ ФОНА ====================
// Чистый AMOLED — без градиента, только чёрный
val WowBackgroundGradient = listOf(AmoledBlack, AmoledBlack, AmoledBlack)

// ==================== СОВМЕСТИМОСТЬ (старые имена) ====================
// Оставляем старые имена, чтобы не ломать другие экраны
val WowGold = NeonWhite
val WowBronze = NeonWhiteDim
val WowMuted = NeonWhiteSubtle
val WowSubtle = NeonWhiteSubtle
val WowError = NeonError

// ==================== ОРИГИНАЛЬНАЯ ПАЛИТРА PLUVIA ====================
// Оставляем как есть, чтобы не сломать остальное приложение
val customBackground = Color(0xFF09090B)
val customForeground = Color(0xFFFAFAFA)
val customCard = Color(0xFF09090B)
val customCardForeground = Color(0xFFFAFAFA)
val customPrimary = Color(0xFFA21CAF)
val customPrimaryForeground = Color(0xFFFAFAFA)
val customSecondary = Color(0xFF27272A)
val customSecondaryForeground = Color(0xFFFAFAFA)
val customMuted = Color(0xFF27272A)
val customMutedForeground = Color(0xFF94969C)
val customAccent = Color(0xFF06B6D4)
val customAccentForeground = Color(0xFFFAFAFA)
val customDestructive = Color(0xFF7F1D1D)

val pluviaSeedColor = Color(0x284561FF)

val PluviaPrimary = Color(0xFFA21CAF)
val PluviaSeed = Color(0x284561FF)

val PluviaBackground = Color(0xFF09090B)
val PluviaSurface = Color(0xFF12121A)
val PluviaSurfaceElevated = Color(0xFF1A1A24)
val PluviaCard = Color(0xFF09090B)

val PluviaForeground = Color(0xFFFAFAFA)
val PluviaForegroundMuted = Color(0xFF94969C)

val PluviaSecondary = Color(0xFF27272A)

val PluviaCyan = Color(0xFF00D4FF)
val PluviaPurple = Color(0xFF8B5CF6)
val PluviaPink = Color(0xFFEC4899)

val PluviaSuccess = Color(0xFF10B981)
val PluviaWarning = Color(0xFFF59E0B)
val PluviaDanger = Color(0xFFEF4444)
val PluviaDestructive = Color(0xFF7F1D1D)

val PluviaBorder = Color(0xFF3A3A4A)

val StatusInstalled = Color(0xFF4CAF50)
val StatusDownloading = Color(0xFF00BCD4)
val StatusAvailable = Color(0xFF2196F3)
val StatusAway = Color(0xFFFF9800)
val StatusOffline = Color(0xFF9E9E9E)

val FriendOnline = Color(0xFF6DCFF6)
val FriendOffline = Color(0xFF7A7A7A)
val FriendInGame = Color(0xFF90BA3C)
val FriendAwayOrSnooze = Color(0x806DCFF6)
val FriendInGameAwayOrSnooze = Color(0x8090BA3C)
val FriendBlocked = Color(0xFF983D3D)

val CompatibilityGood = Color(0xFF4CAF50)
val CompatibilityGoodBg = Color(0xFF1B5E20)
val CompatibilityPartial = Color(0xFF8BC34A)
val CompatibilityPartialBg = Color(0xFF33691E)
val CompatibilityUnknown = Color(0xFF9E9E9E)
val CompatibilityUnknownBg = Color(0xFF424242)
val CompatibilityBad = Color(0xFFEF5350)
val CompatibilityBadBg = Color(0xFFB71C1C)