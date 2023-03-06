import 'package:med_tracker/constants/strings/lang_code.dart';

import 'strings.dart';

/// A collection of all of the strings within the app.
///
/// This is the English version.
class StringsEN implements Strings {
  const StringsEN();

  @override
  LangCode get langCode => LangCode.en;

  @override
  String get addTime => "add time";

  @override
  String get asNeeded => "as needed";

  @override
  String get back => "back";

  @override
  String get cancel => "cancel";

  @override
  String get confirmPassword => "confirm password";

  @override
  String get createAccount => "create account";

  @override
  String get day => "day";

  @override
  String get displayName => "display name";

  @override
  String get done => "done";

  @override
  String get edit => "edit";

  @override
  String get email => "email";

  @override
  String get emailAlreadyExists => "email already exists";

  @override
  String get emailDoesNotExist => "email does not exist";

  @override
  String get error => "error";

  @override
  String get existingAccount => "already have an account";

  @override
  String get failedAccountCreation => "failed to create account";

  @override
  String get failedPasswordReset => "failed to send password reset link";

  @override
  String get forgotPassword => "forgot password";

  @override
  String get frequency => "frequency";

  @override
  String get help => "help";

  @override
  String get interval => "select interval";

  @override
  String get invalidEmail => "invalid email";

  @override
  String get invalidPassword => "invalid password";

  @override
  String get loading => "loading";

  @override
  String get log => "log";

  @override
  String get logIn => "log in";

  @override
  String get logOut => "log out";

  @override
  String get medName => "medication name";

  @override
  String get medNameMsg =>
      "This should be the proper name; you will have a chance to give it a "
      "nickname later.";

  @override
  String get medSchedule => "medication schedule";

  @override
  String get medStrength => "medication strength";

  @override
  String get medTracker => "med tracker";

  @override
  String get medType => "medication type";

  @override
  String get more => "more";

  @override
  String get moreOptions => "more options";

  @override
  String get next => "next";

  @override
  String get noMedsToday => "no medications today";

  @override
  String get noneMarkedAsNeeded => "no medications marked \"as needed\"";

  @override
  String get notes => "notes";

  @override
  String get ok => "ok";

  @override
  String get optionalDetails => "optional details";

  @override
  String get overdue => "overdue";

  @override
  String get password => "password";

  @override
  String get passwordResetLinkSent => "password reset link sent";

  @override
  String get passwordsDontMatch => "passwords do not match";

  @override
  String get passwordTooWeak => "password too weak";

  @override
  String get profile => "profile";

  @override
  String get regularly => "at regular intervals";

  @override
  String get required => "required";

  @override
  String get reset => "reset";

  @override
  String get resetPasswordInstructions =>
      "enter your email and a password reset link will be sent to you";

  @override
  String get search => "search";

  @override
  String get settings => "settings";

  @override
  String get skipped => "skipped";

  @override
  String get somethingWentWrong => "something went wrong";

  @override
  String get taken => "taken";

  @override
  String get time => "time";

  @override
  String get today => "today";

  @override
  String get tryAgainLater => "please try again later";

  @override
  String get upcoming => "upcoming";
}
