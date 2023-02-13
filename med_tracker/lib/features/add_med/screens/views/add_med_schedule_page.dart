import 'package:flutter/material.dart';
import 'package:med_tracker/constants/assets.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/providers/my_string_provider.dart';
import 'package:med_tracker/providers/my_theme_provider.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_text.dart';

import '../../providers/add_med_provider.dart';
import '../../widgets/add_med_img.dart';
import '../view_models/add_med_schedule_page_view_model.dart';
import 'add_med_freq_page.dart';

class AddMedSchedulePage extends StatefulWidget {
  const AddMedSchedulePage({super.key, required this.onNext});

  final VoidCallback onNext;

  @override
  State<AddMedSchedulePage> createState() => _AddMedSchedulePageState();
}

class _AddMedSchedulePageState extends State<AddMedSchedulePage> {
  /// All of the logic that controls this page's UI.
  late final AddMedSchedulePageViewModel viewModel;

  @override
  void initState() {
    viewModel = AddMedSchedulePageViewModel(
      addMedProvider: AddMedProvider.of(context),
      onNext: widget.onNext,
      strings: MyStringProvider.of(context).strings,
    );

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // IMAGE
        const AddMedImg(Assets.addMedSchedule),

        // TITLE
        MyText(
          MyTools.capitalizeEachWord(viewModel.strings.medSchedule),
          myTextStyle: MyTextStyle.title,
        ),

        const SizedBox(height: MyMeasurements.elementSpread),

        // FREQUENCY field
        GestureDetector(
          onTap: () {
            showModalBottomSheet(
              context: context,
              isScrollControlled: true,
              isDismissible: false,
              enableDrag: false,
              builder: (_) => AddMedFreqPage(viewModel.addMedProvider),
            ).whenComplete(() => setState(() {}));
          },
          child: Container(
            padding: const EdgeInsets.all(MyMeasurements.elementSpread),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(MyMeasurements.borderRadius),
              color: MyThemeProvider.of(context).colors.container,
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                // FREQUENCY
                Text(
                    MyTools.capitalizeFirstLetter(viewModel.strings.frequency)),

                // FREQ SELECTION
                Text(
                  MyTools.capitalizeEachWord(
                      AddMedProvider.of(context).myMed.frequency.value),
                  style: TextStyle(
                    color: Theme.of(context).colorScheme.primary,
                  ),
                ),
              ],
            ),
          ),
        ),

        _buildTimeInputArea(),

        const SizedBox(height: MyMeasurements.elementSpread),

        // NEXT button
        ElevatedButton(
          onPressed: viewModel.btnEnabled ? viewModel.onNext : null,
          child: Text(MyTools.capitalizeFirstLetter(viewModel.strings.next)),
        ),
      ],
    );
  }

  /// Builds the area where the user can add times for when they are to take
  /// their medicine.
  Widget _buildTimeInputArea() {
    if (viewModel.addMedProvider.myMed.frequency != MedFrequency.daily &&
        viewModel.addMedProvider.myMed.frequency !=
            MedFrequency.everyOtherDay) {
      return Container();
    }

    return Column(
      children: [
        const SizedBox(height: MyMeasurements.elementSpread),

        // TIME
        Row(
          children: [
            MyText(
              MyTools.capitalizeEachWord(viewModel.strings.time),
              myTextStyle: MyTextStyle.header,
            ),
          ],
        ),

        const SizedBox(height: MyMeasurements.textPadding),

        // TODO
      ],
    );
  }
}
