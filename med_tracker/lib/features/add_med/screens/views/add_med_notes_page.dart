import 'package:flutter/material.dart';

import '../../providers/add_med_provider.dart';
import '../view_models/add_med_notes_page_view_model.dart';

class AddMedNotesPage extends StatefulWidget {
  const AddMedNotesPage({super.key});

  @override
  State<AddMedNotesPage> createState() => _AddMedNotesPageState();
}

class _AddMedNotesPageState extends State<AddMedNotesPage> {
  /// All of the logic that controls this page's UI.
  late final AddMedNotesPageViewModel viewModel;

  @override
  void initState() {
    viewModel = AddMedNotesPageViewModel(
      addMedProvider: AddMedProvider.of(context),
    );

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }
}
