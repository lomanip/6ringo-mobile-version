import 'package:flutter/material.dart';

import '../../../config/theme/type.dart';
import 'custom_text_or_progress_bar.dart';

class CustomButton extends StatelessWidget {
  final String text;
  final Color backgroundColor;
  final Color foregroundColor;
  final bool isLoading;
  final Function()? onPressed;

  const CustomButton(
      {super.key,
      required this.text,
      required this.backgroundColor,
      required this.foregroundColor,
      this.isLoading = false,
      this.onPressed});

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      style: ButtonStyle(
          backgroundColor: WidgetStateProperty.all(backgroundColor),
          foregroundColor: WidgetStateProperty.all(foregroundColor),
          elevation: WidgetStateProperty.all(0),
          textStyle: WidgetStateProperty.all(
              bodyTextStyle.copyWith(color: foregroundColor)),
          fixedSize: WidgetStateProperty.all(const Size.fromHeight(45)),
          shape: WidgetStateProperty.all(
            RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(25),
            ),
          )),
      onPressed: () {
        onPressed!();
      },
      child: CustomTextOrProgressBar(
          isLoading: isLoading, text: text, foregroundColor: foregroundColor),
    );
  }
}
