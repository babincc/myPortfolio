import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart';
import 'package:flutter/services.dart';
import 'package:med_tracker/constants/theme/my_colors.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/domain/services/my_theme_service.dart';
import 'package:provider/provider.dart';

/// The main brightness level of the app's UI.
enum ThemeType {
  /// All of the main background colors will be dark with lighter foreground
  /// colors for contrast.
  dark,

  /// All of the main background colors will be light with darker foreground
  /// colors for contrast.
  light,
}

class MyThemeProvider extends ChangeNotifier {
  MyThemeProvider() : _themeType = ThemeType.dark {
    initThemeType();
  }

  /// Describes the main brightness level of the UI throughout the app.
  ThemeType _themeType;

  set themeType(ThemeType themeType) {
    _themeType = themeType;
    MyThemeService.cacheThemeType(themeType).then((value) => notifyListeners());
  }

  ThemeType get themeType => _themeType;

  MyColors get colors => MyColors(themeType);

  /// Returns the app UI's theme data based on the selected [_themeType].
  ThemeData get themeData => ThemeData(
        useMaterial3: true,
        colorScheme: colors.colorScheme,
        appBarTheme: AppBarTheme(
          systemOverlayStyle: SystemUiOverlayStyle(
            statusBarIconBrightness: _themeType == ThemeType.light
                ? Brightness.dark
                : Brightness.light, // For Android (dark == dark icons)
            statusBarBrightness: _themeType == ThemeType.light
                ? Brightness.light
                : Brightness.dark, // For iOS (light == dark icons)
          ),
        ),
        canvasColor: colors.colorScheme.background,
      );

  /// The decoration for all of the text fields used in this app.
  InputDecoration myInputDecoration({
    String? label,
    String? hint,
    Icon? prefixIcon,
    String? errorText,
  }) {
    OutlineInputBorder border = OutlineInputBorder(
      borderSide: BorderSide.none,
      borderRadius: BorderRadius.circular(MyMeasurements.borderRadius),
      gapPadding: MyMeasurements.textPadding,
    );

    return InputDecoration(
      labelText: label,
      floatingLabelBehavior: FloatingLabelBehavior.always,
      prefixIcon: prefixIcon,
      hintText: hint,
      errorText: errorText,
      contentPadding: const EdgeInsets.symmetric(
        horizontal: MyMeasurements.textPadding * 2,
        vertical: MyMeasurements.textPadding,
      ),
      filled: true,
      fillColor: colors.textField,
      enabledBorder: border,
      focusedBorder: border,
      errorBorder: border,
      focusedErrorBorder: border,
    );
  }

  /// Checks the phone for which theme it should apply to the app.
  Future<void> initThemeType() async {
    // See if the user has a theme preference saved in their local files.
    ThemeType? tempThemeType = await MyThemeService.cachedThemeType;
    if (tempThemeType != null) {
      themeType = tempThemeType;
      return;
    }

    // If there is no saved preference, try to match the phone's theme.
    var brightness = SchedulerBinding.instance.window.platformBrightness;
    bool isDarkMode = brightness == Brightness.dark;
    if (isDarkMode) {
      themeType = ThemeType.dark;
    } else {
      themeType = ThemeType.light;
    }
  }

  static MyThemeProvider of(BuildContext context, {bool listen = false}) =>
      Provider.of<MyThemeProvider>(context, listen: listen);
}