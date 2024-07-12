import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';

InputDecoration customInputDecoration({
  String? label,
  Widget? prefixIcon,
  Widget? suffixIcon,
  Color fillColor = grayColor,
}) {
  return InputDecoration(
    filled: true,
    fillColor: fillColor,
    enabledBorder: OutlineInputBorder(
      borderSide: BorderSide(color: Colors.black.withOpacity(0.05), width: 1),
      borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
    ),
    disabledBorder: OutlineInputBorder(
      borderSide: BorderSide(color: Colors.black.withOpacity(0.05), width: 1),
      borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
    ),
    focusedBorder: OutlineInputBorder(
      borderSide: const BorderSide(color: primaryColor, width: 1),
      borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
    ),
    focusedErrorBorder: OutlineInputBorder(
      borderSide: const BorderSide(color: primaryColor, width: 1),
      borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
    ),
    errorBorder: OutlineInputBorder(
      borderSide: const BorderSide(color: Colors.red, width: 1),
      borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
    ),
    prefixIcon: prefixIcon,
    prefixIconConstraints:
        const BoxConstraints(minWidth: 48, maxWidth: double.infinity),
    suffixIcon: suffixIcon,
    labelText: label,
    labelStyle: bodyTextStyle.copyWith(color: Colors.black45),
  );
}
