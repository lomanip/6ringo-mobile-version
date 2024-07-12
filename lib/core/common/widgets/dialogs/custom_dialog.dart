import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';

class CustomDialogWidget extends StatelessWidget {
  final Widget content;

  const CustomDialogWidget({super.key, required this.content});

  @override
  Widget build(BuildContext context) {
    return Dialog(
        backgroundColor: Colors.transparent,
        child: Container(
            padding: const EdgeInsets.symmetric(
              horizontal: Dimensions.spacingMd,
              vertical: Dimensions.spacingMd,
            ),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
            ),
            child: SingleChildScrollView(
              child: content,
            )));
  }
}
