import 'package:flutter/material.dart';
import 'package:med_tracker/providers/my_theme_provider.dart';

/// This is used to control all of the logic on the settings screen.
class SettingsScreenViewModel extends ChangeNotifier {
  void toggleTheme(MyThemeProvider currentTheme) {
    if (currentTheme.themeType == ThemeType.light) {
      currentTheme.themeType = ThemeType.dark;
    } else {
      currentTheme.themeType = ThemeType.light;
    }
  }
}
