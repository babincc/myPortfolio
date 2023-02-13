import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:med_tracker/constants/db_collections.dart';
import 'package:med_tracker/constants/db_fields.dart';
import 'package:med_tracker/domain/models/my_user.dart';
import 'package:med_tracker/domain/services/my_med_service.dart';

class MyUserService {
  /// The "users" collection in Firebase.
  static final CollectionReference usersCol =
      FirebaseFirestore.instance.collection(DBCollections.users);

  /// Creates a new user, and adds them to Firebase.
  static Future<MyUser> createUserDoc(String userId) async {
    /// The user object that is being created and sent to Firebase.
    final MyUser myUser = MyUser(id: userId, firstName: "", lastName: "");

    /// The bundle of documents that will be sent to Firebase to be created.
    WriteBatch batch = FirebaseFirestore.instance.batch();

    // The documents being sent to Firebase.
    DocumentReference userDoc = usersCol.doc(userId);
    DocumentReference medTrackerDoc = MyMedService.medTrackerCol.doc();

    // Prep the docs to go to Firebase.
    batch.set(userDoc, myUser.toJson());
    batch.set(medTrackerDoc, {DBFields.userId: userId, DBFields.log: {}});

    // Write the new docs to Firebase.
    await batch.commit();

    return myUser;
  }

  /// Fetches the user from Firebase.
  ///
  /// Returns `null` if the user does not exist.
  static Future<MyUser?> fetchUser(String userId) async {
    /// Fetch the user document from Firebase.
    final DocumentSnapshot<Object?> userDoc = await usersCol.doc(userId).get();

    /// If the document does not exist, return null.
    if (userDoc.data() == null) return null;

    /// If the data is corrupt, return null.
    if (userDoc.data is! Map<String, dynamic>) return null;

    Map<String, dynamic> dataMap = userDoc.data() as Map<String, dynamic>;

    return MyUser.fromJson(userDoc.id, dataMap);
  }
}
