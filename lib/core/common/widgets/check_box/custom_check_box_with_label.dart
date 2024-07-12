import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';

class CustomCheckBoxWithLabel extends StatelessWidget {
  final bool checked;
  final String? label;
  final String? link;
  final void Function(bool?)? onChanged;
  final void Function()? onLinkClicked;

  const CustomCheckBoxWithLabel(
      {super.key,
      required this.checked,
      this.label,
      this.link,
      this.onChanged,
      this.onLinkClicked});

  @override
  Widget build(BuildContext context) {
    return CheckboxListTile(
      value: checked,
      controlAffinity: ListTileControlAffinity.leading,
      contentPadding: EdgeInsets.zero,
      title: Transform.translate(
        offset: const Offset(-20, 0),
        child: RichText(
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          text: TextSpan(
            text: label,
            style: hintTextStyle,
            children: <TextSpan>[
              if (link?.isNotEmpty == true)
                TextSpan(
                  text: link,
                  style: hintTextStyle.copyWith(
                    color: primaryColor,
                    decoration: TextDecoration.underline,
                  ),
                  recognizer: TapGestureRecognizer()
                    ..onTap = () {
                      onLinkClicked!();
                    },
                ),
            ],
          ),
        ),
      ),
      checkColor: Colors.black,
      fillColor: WidgetStateProperty.all(Colors.transparent),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(4),
      ),
      side: WidgetStateBorderSide.resolveWith(
        (states) => const BorderSide(width: 2.0, color: Colors.black),
      ),
      onChanged: (bool? value) {
        onChanged!(value);
      },
    );
  }
}
