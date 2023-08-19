package org.cccsharonparish.spiritualdailydigest.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver


val primary50 = Color(0xfffaf5f2)
val primary100 = Color(0xfff4e9e0)
val primary200 = Color(0xffe8d1c0)
val primary300 = Color(0xffdab297)
val primary400 = Color(0xffca8e6d)
val primary500 = Color(0xffbf7350)
val primary600 = Color(0xffb15f45)
val primary700 = Color(0xff944c3b)
val primary800 = Color(0xff773e35)
val primary900 = Color(0xff61352d)

val primaryDark50 = Color(0xfff7f3ed)
val primaryDark100 = Color(0xfff2ece2)
val primaryDark200 = Color(0xffe4d6c4)
val primaryDark300 = Color(0xffd3bb9e)
val primaryDark400 = Color(0xffc09b77)
val primaryDark500 = Color(0xffb3845c)
val primaryDark600 = Color(0xffa67250)
val primaryDark700 = Color(0xff8a5b44)
val primaryDark800 = Color(0xff704b3c)
val primaryDark900 = Color(0xff5b3f33)

val tertiary50 = Color(0xfffff4ed)
val tertiary100 = Color(0xffffe6d4)
val tertiary200 = Color(0xffffc8a8)
val tertiary300 = Color(0xffffa270)
val tertiary400 = Color(0xffff6f37)
val tertiary500 = Color(0xffff5722)
val tertiary600 = Color(0xfff02e06)
val tertiary700 = Color(0xffc71e07)
val tertiary800 = Color(0xff9e1a0e)
val tertiary900 = Color(0xff7f190f)

val tertiaryDark50 = Color(0xfffdf5ed)
val tertiaryDark100 = Color(0xfffaebdb)
val tertiaryDark200 = Color(0xfff4d3b3)
val tertiaryDark300 = Color(0xffecb283)
val tertiaryDark400 = Color(0xffe48b53)
val tertiaryDark500 = Color(0xffdd6d31)
val tertiaryDark600 = Color(0xffc05126)
val tertiaryDark700 = Color(0xffa84324)
val tertiaryDark800 = Color(0xff883725)
val tertiaryDark900 = Color(0xff6d3122)

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
    background = primary50,
    onBackground = textPrimary,
    onSecondary = white,
    surface = primary100,
    onSurface = textPrimary,
    onPrimary = white
)