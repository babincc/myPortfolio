import 'package:intl/intl.dart';

/// This model makes it easy to work with days as three letter names.
class DashboardDay {
  /// The days of the week as [DateTime] sees them.
  ///
  /// Monday is 1 ... Sunday is 7.
  static const List<String> _days = [
    "invalid",
    "mon",
    "tue",
    "wed",
    "thu",
    "fri",
    "sat",
    "sun",
  ];

  /// Returns today's name as a three letter string.
  static String currentDayStr() => DateFormat.E().format(DateTime.now());

  /// Returns the name that corresponds to `day` as a three letter string.
  static String intToDay(int day) {
    assert(day >= 1 && day <= 7, "ERROR: '$day' is an invalid day!");

    return _days[day];
  }

  /// Returns the number that corresponds to `day`.
  static int dayToInt(String day) {
    String dayLower = day.toLowerCase();
    assert(_days.contains(dayLower), "ERROR: '$day' is an invalid day!");

    int index = _days.indexOf(dayLower);

    return index;
  }

  /// All of the days as three letter names, starting with Monday.
  static List<String> days() => _days.sublist(1);

  /// All of the days as three letter names, starting with `day`.
  static List<String> daysFrom(int day) {
    String dayStr = intToDay(day);

    int index = _days.indexOf(dayStr);
    return [..._days.sublist(index), ..._days.sublist(1, index)];
  }
}
