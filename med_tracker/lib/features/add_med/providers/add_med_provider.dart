import 'package:flutter/material.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:provider/provider.dart';

class AddMedProvider extends ChangeNotifier {
  AddMedProvider()
      : _myMed = MyMed.empty().copyWith(frequency: MedFrequency.daily);

  MyMed _myMed;

  MyMed get myMed => _myMed;
  set myMed(MyMed myMed) {
    if (myMed == _myMed) return;

    _myMed = myMed;
    notifyListeners();
  }

  static AddMedProvider of(BuildContext context, {bool listen = false}) =>
      Provider.of<AddMedProvider>(context, listen: listen);
}
