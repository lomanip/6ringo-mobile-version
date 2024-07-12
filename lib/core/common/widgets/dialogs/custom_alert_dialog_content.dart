import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';
import '../buttons/custom_button.dart';

class CustomAlertDialogContent extends StatelessWidget {
  final String? title;
  final String? description;
  final String? noButtonText;
  final String? okButtonText;
  final Function()? onNoClicked;
  final Function()? onOkClicked;

  const CustomAlertDialogContent(
      {super.key,
      this.title,
      this.description,
      this.noButtonText = 'Cancel',
      this.okButtonText = 'Yes',
      this.onNoClicked,
      this.onOkClicked});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: Dimensions.spacingLg),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          const SizedBox(height: Dimensions.spacingMd),
          Text(
            title ?? '',
            style: subtitleTextStyle,
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: Dimensions.spacingMd),
          Text(
            description ?? '',
            style: bodyTextStyle,
            maxLines: 4,
            overflow: TextOverflow.ellipsis,
            textAlign: TextAlign.center,
          ),
          const SizedBox(height: Dimensions.spacingXl),
          Row(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              CustomButton(
                text: noButtonText ?? '',
                backgroundColor: Colors.transparent,
                foregroundColor: primaryColor,
                onPressed: () {
                  onNoClicked!();
                },
              ),
              CustomButton(
                text: okButtonText ?? '',
                backgroundColor: Colors.transparent,
                foregroundColor: primaryColor,
                onPressed: () {
                  onOkClicked!();
                },
              ),
            ],
          )
        ],
      ),
    );
  }
}
