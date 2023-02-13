import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';

import '../../providers/add_med_provider.dart';

class AddMedSchedulePageViewModel {
  AddMedSchedulePageViewModel({
    required this.addMedProvider,
    required VoidCallback onNext,
    required this.strings,
  }) : _onNext = onNext;

  final Strings strings;

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;

  /// Called when the user clicks the "next" button.
  final VoidCallback _onNext;

  /// Whether or not the "next" button is enabled.
  bool get btnEnabled {
    // TODO
    return true;
  }

  /// Called when the user clicks the "next" button.
  void onNext() {
    // TODO

    _onNext();
  }
}
