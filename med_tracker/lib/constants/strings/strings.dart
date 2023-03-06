import 'package:med_tracker/constants/strings/lang_code.dart';

/// A collection of all of the strings within the app.
///
/// This abstract class allows each language to implement it and there will
/// never be a language that is missing strings.
abstract class Strings {
  /// The [LangCode] associated with this specific language.
  LangCode get langCode;

  String get addTime;

  String get asNeeded;

  String get back;

  String get cancel;

  String get confirmPassword;

  String get createAccount;

  String get day;

  String get displayName;

  String get done;

  String get edit;

  String get email;

  String get emailAlreadyExists;

  String get emailDoesNotExist;

  String get error;

  String get existingAccount;

  String get failedAccountCreation;

  String get failedPasswordReset;

  String get forgotPassword;

  String get frequency;

  String get help;

  String get interval;

  String get invalidEmail;

  String get invalidPassword;

  String get loading;

  String get log;

  String get logIn;

  String get logOut;

  String get medName;

  String get medNameMsg;

  String get medSchedule;

  String get medStrength;

  String get medTracker;

  String get medType;

  String get more;

  String get moreOptions;

  String get next;

  String get noMedsToday;

  String get noneMarkedAsNeeded;

  String get notes;

  String get ok;

  String get optionalDetails;

  String get overdue;

  String get password;

  String get passwordResetLinkSent;

  String get passwordsDontMatch;

  String get passwordTooWeak;

  String get profile;

  String get regularly;

  String get required;

  String get reset;

  String get resetPasswordInstructions;

  String get search;

  String get settings;

  String get skipped;

  String get somethingWentWrong;

  String get taken;

  String get time;

  String get today;

  String get tryAgainLater;

  String get upcoming;
}
