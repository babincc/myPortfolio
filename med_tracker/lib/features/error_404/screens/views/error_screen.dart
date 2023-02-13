import 'package:flutter/material.dart';
import 'package:med_tracker/widgets/my_scaffold.dart';

class ErrorScreen extends StatelessWidget {
  const ErrorScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MyScaffold(
      builder: (context) => const Text("Error!"),
    );
  }
}
