import 'package:flutter/material.dart';
import 'package:med_tracker/widgets/my_modal_bottom_sheet_scaffold.dart';
import 'package:provider/provider.dart';

import '../../providers/add_med_provider.dart';
import '../view_models/add_med_screen_view_model.dart';
import 'add_med_schedule_page.dart';
import 'add_med_name_page.dart';
import 'add_med_notes_page.dart';
import 'add_med_strength_page.dart';
import 'add_med_type_page.dart';

class AddMedScreen extends StatefulWidget {
  const AddMedScreen({super.key});

  @override
  State<AddMedScreen> createState() => _AddMedScreenState();
}

class _AddMedScreenState extends State<AddMedScreen> {
  /// All of the logic that controls this page's UI.
  final AddMedScreenViewModel viewModel = AddMedScreenViewModel();

  final AddMedProvider addMedProvider = AddMedProvider();

  /// The page that is currently being shown to the user.
  _Page currentPage = _Page.medName;

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider.value(
      value: addMedProvider,
      builder: (context, _) => MyModalBottomSheetScaffold(
        isDragToDismiss: false,
        onRightActionBtn: () => mounted ? Navigator.of(context).pop() : null,
        onLeftActionBtn: currentPage.prevPage == null ? null : _onBack,
        child: _buildChild(),
      ),
    );
  }

  /// What to do when the "back" button is pressed.
  void _onBack() {
    if (currentPage.prevPage == null) return;

    setState(() => currentPage = currentPage.prevPage!);
  }

  /// What to do when the "next" button is pressed.
  void _onNext() {
    if (currentPage.nextPage == null) return;

    setState(() => currentPage = currentPage.nextPage!);
  }

  /// Determines what pages needs to be built and returns it.
  Widget _buildChild() {
    switch (currentPage) {
      case _Page.medType:
        return AddMedTypePage(onNext: _onNext);
      case _Page.strength:
        return AddMedStrengthPage(onNext: _onNext);
      case _Page.schedule:
        return AddMedSchedulePage(onNext: _onNext);
      case _Page.notes:
        return const AddMedNotesPage();
      case _Page.medName:
      default:
        return AddMedNamePage(onNext: _onNext);
    }
  }
}

/// All of the pages that can be shown in this medication adding process.
enum _Page {
  /// [AddMedNamePage]
  medName,

  /// [AddMedTypePage]
  medType,

  /// [AddMedStrengthPage]
  strength,

  /// [AddMedSchedulePage]
  schedule,

  /// [AddMedNotesPage]
  notes;

  /// The page that was displayed before this one.
  ///
  /// Returns `null` if there was none before this one.
  _Page? get prevPage {
    switch (this) {
      case _Page.medType:
        return _Page.medName;
      case _Page.strength:
        return _Page.medType;
      case _Page.schedule:
        return _Page.strength;
      case _Page.notes:
        return _Page.schedule;
      case _Page.medName:
      default:
        return null;
    }
  }

  /// The page that is to be displayed after this one.
  ///
  /// Returns `null` if none come after this one.
  _Page? get nextPage {
    switch (this) {
      case _Page.medName:
        return _Page.medType;
      case _Page.medType:
        return _Page.strength;
      case _Page.strength:
        return _Page.schedule;
      case _Page.schedule:
        return _Page.notes;
      case _Page.notes:
      default:
        return null;
    }
  }
}
