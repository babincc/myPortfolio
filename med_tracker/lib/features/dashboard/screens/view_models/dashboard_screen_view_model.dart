import 'package:med_tracker/constants/strings/strings.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/domain/models/my_user.dart';
import 'package:med_tracker/domain/repos/my_med_repo.dart';
import 'package:med_tracker/providers/my_med_provider.dart';

import '../../providers/dashboard_day_provider.dart';

class DashboardScreenViewModel {
  DashboardScreenViewModel(this.strings);

  /// The strings used throughout the app.
  final Strings strings;

  /// Get all of the user's medications.
  ///
  /// Returns `null` if the user does not have any medications for today.
  Future<List<MyMed>?> fetchMedsFromDay({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
    required DashboardDayProvider dashboardDayProvider,
  }) =>
      MyMedRepo.fetchMedsFromDay(
        myUser: myUser,
        myMedProvider: myMedProvider,
        date: dashboardDayProvider.relativeDay,
      );

  /// Get all of the user's medications that are marked "as needed".
  ///
  /// Returns `null` if the user does not have any medications marked
  /// "as needed".
  Future<List<MyMed>?> fetchAsNeededMeds({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
  }) =>
      MyMedRepo.fetchAsNeededMeds(
        myUser: myUser,
        myMedProvider: myMedProvider,
      );
}
