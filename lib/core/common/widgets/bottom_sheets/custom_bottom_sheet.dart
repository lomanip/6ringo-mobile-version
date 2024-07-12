import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';

class CustomBottomSheet extends StatelessWidget {
  final Widget? child;

  const CustomBottomSheet({super.key, this.child});

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: const EdgeInsets.symmetric(
          horizontal: Dimensions.spacingMd,
          vertical: Dimensions.spacingMd,
        ),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
        ),
        child: SingleChildScrollView(
          child: child,
        ));
  }
}
