import 'package:flutter/material.dart';
import 'package:med_tracker/widgets/my_scaffold.dart';

class HelpScreen extends StatelessWidget {
  const HelpScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MyScaffold(
      appBar: AppBar(
        title: const Text("Help"),
      ),
      builder: (context) => const Text("Howdy!"),
    );
  }
}
