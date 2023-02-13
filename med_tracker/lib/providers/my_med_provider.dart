import 'package:flutter/material.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/domain/models/my_med_log.dart';
import 'package:provider/provider.dart';

class MyMedProvider extends ChangeNotifier {
  /// All of the user's medications.
  ///
  /// This will be `null` if the medications have not been read from Firebase.
  ///
  /// It will be empty if Firebase has been read, and the user has no
  /// medications.
  List<MyMed>? allMeds;

  /// A history of all the medications the user has taken and logged with this
  /// app.
  ///
  /// This will be `null` if the medications have not been read from Firebase.
  ///
  /// It will be empty if Firebase has been read, and the user has no
  /// medications.
  MyMedLog? medLog;

  static MyMedProvider of(BuildContext context, {bool listen = false}) =>
      Provider.of<MyMedProvider>(context, listen: listen);
}
