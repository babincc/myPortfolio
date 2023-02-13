import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';

import '../../providers/add_med_provider.dart';

class AddMedStrengthPageViewModel {
  AddMedStrengthPageViewModel({
    required this.addMedProvider,
    required VoidCallback onNext,
    required this.strings,
  }) : _onNext = onNext;

  final Strings strings;

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;

  /// The controller for the "medication strength" text field.
  final TextEditingController strengthController = TextEditingController();

  /// Called when the user clicks the "next" button.
  final VoidCallback _onNext;

  /// Whether or not the "next" button is enabled.
  bool get btnEnabled => strengthController.text.isNotEmpty;

  /// Called when the user clicks the "next" button.
  void onNext() {
    addMedProvider.myMed = addMedProvider.myMed
        .copyWith(strength: double.tryParse(strengthController.text.trim()));

    _onNext();
  }
}
