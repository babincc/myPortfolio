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
import '../view_models/add_med_type_page_view_model.dart';

class AddMedTypePage extends StatefulWidget {
  const AddMedTypePage({super.key, required this.onNext});

  final VoidCallback onNext;

  @override
  State<AddMedTypePage> createState() => _AddMedTypePageState();
}

class _AddMedTypePageState extends State<AddMedTypePage> {
  /// All of the logic that controls this page's UI.
  late final AddMedTypePageViewModel viewModel;

  bool showAllOptions = false;

  @override
  void initState() {
    viewModel = AddMedTypePageViewModel(
      addMedProvider: AddMedProvider.of(context),
      onNext: widget.onNext,
      strings: MyStringProvider.of(context).strings,
    );

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          // IMAGE
          const AddMedImg(Assets.addMedType),

          // TITLE
          MyText(
            MyTools.capitalizeEachWord(viewModel.strings.medType),
            myTextStyle: MyTextStyle.title,
          ),

          const SizedBox(height: MyMeasurements.elementSpread),

          // TYPE selection
          _buildTypeSelector(context),

          const SizedBox(height: MyMeasurements.elementSpread),

          // MORE OPTIONS button
          if (!showAllOptions)
            Padding(
              padding:
                  const EdgeInsets.only(bottom: MyMeasurements.elementSpread),
              child: TextButton(
                onPressed: () {
                  setState(() {
                    showAllOptions = true;
                  });
                },
                child: Text(
                    MyTools.capitalizeEachWord(viewModel.strings.moreOptions)),
              ),
            ),

          // NEXT button
          ElevatedButton(
            onPressed: viewModel.btnEnabled ? viewModel.onNext : null,
            child: Text(MyTools.capitalizeFirstLetter(viewModel.strings.next)),
          ),
        ],
      ),
    );
  }

  /// Builds the list of options the user can pick from.
  Widget _buildTypeSelector(BuildContext context) {
    List<MedType> options = showAllOptions ? MedType.all : MedType.common;

    // If the user has added an uncommon type, show it.
    if (viewModel.selected != MedType.undefined &&
        !options.contains(viewModel.selected)) {
      options.add(viewModel.selected);
    }

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
      BuildContext context, List<MedType> options, MedType option) {
    return Column(
      children: [
        // OPTION
        GestureDetector(
          onTap: () => setState(() => viewModel.selected = option),
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
                      child: Text(MyTools.capitalizeEachWord(option.value))),

                  // CHECK MARK indicator
                  if (identical(option, viewModel.selected))
                    Padding(
                      padding: const EdgeInsets.only(
                          right: MyMeasurements.elementSpread),
                      child: Icon(
                        Icons.check,
                        color: Theme.of(context).colorScheme.primary,
                        size: Theme.of(context).textTheme.bodyText2?.fontSize,
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
}
