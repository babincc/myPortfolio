import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../domain/models/dashboard_day.dart';

class DashboardDayProvider extends ChangeNotifier {
  DashboardDayProvider() : _day = DateTime.now().weekday;

  /// The currently selected day.
  ///
  /// `1` is Monday ... `7` is Sunday
  int _day;

  /// The currently selected day as a three letter string.
  ///
  /// This is first initialized to today.
  String get day => DashboardDay.intToDay(_day);
  set day(String day) {
    int temp = DashboardDay.dayToInt(day);

    if (temp == _day) return;

    _day = temp;
    notifyListeners();
  }

  /// The date that corresponds to [day].
  ///
  /// This is calculated relative to today's date.
  DateTime get relativeDay {
    DateTime now = DateTime.now();

    // If today is the currently selected day, return today.
    if (_day == now.weekday) return now;

    // If today is Sunday, we handle it differently since sunday is an edge
    // case. [DateTime] treats Sunday as the last day of the week; however, this
    // app treats Sunday as the first day of the week.
    if (now.weekday == DateTime.sunday) return now.add(Duration(days: _day));

    // Just like above, we need to handle the case of the currently selected day
    // being Sunday.
    if (_day == DateTime.sunday) {
      return now.subtract(Duration(days: now.weekday));
    }

    // If the currently selected day is in the past, do this.
    if (_day < now.weekday) {
      return now.subtract(Duration(days: now.weekday - _day));
    }

    // At this point, the currently selected day is in the future.
    return now.add(Duration(days: _day - now.weekday));
  }

  static DashboardDayProvider of(BuildContext context, {bool listen = false}) =>
      Provider.of<DashboardDayProvider>(context, listen: listen);
}
