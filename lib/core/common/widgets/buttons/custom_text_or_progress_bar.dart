import 'package:flutter/material.dart';

import '../../../config/theme/type.dart';

class CustomTextOrProgressBar extends StatelessWidget {
  final bool isLoading;
  final String text;
  final Color foregroundColor;

  const CustomTextOrProgressBar(
      {super.key,
      required this.isLoading,
      required this.text,
      required this.foregroundColor});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisSize: MainAxisSize.min,
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
          Text(
            text,
            style: bodyTextStyle.copyWith(color: foregroundColor),
            overflow: TextOverflow.ellipsis,
            maxLines: 1,
          )
      ],
    );
  }
}
