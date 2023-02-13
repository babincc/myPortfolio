import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';

import '../../providers/add_med_provider.dart';

class AddMedNamePageViewModel {
  AddMedNamePageViewModel({
    required this.addMedProvider,
    required VoidCallback onNext,
    required this.strings,
  })  : _onNext = onNext,
        nameController = TextEditingController(text: addMedProvider.myMed.name);

  final Strings strings;

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;

  /// Called when the user clicks the "next" button.
  final VoidCallback _onNext;

  /// The controller for the "medication name" text field.
  final TextEditingController nameController;

  /// Whether or not the "next" button is enabled.
  bool get btnEnabled => nameController.text.isNotEmpty;

  /// Called when the user clicks the "next" button.
  void onNext() {
    addMedProvider.myMed =
        addMedProvider.myMed.copyWith(name: nameController.text.trim());

    _onNext();
  }
}
