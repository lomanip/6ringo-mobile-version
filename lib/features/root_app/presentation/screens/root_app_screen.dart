import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../core/common/widgets/bottom_navigation_bar/custom_bottom_nav_bar.dart';
import '../../../../core/common/widgets/bottom_navigation_bar/models/custom_tab_item.dart';
import '../../../../core/config/theme/color.dart';
import '../../../home/presentation/screens/home_screen.dart';
import '../bloc/root_app_bloc.dart';

class RootAppScreen extends StatefulWidget {
  const RootAppScreen({super.key});

  @override
  State<RootAppScreen> createState() => _RootAppScreenState();
}

class _RootAppScreenState extends State<RootAppScreen> {
  int _pageIndex = 0;
  final _tabItems = const <TabItem>[
    TabItem(id: 0, iconData: Icons.house_rounded, label: "Home"),
    TabItem(id: 1, iconData: Icons.gamepad_rounded, label: "Games"),
    TabItem(id: 2, iconData: null, label: null),
    TabItem(id: 3, iconData: Icons.shopping_bag_outlined, label: "Cart"),
    TabItem(id: 4, iconData: Icons.account_circle_outlined, label: "Account"),
  ];
  final List<Widget> _children = const [
    HomeScreen(),
    HomeScreen(),
    SizedBox(), /// Empty
    HomeScreen(),
    HomeScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      floatingActionButton: FloatingActionButton(
        elevation: 5,
        onPressed: () {
          openDialogForQRCode();
        },
        backgroundColor: primaryColor,
        foregroundColor: Colors.white,
        shape: const CircleBorder(),
        child: const Icon(
          Icons.event_note_rounded,
          size: 25,
        ),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
      floatingActionButtonAnimator: FloatingActionButtonAnimator.scaling,
      bottomNavigationBar: CustomBottomNavBar(
          pageIndex: _pageIndex,
          tabItems: _tabItems,
          onPageChanged: (newIndex) {
            setState(() {
              _pageIndex = newIndex;
            });
          }),
      body: BlocListener<RootAppBloc, RootAppState>(
        listener: (context, state) {
          if (state is RootAppPageChanged) {
            setState(() {
              _pageIndex = state.pageIndex;
            });
          }
        },
        child: IndexedStack(
          index: _pageIndex,
          children: _children,
        ),
      ),
    );
  }

  void openDialogForQRCode() {
    // TODO
  }
}
