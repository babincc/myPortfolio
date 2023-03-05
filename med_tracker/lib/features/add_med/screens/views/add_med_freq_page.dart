import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/providers/my_string_provider.dart';
import 'package:med_tracker/providers/my_theme_provider.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_modal_bottom_sheet_scaffold.dart';
import 'package:med_tracker/widgets/my_text.dart';

import '../../providers/add_med_provider.dart';
import '../view_models/add_med_freq_page_view_model.dart';

class AddMedFreqPage extends StatefulWidget {
  const AddMedFreqPage(this.addMedProvider, {super.key});

  final AddMedProvider addMedProvider;

  @override
  State<AddMedFreqPage> createState() => _AddMedFreqPageState();
}

class _AddMedFreqPageState extends State<AddMedFreqPage> {
  /// All of the logic that controls this page's UI.
  late final AddMedFreqPageViewModel viewModel;

  @override
  void initState() {
    final MedFrequency initFreq = widget.addMedProvider.myMed.frequency;
    int? index;
    if (MedFrequency.regulars.contains(initFreq)) {
      index = MedFrequency.regulars.indexOf(initFreq);
    }

    viewModel = AddMedFreqPageViewModel(
      addMedProvider: widget.addMedProvider,
      strings: MyStringProvider.of(context).strings,
      intervalStartIndex: index,
    );

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MyModalBottomSheetScaffold(
      heightFactor: 0.91,
      title: MyTools.capitalizeFirstLetter(viewModel.strings.frequency),
      isDragToDismiss: false,
      onRightActionBtn: () {
        viewModel.onDone();
        _close(context);
      },
      rightActionBtn:
          Text(MyTools.capitalizeFirstLetter(viewModel.strings.done)),
      onLeftActionBtn: () => _close(context),
      leftActionBtn:
          Text(MyTools.capitalizeFirstLetter(viewModel.strings.cancel)),
      child: Column(
        children: [
          // REGULARITY selector
          _buildFreqSelector(),

          const SizedBox(height: MyMeasurements.elementSpread),

          // INTERVAL selector
          if (viewModel.selectedFreq == viewModel.strings.regularly)
            _buildIntervalSelector(context),
        ],
      ),
    );
  }

  /// Closes this modal bottom sheet.
  void _close(BuildContext context) =>
      mounted ? Navigator.of(context).pop() : null;

  /// Builds the list of options the user can pick from.
  Widget _buildFreqSelector() {
    final List<String> options = [
      viewModel.strings.regularly,
      viewModel.strings.asNeeded
    ];

    return Container(
      padding: const EdgeInsets.only(left: MyMeasurements.elementSpread),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(MyMeasurements.borderRadius),
        color: MyThemeProvider.of(context).colors.container,
      ),
      child: Column(
        children: options
            .map((option) => _buildOption(context, options, option))
            .toList(),
      ),
    );
  }

  /// Builds each, individual selectable option.
  Widget _buildOption(
      BuildContext context, List<String> options, String option) {
    return Column(
      children: [
        // OPTION
        GestureDetector(
          onTap: () => setState(() => viewModel.selectedFreq = option),
          child: Container(
            color: Colors.transparent,
            child: Padding(
              padding: const EdgeInsets.symmetric(
                  vertical: MyMeasurements.elementSpread),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  // OPTION name
                  Expanded(
                    child: Text(
                      MyTools.capitalizeEachWord(
                        MyTools.capitalizeEachWord(option),
                      ),
                    ),
                  ),

                  // CHECK MARK indicator
                  if (identical(option, viewModel.selectedFreq))
                    Padding(
                      padding: const EdgeInsets.only(
                          right: MyMeasurements.elementSpread),
                      child: Icon(
                        Icons.check,
                        color: Theme.of(context).colorScheme.primary,
                        size: Theme.of(context).textTheme.bodyMedium?.fontSize,
                      ),
                    )
                ],
              ),
            ),
          ),
        ),

        // DIVIDING LINE
        if (!identical(option, options.last))
          const Divider(
            height: 0.0,
          ),
      ],
    );
  }

  /// Builds an iOS style item selector wheel for the time intervals the user
  /// can select from.
  Widget _buildIntervalSelector(BuildContext context) {
    final double fontSize =
        Theme.of(context).textTheme.bodyMedium?.fontSize ?? 14.0;
    final double lineHeight =
        Theme.of(context).textTheme.bodyMedium?.height ?? 1.43;
    const double padding = 2.0 * MyMeasurements.elementSpread;

    /// The height of each item in the wheel.
    final double itemExtent =
        fontSize + (((lineHeight / 10.0) * fontSize) * 2.0) + padding;

    viewModel.intervalController.jumpToItem(viewModel.selectedIntervalIndex);

    return Column(
      children: [
        // INTERVAL
        Row(
          children: [
            MyText(MyTools.capitalizeEachWord(viewModel.strings.interval)),
          ],
        ),

        const SizedBox(height: MyMeasurements.textPadding),

        // INTERVAL selector
        Container(
          height: MyMeasurements.textBlockWidth,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(MyMeasurements.borderRadius),
            color: MyThemeProvider.of(context).colors.container,
          ),
          child: CupertinoPicker(
            scrollController: viewModel.intervalController,
            itemExtent: itemExtent,
            onSelectedItemChanged: (value) =>
                viewModel.selectedIntervalIndex = value,
            children: MedFrequency.regulars
                .map((interval) => Padding(
                      padding: const EdgeInsets.symmetric(
                          vertical: MyMeasurements.elementSpread),
                      child: MyText(
                        MyTools.capitalizeEachWord(interval.value),
                        myTextStyle: MyTextStyle.body,
                      ),
                    ))
                .toList(),
          ),
        )
      ],
    );
  }
}
