import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:med_tracker/constants/db_collections.dart';
import 'package:med_tracker/constants/db_fields.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/domain/models/my_med_log.dart';
import 'package:med_tracker/domain/models/my_user.dart';

class MyMedService {
  /// The "MedTracker" collection in Firebase.
  static final CollectionReference medTrackerCol =
      FirebaseFirestore.instance.collection(DBCollections.medTracker);

  /// Returns the user's document from the "MedTracker" collection in Firebase.
  static DocumentReference getMedTrackerDocPath(String myUserId) =>
      medTrackerCol.doc(myUserId);

  /// Returns the user's "Medication" collection in Firebase.
  static CollectionReference getMedicationsColPath(String myUserId) =>
      getMedTrackerDocPath(myUserId).collection(DBCollections.meds);

  /// Returns all of the user's medications from Firebase.
  ///
  /// Returns `null` if the user does not have any medications in the database.
  static Future<List<MyMed>?> fetchAll(MyUser myUser) async {
    /// The collection that contains all of the user's medications.
    final CollectionReference medicationsCol = getMedicationsColPath(myUser.id);

    /// A bundle of all the user's medication docs.
    final QuerySnapshot<Object?> medicationDocs = await medicationsCol.get();

    /// If they have no medications, return null.
    if (medicationDocs.docs.isEmpty) return null;

    /// The list of medications that will be returned.
    List<MyMed> myMeds = [];

    // Go through each document and get all of the medications.
    for (QueryDocumentSnapshot<Object?> medDoc in medicationDocs.docs) {
      // Don't process corrupt files.
      if (medDoc.data() is! Map<String, dynamic>) continue;

      /// The document info.
      final Map<String, dynamic> dataMap =
          medDoc.data() as Map<String, dynamic>;

      /// A [MyMed] object is created from the document.
      final MyMed myMed = MyMed.fromJson(medDoc.id, dataMap);

      // Add this medication to the list.
      myMeds.add(myMed);
    }

    return myMeds;
  }

  /// Fetches the user's medical history log from Firebase.
  ///
  /// Returns `null` if the user's log is empty or does not exist.
  static Future<MyMedLog?> fetchMedLog(MyUser myUser) async {
    /// The path to the user's medical history log.
    final DocumentReference docPath = getMedTrackerDocPath(myUser.id);

    /// The user's med log doc.
    final DocumentSnapshot<Object?> doc = await docPath.get();

    // If the doc is empty, return null.
    if (doc.data() == null) return null;

    // If the doc is corrupt, return null.
    if (doc.data() is! Map<String, dynamic>) return null;

    /// The doc data.
    final Map<String, dynamic> dataMap = doc.data() as Map<String, dynamic>;

    // If the doc has no medical log, return null.
    if (!dataMap.containsKey(DBFields.log)) return null;

    // If the medical log is corrupt, return null.
    if (dataMap[DBFields.log] is! Map<String, List<Timestamp>>) return null;

    /// The medical log.
    final Map<String, List<dynamic>> log =
        dataMap[DBFields.log] as Map<String, List<Timestamp>>;

    // If the medical log is empty, return null.
    if (log.isEmpty) return null;

    for (String key in log.keys) {
      for (int i = 0; i < (log[key]?.length ?? 0); i++) {
        log[key]![i] = (log[key]![i] as Timestamp).toDate();
      }
    }

    // Return the medical log.
    return MyMedLog(log as Map<String, List<DateTime>>);
  }
}
