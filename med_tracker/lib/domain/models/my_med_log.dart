import 'package:med_tracker/utils/my_tools.dart';

class MyMedLog {
  MyMedLog(Map<String, List<DateTime>> log) : _log = log;

  Map<String, List<DateTime>> _log;

  Map<String, List<DateTime>> get log => _log;
  set log(Map<String, List<DateTime>> log) {
    if (MyTools.mapsMatch(_log, log)) return;

    _log = log;
  }

  bool get isEmpty => _log.isEmpty;

  bool get isNotEmpty => !isEmpty;
}
