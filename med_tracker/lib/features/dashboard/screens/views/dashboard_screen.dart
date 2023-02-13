import 'package:flutter/material.dart';
import 'package:med_tracker/constants/strings/strings.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/domain/models/my_med.dart';
import 'package:med_tracker/providers/my_med_provider.dart';
import 'package:med_tracker/providers/my_string_provider.dart';
import 'package:med_tracker/providers/my_theme_provider.dart';
import 'package:med_tracker/providers/my_user_provider.dart';
import 'package:med_tracker/utils/my_tools.dart';
import 'package:med_tracker/widgets/my_drawer_menu.dart';
import 'package:med_tracker/widgets/my_scaffold.dart';
import 'package:med_tracker/widgets/my_text.dart';
import 'package:provider/provider.dart';

import '../../domain/models/dashboard_day.dart';
import '../../providers/dashboard_day_provider.dart';
import '../../widgets/dashboard_day_btn.dart';
import '../view_models/dashboard_screen_view_model.dart';

class DashboardScreen extends StatelessWidget {
  const DashboardScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final Strings strings = MyStringProvider.of(context, listen: true).strings;

    /// All of the logic that controls this page's UI.
    final DashboardScreenViewModel viewModel =
        DashboardScreenViewModel(strings);

    return MyScaffold(
      appBar: AppBar(
        title: Text(MyTools.capitalizeEachWord(viewModel.strings.medTracker)),
      ),
      drawer: const MyDrawerMenu(),
      isCentered: false,
      bottomNavSelection: BottomNavSelection.home,
      builder: (context) => ChangeNotifierProvider.value(
        value: DashboardDayProvider(),
        builder: (context, _) {
          DashboardDayProvider.of(context, listen: true).day;

          return SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                // WEEK IN VIEW
                Padding(
                  padding: const EdgeInsets.only(
                    bottom: MyMeasurements.elementSpread,
                  ),
                  child: _buildThisWeek(context, viewModel),
                ),

                // TODAY'S MEDS
                Padding(
                  padding: const EdgeInsets.only(
                    bottom: MyMeasurements.elementSpread,
                  ),
                  child: _buildTodaysMeds(context, viewModel),
                ),

                // AS NEEDED
                _buildAsNeeded(context, viewModel),
              ],
            ),
          );
        },
      ),
    );
  }

  /// Builds the current week calendar that sits at the top of the page.
  Widget _buildThisWeek(
      BuildContext context, DashboardScreenViewModel viewModel) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        // TODAY header
        Padding(
          padding: const EdgeInsets.only(
            bottom: MyMeasurements.elementSpread,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.end,
            children: [
              // TODAY text
              MyText(
                MyTools.capitalizeFirstLetter(viewModel.strings.today),
                myTextStyle: MyTextStyle.title,
              ),

              // MORE button
              TextButton(
                onPressed: () {
                  // TODO go to larger calendar view
                },
                style: const ButtonStyle(
                  minimumSize: MaterialStatePropertyAll<Size>(Size.zero),
                  tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                  padding:
                      MaterialStatePropertyAll<EdgeInsets>(EdgeInsets.zero),
                ),
                child: Text(
                  MyTools.capitalizeFirstLetter(viewModel.strings.more),
                ),
              ),
            ],
          ),
        ),

        // WEEK buttons
        Container(
          padding: const EdgeInsets.all(MyMeasurements.textPadding),
          decoration: BoxDecoration(
            borderRadius: const BorderRadius.all(
              Radius.circular(MyMeasurements.borderRadius),
            ),
            color: MyThemeProvider.of(context).colors.container,
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: DashboardDay.daysFrom(DateTime.sunday)
                .map(
                  (day) => DashboardDayBtn(day),
                )
                .toList(),
          ),
        ),
      ],
    );
  }

  /// Builds the section that houses the medications that are taken today.
  Widget _buildTodaysMeds(
      BuildContext context, DashboardScreenViewModel viewModel) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(MyMeasurements.textPadding),
      decoration: BoxDecoration(
        borderRadius: const BorderRadius.all(
          Radius.circular(MyMeasurements.borderRadius),
        ),
        color: MyThemeProvider.of(context).colors.container,
      ),
      child: Center(
        child: FutureBuilder(
          future: viewModel.fetchMedsFromDay(
            myUser: MyUserProvider.of(context).myUser,
            myMedProvider: MyMedProvider.of(context),
            dashboardDayProvider: DashboardDayProvider.of(context),
          ),
          builder: (context, snapshot) {
            // If the medications are still loading, do this.
            if (snapshot.connectionState != ConnectionState.done) {
              return MyText(
                "${viewModel.strings.loading}...",
                myTextStyle: MyTextStyle.caption,
              );
            }

            // If medication are null, do this.
            if (snapshot.data == null) {
              return MyText(
                viewModel.strings.noMedsToday,
                myTextStyle: MyTextStyle.caption,
              );
            }

            // If the data is corrupt, do this.
            if (snapshot.data is! List<MyMed>) {
              return MyText(
                viewModel.strings.error,
                myTextStyle: MyTextStyle.caption,
              );
            }

            // Return the list of medications.
            return Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: (snapshot.data as List<MyMed>)
                  .map((myMed) => Text(myMed.name))
                  .toList(),
            );
          },
        ),
      ),
    );
  }

  Widget _buildAsNeeded(
      BuildContext context, DashboardScreenViewModel viewModel) {
    return Column(
      children: [
        // AS NEEDED text
        Padding(
          padding: const EdgeInsets.only(
            bottom: MyMeasurements.elementSpread,
          ),
          child: Row(
            children: [
              MyText(
                MyTools.capitalizeEachWord(viewModel.strings.asNeeded),
                myTextStyle: MyTextStyle.title,
              ),
            ],
          ),
        ),

        // AS NEEDED section
        Container(
          width: double.infinity,
          padding: const EdgeInsets.all(MyMeasurements.textPadding),
          decoration: BoxDecoration(
            borderRadius: const BorderRadius.all(
              Radius.circular(MyMeasurements.borderRadius),
            ),
            color: MyThemeProvider.of(context).colors.container,
          ),
          child: Center(
            child: FutureBuilder(
                future: viewModel.fetchAsNeededMeds(
                  myUser: MyUserProvider.of(context).myUser,
                  myMedProvider: MyMedProvider.of(context),
                ),
                builder: (context, snapshot) {
                  // If the medications are still loading, do this.
                  if (snapshot.connectionState != ConnectionState.done) {
                    return MyText(
                      "${viewModel.strings.loading}...",
                      myTextStyle: MyTextStyle.caption,
                    );
                  }

                  // If medication are null, do this.
                  if (snapshot.data == null) {
                    return MyText(
                      viewModel.strings.noneMarkedAsNeeded,
                      myTextStyle: MyTextStyle.caption,
                    );
                  }

                  // If the data is corrupt, do this.
                  if (snapshot.data is! List<MyMed>) {
                    return MyText(
                      viewModel.strings.error,
                      myTextStyle: MyTextStyle.caption,
                    );
                  }

                  // Return the list of medications.
                  return Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: (snapshot.data as List<MyMed>)
                        .map((myMed) => Text(myMed.name))
                        .toList(),
                  );
                }),
          ),
        ),
      ],
    );
  }
}
