import 'package:flutter/material.dart';

import '../config/dimens/dimensions.dart';
import '../config/theme/color.dart';
import '../config/theme/type.dart';

class SnackBarUtil {

  static void showCopiedToClipboard(BuildContext context) {
    showSimpleSnackBarWithHideAction(context: context, message: 'Copied to clipboard !');
  }
  static void showSimpleSnackBar(
      {required BuildContext context, required String message}) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      backgroundColor: snackBarColor,
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      margin: const EdgeInsets.all(Dimensions.spacingMd),
      shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd)),
      content: Text(
        message,
        style: bodyTextStyle.copyWith(color: Colors.white),
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
      ),
    ));
  }

  static void showSimpleSnackBarWithHideAction(
      {required BuildContext context, required String message}) {
    ScaffoldMessenger.of(context).showSnackBar(SnackBar(
      action: SnackBarAction(
        label: 'Hide',
        onPressed: () {
          ScaffoldMessenger.of(context).hideCurrentSnackBar();
        },
      ),
      backgroundColor: snackBarColor,
      elevation: 0,
      behavior: SnackBarBehavior.floating,
      margin: const EdgeInsets.all(Dimensions.spacingMd),
      shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd)),
      content: Text(
        message,
        style: bodyTextStyle.copyWith(color: Colors.white),
        maxLines: 1,
        overflow: TextOverflow.ellipsis,
      ),
    ));
  }
}
