import 'package:flutter/material.dart';
import 'package:med_tracker/constants/assets.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/providers/my_string_provider.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_text.dart';
import 'package:med_tracker/widgets/my_text_field.dart';

import '../../providers/add_med_provider.dart';
import '../../widgets/add_med_img.dart';
import '../view_models/add_med_name_page_view_model.dart';

class AddMedNamePage extends StatefulWidget {
  const AddMedNamePage({super.key, required this.onNext});

  final VoidCallback onNext;

  @override
  State<AddMedNamePage> createState() => _AddMedNamePageState();
}

class _AddMedNamePageState extends State<AddMedNamePage> {
  /// All of the logic that controls this page's UI.
  late final AddMedNamePageViewModel viewModel;

  /// Whether or not the "next" button is enabled.
  bool enabled = false;

  @override
  void initState() {
    viewModel = AddMedNamePageViewModel(
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
        const AddMedImg(Assets.addMedName),

        // TITLE
        MyText(
          MyTools.capitalizeEachWord(viewModel.strings.medName),
          myTextStyle: MyTextStyle.title,
        ),

        const SizedBox(height: MyMeasurements.elementSpread),

        // INSTRUCTIONS
        SizedBox(
          width: MyMeasurements.textBlockWidth,
          child: Text(
            viewModel.strings.medNameMsg,
            textAlign: TextAlign.center,
          ),
        ),

        const SizedBox(height: MyMeasurements.elementSpread),

        // NAME Input field
        MyTextField(
          controller: viewModel.nameController,
          hint: MyTools.capitalizeEachWord(viewModel.strings.medName),
          isLastField: true,
          onChanged: (_) => setState(() {}),
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
