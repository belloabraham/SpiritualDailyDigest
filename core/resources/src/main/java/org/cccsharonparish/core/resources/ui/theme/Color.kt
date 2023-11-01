package org.cccsharonparish.core.resources.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val primary50 = Color(0xffc2fcff)
val primary100 = Color(0xffa3f7ff)
val primary200 = Color(0xff7cf3fe)
val primary300 = Color(0xff3be6fc)
val primary400 = Color(0xff0bc2da)
val primary500 = Color(0xff019ab2)
val primary600 = Color(0xff03738c)
val primary700 = Color(0xff08596d)
val primary800 = Color(0xff0e4353)
val primary900 = Color(0xff0d3644)

val primaryDark50 = Color(0xffc8f6f9)
val primaryDark100 = Color(0xffacf0f6)
val primaryDark200 = Color(0xff89e8f0)
val primaryDark300 = Color(0xff4fd6e8)
val primaryDark400 = Color(0xff22b0c3)
val primaryDark500 = Color(0xff138da0)
val primaryDark600 = Color(0xff116a7e)
val primaryDark700 = Color(0xff145261)
val primaryDark800 = Color(0xff173e4a)
val primaryDark900 = Color(0xff16323c)

val tertiary50 = Color(0xfffff7eb)
val tertiary100 = Color(0xfffff0d6)
val tertiary200 = Color(0xffffd9a8)
val tertiary300 = Color(0xffffc37a)
val tertiary400 = Color(0xfffe9839)
val tertiary500 = Color(0xfffb7c13)
val tertiary600 = Color(0xffec6009)
val tertiary700 = Color(0xffc34709)
val tertiary800 = Color(0xff9d3810)
val tertiary900 = Color(0xff7e3010)


val tertiaryDark50 = Color(0xfffdf7ed)
val tertiaryDark100 = Color(0xfffbefda)
val tertiaryDark200 = Color(0xfff6d8b1)
val tertiaryDark300 = Color(0xfff2c288)
val tertiaryDark400 = Color(0xffea994d)
val tertiaryDark500 = Color(0xffe37e2b)
val tertiaryDark600 = Color(0xffd46621)
val tertiaryDark700 = Color(0xffae4e1e)
val tertiaryDark800 = Color(0xff8c4022)
val tertiaryDark900 = Color(0xff70361f)

val error500 = Color(0xffff2626)
val errorDark500 = Color(0xffe93a3a)

val white = Color(0xffffffff)
val black = Color(0xff000000)

val darkBackground = Color(0xff9bbed3).copy(alpha = 0.08f).compositeOver(Color(0xff121212))

val textPrimary = Color(0xff212121)
val textSecondary = Color(0xff757575)

val textPrimaryDark = Color(0xfffafafa)
val textSecondaryDark = Color(0xffe0e0e0)

val divider = Color(0xffbdbdbd)

val DarkThemeColours = darkColors(
    primary = primaryDark500,
    primaryVariant = primaryDark700,
    secondary = tertiaryDark500,
    secondaryVariant = tertiaryDark700,
    error = errorDark500,
    background = darkBackground,
    onBackground = textPrimaryDark,
    surface= darkBackground,
    onSurface= textPrimaryDark,
    onSecondary = textPrimaryDark,
    onPrimary = textPrimaryDark,
)

val LightThemeColours = lightColors(
    primary = primary500,
    primaryVariant = primary700,
    secondary = tertiary500,
    secondaryVariant = tertiary700,
    error = error500,
    background = white,
    onBackground = textPrimary,
    onSecondary = white,
    surface = primary100,
    onSurface = textPrimary,
    onPrimary = white
)