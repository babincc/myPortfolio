import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:med_tracker/constants/db_fields.dart';
import 'package:med_tracker/domain/models/my_med_log.dart';

/// This model represents a medication.
///
/// It includes information about the medication itself, as well as how the user
/// is meant to interact with the medication.
class MyMed {
  /// Creates a [MyMed] object.
  const MyMed({
    required this.id,
    required this.name,
    this.displayName,
    required this.type,
    required this.strength,
    required this.dosage,
    required this.frequency,
    required this.time,
    this.notes,
  });

  /// Creates a [MyMed] object from the data map obtained from Firebase.
  factory MyMed.fromJson(String id, Map<String, dynamic> dataMap) {
    List<String> times = dataMap[DBFields.time] as List<String>;

    return MyMed(
      id: id,
      name: dataMap[DBFields.name],
      displayName: dataMap[DBFields.displayName],
      type: MedType.fromString(dataMap[DBFields.type]),
      strength: dataMap[DBFields.strength] as double,
      dosage: dataMap[DBFields.dosage] as double,
      frequency: MedFrequency.fromString(dataMap[DBFields.frequency]),
      time: times.map((timeStr) {
        DateTime dateTime = DateFormat("H:mm").parse(timeStr);
        return TimeOfDay.fromDateTime(dateTime);
      }).toList(),
      notes: dataMap[DBFields.notes],
    );
  }

  factory MyMed.empty() {
    return const MyMed(
      id: "",
      name: "",
      type: MedType.undefined,
      strength: -1.0,
      dosage: -1.0,
      frequency: MedFrequency.undefined,
      time: [],
    );
  }

  /// This medication's document ID in Firebase.
  final String id;

  /// The name of this medication.
  final String name;

  /// The nickname the user has given this medication.
  final String? displayName;

  /// The form factor of this medication.
  final MedType type;

  /// How big a single unit of this medications is.
  ///
  /// Ex. 25mg tablets would set this value to `25`
  final double strength;

  /// How much of this medication the user is meant to take at a time.
  ///
  /// Ex. 1.5 tablets would set this value to `1.5`
  final double dosage;

  /// How often the user is meant to take this medication.
  final MedFrequency frequency;

  /// What time of day the user is meant to take this medication.
  final List<TimeOfDay> time;

  /// Any additional notes the user wants to add about this medication.
  final String? notes;

  /// Whether or not this medication has any significant values.
  bool get isEmpty =>
      id == "" &&
      name == "" &&
      displayName == null &&
      type == MedType.undefined &&
      strength == -1.0 &&
      dosage == -1.0 &&
      frequency == MedFrequency.undefined &&
      time == [] &&
      notes == null;

  /// Returns a data map that represents all of the information in this model.
  ///
  /// This is useful for sending information to Firebase.
  Map<String, dynamic> toJson() {
    return {
      DBFields.name: name,
      DBFields.displayName: displayName,
      DBFields.type: type.value,
      DBFields.strength: strength,
      DBFields.dosage: dosage,
      DBFields.frequency: frequency.value,
      DBFields.time: time.map((time) => "${time.hour}:${time.minute}"),
      DBFields.notes: notes,
    };
  }

  /// Returns a copy of this medication with its field values replaced by the
  /// ones provided to this method.
  ///
  /// Since [displayName] and [notes] are nullable, they are defaulted to empty
  /// strings in this method. If left as empty strings, their current value in
  /// this [MyMed] object will be used. This way, if they are `null`, the
  /// program will know that they are intentionally being set to `null`.
  MyMed copyWith({
    String? id,
    String? name,
    String? displayName = "",
    MedType? type,
    double? strength,
    double? dosage,
    MedFrequency? frequency,
    List<TimeOfDay>? time,
    String? notes = "",
  }) {
    return MyMed(
      id: id ?? this.id,
      name: name ?? this.name,
      displayName: displayName == null || displayName.isNotEmpty
          ? displayName
          : this.displayName,
      type: type ?? this.type,
      strength: strength ?? this.strength,
      dosage: dosage ?? this.dosage,
      frequency: frequency ?? this.frequency,
      time: time ?? this.time,
      notes: notes == null || notes.isNotEmpty ? notes : this.notes,
    );
  }

  DateTime? lastUse(MyMedLog myMedLog) {
    if (myMedLog.log[id] == null || myMedLog.log[id]!.isEmpty) return null;

    DateTime mostRecent = myMedLog.log[id]!.first;

    for (DateTime date in myMedLog.log[id]!) {
      if (date.isAfter(mostRecent)) {
        mostRecent = date;
      }
    }

    return mostRecent;
  }
}

/// The form in which this medication is taken.
///
/// Tablet form, capsule form, liquid form, etc.
enum MedType {
  capsule("capsule", mg),
  tablet("tablet", mg),
  liquid("oral liquid", ml),
  powder("oral powder", mg),
  injection("injection", mgPerMl),
  ointment("ointment", percent),
  nasalSpray("nasal spray", mcgPerActuation),
  eyeDrops("eye drops", percent),
  enema("enema", mg),
  undefined("undefined", "undefined");

  static const String mg = "mg";
  static const String ml = "ml";
  static const String mgPerMl = "mg/ml";
  static const String percent = "%";
  static const String mcgPerActuation = "mcg/actuation";

  const MedType(this.value, this.units);

  /// This [MedType]'s string value.
  final String value;

  /// The measurements units for the strength of this [MedType].
  final String units;

  /// Get a [MedType] from a given string `value`.
  static MedType fromString(String value) {
    return values.firstWhere(
      (medType) => medType.value == value,
      orElse: () => undefined,
    );
  }

  /// The more common medication types from all of these options.
  static List<MedType> get common => [
        capsule,
        tablet,
        liquid,
        ointment,
        eyeDrops,
      ];

  /// All of the types from these options except for [undefined].
  static List<MedType> get all => [
        capsule,
        tablet,
        liquid,
        powder,
        injection,
        ointment,
        nasalSpray,
        eyeDrops,
        enema,
      ];
}

/// The form in which this medication is taken.
///
/// Tablet form, capsule form, liquid form, etc.
enum MedFrequency {
  daily("daily"),
  everyOtherDay("every other day"),
  weekly("weekly"),
  monthly("monthly"),
  annually("annually"),
  asNeeded("as needed"),
  undefined("undefined");

  const MedFrequency(this.value);

  /// This [MedFrequency]'s string value.
  final String value;

  /// Get a [MedFrequency] from a given string `value`.
  static MedFrequency fromString(String value) {
    return values.firstWhere(
      (medFrequency) => medFrequency.value == value,
      orElse: () => undefined,
    );
  }

  /// A list of all the frequencies in this list that would be considered a
  /// regular basis.
  ///
  /// This list excludes [undefined] and [asNeeded].
  static List<MedFrequency> get regulars => [
        daily,
        everyOtherDay,
        weekly,
        monthly,
        annually,
      ];
}
