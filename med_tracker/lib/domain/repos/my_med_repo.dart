import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/domain/models/my_med_log.dart';
import 'package:med_tracker/domain/models/my_user.dart';
import 'package:med_tracker/domain/services/my_med_service.dart';
import 'package:med_tracker/providers/my_med_provider.dart';

class MyMedRepo {
  /// Fetches all of the user's medications.
  ///
  /// Returns `null` if the user does not have any medications.
  static Future<List<MyMed>?> fetchAll({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
  }) async {
    // If the med provider has not been initialized, read Firebase and fill the
    // provider.
    myMedProvider.allMeds ??= await MyMedService.fetchAll(myUser) ?? [];

    // If the med provider has been initialized, and it is empty, return null.
    if (myMedProvider.allMeds!.isEmpty) return null;

    // Return the meds.
    return myMedProvider.allMeds;
  }

  /// Returns all of the user's medications they need to take today.
  static Future<List<MyMed>?> fetchTodaysMeds({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
  }) async =>
      fetchMedsFromDay(
        myUser: myUser,
        myMedProvider: myMedProvider,
        date: DateTime.now(),
      );

  /// Returns all of the user's medications they need to take on the given
  /// `date`.
  static Future<List<MyMed>?> fetchMedsFromDay({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
    required DateTime date,
  }) async {
    /// All of the user's medications.
    List<MyMed>? allMeds = await fetchAll(
      myUser: myUser,
      myMedProvider: myMedProvider,
    );

    // If the user has no medications, return null.
    if (allMeds == null) return null;

    List<MyMed> toReturn = [];

    // Go through each medication and get the ones with the given `date`.
    for (MyMed med in allMeds) {
      /// How the minimum amount of time the user should go without taking this
      /// medication.
      int frequencyInDays;

      // Get this medication's frequency.
      switch (med.frequency) {
        case MedFrequency.daily:
          toReturn.add(med);
          continue;
        case MedFrequency.everyOtherDay:
          frequencyInDays = 2;
          break;
        case MedFrequency.weekly:
          frequencyInDays = 7;
          break;
        case MedFrequency.monthly:
          frequencyInDays = 30;
          break;
        case MedFrequency.annually:
          frequencyInDays = 365;
          break;
        case MedFrequency.asNeeded:
        case MedFrequency.undefined:
        default:
          continue;
      }

      // If the medical log is null, move on.
      if (myMedProvider.medLog == null) continue;

      // If the medication is not in the log, the user hasn't taken it, and they
      // should take it today.
      if (myMedProvider.medLog!.isEmpty ||
          med.lastUse(myMedProvider.medLog!) == null) {
        toReturn.add(med);
      }

      // See if the user has waited long enough to take their meds today.
      if (date.difference(med.lastUse(myMedProvider.medLog!)!).inDays >=
          frequencyInDays) {
        toReturn.add(med);
      }
    }

    if (toReturn.isEmpty) return null;
    return toReturn;
  }

  /// Returns the user's medical history log.
  static Future<MyMedLog?> fetchMedLog({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
  }) async {
    // If the med log provider has not been initialized, read Firebase and fill
    // the provider.
    myMedProvider.medLog ??=
        await MyMedService.fetchMedLog(myUser) ?? MyMedLog({});

    // If the med log provider has been initialized, and it is empty, return
    // null.
    if (myMedProvider.medLog!.isEmpty) return null;

    // Return the meds.
    return myMedProvider.medLog;
  }

  static Future<List<MyMed>?> fetchAsNeededMeds({
    required MyUser myUser,
    required MyMedProvider myMedProvider,
  }) async {
    /// All of the user's medications.
    List<MyMed>? allMeds = await fetchAll(
      myUser: myUser,
      myMedProvider: myMedProvider,
    );

    /// If the user has no medications, return null.
    if (allMeds == null) return null;

    List<MyMed> toReturn = [];

    // Go through each medication and get the ones marked, "as needed".
    for (MyMed med in allMeds) {
      if (med.frequency == MedFrequency.asNeeded) {
        toReturn.add(med);
      }
    }

    if (toReturn.isEmpty) return null;

    return toReturn;
  }
}
