import 'package:flutter/material.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_text.dart';

import '../providers/dashboard_day_provider.dart';

class DashboardDayBtn extends StatelessWidget {
  const DashboardDayBtn(this.day, {super.key});

  /// The day of the week being represented by this button.
  final String day;

  @override
  Widget build(BuildContext context) {
    bool active = DashboardDayProvider.of(context).day == day;

    return GestureDetector(
      onTap: () => DashboardDayProvider.of(context).day = day,
      child: Container(
        padding: const EdgeInsets.all(MyMeasurements.textPadding),
        decoration: BoxDecoration(
          borderRadius: const BorderRadius.all(
            Radius.circular(MyMeasurements.borderRadius),
          ),
          color: active ? Theme.of(context).colorScheme.primary : null,
        ),
        child: MyText(
          MyTools.capitalizeFirstLetter(day),
          myTextStyle: MyTextStyle.caption,
          color: active ? Theme.of(context).colorScheme.onPrimary : null,
        ),
      ),
    );
  }
}
