import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';
import 'custom_input_decoration.dart';

class CustomSimpleTextField extends StatelessWidget {
  final bool enabled;
  final TextInputType? keyboardType;
  final bool autoFocus;
  final String label;
  final Color fillColor;
  final IconData? prefixIcon;
  final IconData? suffixIcon;
  final TextInputAction? inputAction;
  final TextEditingController? controller;
  final Function()? onSubmitted;
  final String? Function(String?)? validator;

  const CustomSimpleTextField(
      {super.key,
      required this.enabled,
      this.keyboardType,
      this.autoFocus = false,
      required this.label,
      this.fillColor = grayColor,
      this.prefixIcon,
      this.suffixIcon,
      this.inputAction = TextInputAction.next,
      this.controller,
      this.validator,
      this.onSubmitted,
      });

  @override
  Widget build(BuildContext context) {
    Icon? tempPrefixIcon;
    if (prefixIcon != null) {
      tempPrefixIcon = Icon(
        prefixIcon,
        size: Dimensions.iconSm,
        color: Colors.black,
      );
    }
    Icon? tempSuffixIcon;
    if (suffixIcon != null) {
      tempSuffixIcon = Icon(
        suffixIcon,
        size: Dimensions.iconSm,
        color: Colors.black,
      );
    }

    return TextFormField(
      enabled: enabled,
      autofocus: autoFocus,
      keyboardType: keyboardType,
      maxLines: 1,
      style: bodyTextStyle,
      controller: controller,
      validator: validator,
      textInputAction: inputAction,
      onFieldSubmitted: (value) {
        onSubmitted!();
      },
      decoration: customInputDecoration(
        label: label,
        prefixIcon: tempPrefixIcon,
        suffixIcon: tempSuffixIcon,
        fillColor: fillColor
      ),
    );
  }
}
