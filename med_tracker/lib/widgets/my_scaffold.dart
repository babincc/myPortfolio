import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:med_tracker/constants/theme/my_measurements.dart';
import 'package:med_tracker/features/add_med/screens/views/add_med_screen.dart';
import 'package:med_tracker/navigation/my_routes.dart';
import 'package:med_tracker/providers/my_theme_provider.dart';

class MyScaffold extends StatelessWidget {
  /// Creates a custom scaffold.
  ///
  /// This scaffold is a group of foundational widgets, put together to
  /// eliminate boilerplate and make the code cleaner. Since almost every screen
  /// and page is built with these at their core, it has been placed in one
  /// convenient widget.
  ///
  /// [Scaffold] > [SafeArea] > [Padding]
  const MyScaffold({
    Key? key,
    required this.builder,
    this.appBar,
    this.drawer,
    this.isCentered = true,
    this.bottomNavSelection = BottomNavSelection.hidden,
  })  : hasBottomNav = bottomNavSelection != BottomNavSelection.hidden,
        super(key: key);

  /// The app bar of this scaffold.
  final PreferredSizeWidget? appBar;

  /// The pop out drawer of this scaffold.
  final Widget? drawer;

  /// The body of this scaffold.
  final Widget Function(BuildContext) builder;

  /// Whether or not the contents of this scaffold should be centered on the
  /// screen.
  final bool isCentered;

  /// The tab on the bottom navigation bar that should be selected.
  final BottomNavSelection bottomNavSelection;

  /// Whether this page has a bottom navigation bar or not.
  final bool hasBottomNav;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: appBar,
      drawer: drawer,
      floatingActionButton:
          hasBottomNav ? _buildFloatingActionBtn(context) : null,
      floatingActionButtonLocation:
          hasBottomNav ? FloatingActionButtonLocation.centerDocked : null,
      bottomNavigationBar: hasBottomNav ? _buildBottomNav(context) : null,
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(MyMeasurements.distanceFromEdge),
          child: _buildLayout(context),
        ),
      ),
    );
  }

  /// This method lays out the given [child] based on [isCentered].
  Widget _buildLayout(BuildContext context) {
    if (isCentered) {
      return Center(
        child: builder(context),
      );
    }

    return builder(context);
  }

  /// The center button on the bottom nav bar.
  FloatingActionButton _buildFloatingActionBtn(BuildContext context) {
    return FloatingActionButton(
      onPressed: () {
        showModalBottomSheet(
          context: context,
          isScrollControlled: true,
          isDismissible: false,
          enableDrag: false,
          builder: ((context) => const AddMedScreen()),
        );
      },
      mini: true,
      backgroundColor: Theme.of(context).colorScheme.primary,
      foregroundColor: Theme.of(context).colorScheme.onPrimary,
      elevation: MyMeasurements.elementElevation,
      child: const Icon(Icons.add),
    );
  }

  /// The bottom nav bar.
  BottomNavigationBar _buildBottomNav(BuildContext context) {
    return BottomNavigationBar(
      onTap: (value) {
        // Do nothing if the current screen's own tab is selected.
        if (value == bottomNavSelection.value) return;

        /// The screen the user wants to access.
        String route;

        // Figure out which screen the user wants.
        switch (value) {
          case 1:
            // TODO
            route = "";
            break;
          case 2:
            // TODO
            route = "";
            break;
          case 3:
            // TODO
            route = "";
            break;
          case 0:
          default:
            route = MyRoutes.dashboardScreen;
        }

        // Take the user to their desired screen.
        GoRouter.of(context).replaceNamed(route);
      },
      selectedFontSize: 12.0,
      unselectedFontSize: 12.0,
      backgroundColor: MyThemeProvider.of(context).colors.secondaryContainer,
      type: BottomNavigationBarType.fixed,
      currentIndex: bottomNavSelection.value,
      items: const [
        BottomNavigationBarItem(
          label: "Home",
          icon: Icon(
            Icons.home,
          ),
        ),
        BottomNavigationBarItem(
          label: "Medications",
          icon: Icon(
            Icons.medication,
          ),
        ),
        BottomNavigationBarItem(
          label: "Refills",
          icon: Icon(
            Icons.refresh,
          ),
        ),
        BottomNavigationBarItem(
          label: "Journal",
          icon: Icon(
            Icons.menu_book,
          ),
        ),
      ],
    );
  }
}

/// The tabs on the bottom navigation bar.
enum BottomNavSelection {
  /// The home screen.
  home(0),

  /// The medications screen.
  medications(1),

  /// The refills screen.
  refills(2),

  /// The journal screen.
  journal(3),

  /// The bottom navigation bar should be hidden.
  hidden(-1);

  const BottomNavSelection(this.value);

  /// This [BottomNavSelection]'s index on the nav bar.
  final int value;

  /// Get a [LangCode] from a given string `value`.
  static BottomNavSelection fromInt(int value) {
    return values.firstWhere(
      (selection) => selection.value == value,
      orElse: () => BottomNavSelection.hidden,
    );
  }
}
