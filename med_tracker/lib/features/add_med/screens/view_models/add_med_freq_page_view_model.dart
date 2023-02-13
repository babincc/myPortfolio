import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';
import 'package:med_tracker/domain/models/my_med.dart';

import '../../providers/add_med_provider.dart';

class AddMedFreqPageViewModel {
  AddMedFreqPageViewModel({
    required this.strings,
    required this.addMedProvider,
    int? intervalStartIndex,
  })  : intervalController =
            FixedExtentScrollController(initialItem: intervalStartIndex ?? 0),
        selectedFreq =
            identical(addMedProvider.myMed.frequency, MedFrequency.asNeeded)
                ? strings.asNeeded
                : strings.regularly,
        selectedIntervalIndex = intervalStartIndex ?? 0,
        time = [];

  final Strings strings;

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;

  /// The controller for the interval selector wheel.
  FixedExtentScrollController intervalController;

  /// The currently selected frequency.
  String selectedFreq;

  /// The index of the currently selected interval.
  ///
  /// This is included so the index will persist across state changes. If the
  /// intervalController is re-built, we can't depend on it to keep track.
  int selectedIntervalIndex;

  /// The currently set times that this medication will be taken.
  List<TimeOfDay> time;

  /// Called when the user clicks the "done" button.
  void onDone() {
    if (selectedFreq == MedFrequency.asNeeded.value) {
      addMedProvider.myMed =
          addMedProvider.myMed.copyWith(frequency: MedFrequency.asNeeded);
    } else if (selectedFreq == strings.regularly) {
      final int index = intervalController.selectedItem;

      addMedProvider.myMed = addMedProvider.myMed
          .copyWith(frequency: MedFrequency.regulars[index]);
    }
  }
}
