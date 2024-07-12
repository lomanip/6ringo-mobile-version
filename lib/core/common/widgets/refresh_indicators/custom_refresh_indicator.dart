import 'package:flutter/material.dart';

import '../../../config/theme/color.dart';

class CustomRefreshIndicator extends StatelessWidget {
  final Future<void> Function()? onRefresh;
  final Widget child;

  const CustomRefreshIndicator(
      {super.key, this.onRefresh, required this.child});

  @override
  Widget build(BuildContext context) {
    return RefreshIndicator(
      displacement: 10,
      color: primaryColor,
      backgroundColor: Colors.white,
      onRefresh: () async {
        await onRefresh!();
        await Future.delayed(const Duration(seconds: 2));
      },
      child: child,
    );
  }
}
