import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';
import 'package:med_tracker/domain/models/my_med.dart';

import '../../providers/add_med_provider.dart';

class AddMedTypePageViewModel {
  AddMedTypePageViewModel({
    required this.addMedProvider,
    required VoidCallback onNext,
    required this.strings,
  })  : _onNext = onNext,
        selected = addMedProvider.myMed.type;

  final Strings strings;

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;

  /// Called when the user clicks the "next" button.
  final VoidCallback _onNext;

  /// The currently selected medication type.
  MedType selected;

  /// Whether or not the "next" button is enabled.
  bool get btnEnabled => selected != MedType.undefined;

  /// Called when the user clicks the "next" button.
  void onNext() {
    addMedProvider.myMed = addMedProvider.myMed.copyWith(type: selected);

    _onNext();
  }
}
