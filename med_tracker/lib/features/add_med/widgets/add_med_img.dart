import 'package:flutter/material.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';

class AddMedImg extends StatelessWidget {
  const AddMedImg(this.asset, {super.key});

  final String asset;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.only(
        top: MyMeasurements.distanceFromEdge,
        bottom: MyMeasurements.elementSpread,
      ),
      child: SizedBox(
        width: MyMeasurements.textBlockWidth / 2.0,
        height: MyMeasurements.textBlockWidth / 2.0,
        child: Image.asset(asset),
      ),
    );
  }
}
