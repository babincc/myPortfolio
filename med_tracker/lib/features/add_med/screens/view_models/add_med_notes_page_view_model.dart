import '../../providers/add_med_provider.dart';

class AddMedNotesPageViewModel {
  AddMedNotesPageViewModel({required this.addMedProvider});

  /// Provides the [MyMed] object that is being added.
  final AddMedProvider addMedProvider;
}
