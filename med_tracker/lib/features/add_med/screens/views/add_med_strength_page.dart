import 'package:flutter/material.dart';
import 'package:med_tracker/constants/assets.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/providers/my_string_provider.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_text.dart';
import 'package:med_tracker/widgets/my_text_field.dart';

import '../../providers/add_med_provider.dart';
import '../../widgets/add_med_img.dart';
import '../view_models/add_med_strength_page_view_model.dart';

class AddMedStrengthPage extends StatefulWidget {
  const AddMedStrengthPage({
    super.key,
    required this.onNext,
  });

  final VoidCallback onNext;

  @override
  State<AddMedStrengthPage> createState() => _AddMedStrengthPageState();
}

class _AddMedStrengthPageState extends State<AddMedStrengthPage> {
  /// All of the logic that controls this page's UI.
  late final AddMedStrengthPageViewModel viewModel;

  @override
  void initState() {
    viewModel = AddMedStrengthPageViewModel(
      addMedProvider: AddMedProvider.of(context),
      onNext: widget.onNext,
      strings: MyStringProvider.of(context).strings,
    );

    double strength = AddMedProvider.of(context).myMed.strength;

    if (strength > 0) {
      viewModel.strengthController.text = strength.toString();
    }

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        // IMAGE
        const AddMedImg(Assets.addMedStrength),

        // TITLE
        MyText(
          MyTools.capitalizeEachWord(viewModel.strings.medStrength),
          myTextStyle: MyTextStyle.title,
        ),

        const SizedBox(height: MyMeasurements.elementSpread),

        // NAME Input field
        Row(
          children: [
            // NAME input field
            Expanded(
              child: MyTextField(
                controller: viewModel.strengthController,
                hint: MyTools.capitalizeEachWord(viewModel.strings.medStrength),
                isLastField: true,
                isNumber: true,
                onChanged: (_) => setState(() {}),
              ),
            ),

            // UNIT of measure
            Padding(
              padding:
                  const EdgeInsets.only(left: MyMeasurements.elementSpread),
              child: Text(AddMedProvider.of(context).myMed.type.units),
            ),
          ],
        ),

        const SizedBox(height: MyMeasurements.elementSpread),

        // NEXT button
        ElevatedButton(
          onPressed: viewModel.btnEnabled ? viewModel.onNext : null,
          child: Text(MyTools.capitalizeFirstLetter(viewModel.strings.next)),
        ),
      ],
    );
  }
}
