import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/type.dart';

class CustomButtonWithIcon extends StatelessWidget {
  final String text;
  final IconData? iconData;
  final Color strokeColor;
  final Color backgroundColor;
  final Color foregroundColor;
  final bool isLoading;
  final Function()? onPressed;

  const CustomButtonWithIcon(
      {super.key,
      required this.text,
      this.iconData,
      this.strokeColor = Colors.transparent,
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
        ),
        side: WidgetStateProperty.all(BorderSide(
          color: strokeColor,
          width: 1,
        )),
      ),
      onPressed: () {
        onPressed!();
      },
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          if (isLoading)
            SizedBox(
              height: 24.0,
              width: 24.0,
              child: Center(
                  child: CircularProgressIndicator(
                backgroundColor: Colors.transparent,
                color: foregroundColor,
                strokeWidth: 2.0,
              )),
            ),
          if (!isLoading)
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Icon(
                  iconData,
                  size: 20,
                  color: foregroundColor,
                ),
                const SizedBox(width: Dimensions.spacingXs),
                Text(
                  text,
                  style: bodyTextStyle.copyWith(color: foregroundColor),
                  overflow: TextOverflow.ellipsis,
                  maxLines: 1,
                )
              ],
            )
        ],
      ),
    );
  }
}
