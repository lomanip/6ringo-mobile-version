import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/type.dart';
import 'custom_input_decoration.dart';

class CustomPasswordTextField extends StatefulWidget {
  final bool enabled;
  final IconData? prefixIcon = Icons.key_outlined;
  final String label;
  final bool autoFocus;
  final TextInputAction? inputAction;
  final TextEditingController? controller;
  final Function()? onSubmitted;
  final String? Function(String?)? validator;

  const CustomPasswordTextField(
      {super.key,
      required this.enabled,
      required this.label,
      this.autoFocus = false,
      this.inputAction = TextInputAction.next,
      this.controller,
      this.validator,
      this.onSubmitted,
      });

  @override
  State<CustomPasswordTextField> createState() =>
      _CustomPasswordTextFieldState();
}

class _CustomPasswordTextFieldState extends State<CustomPasswordTextField> {
  bool obscureText = true;

  @override
  Widget build(BuildContext context) {
    Icon? tempPrefixIcon;
    if (widget.prefixIcon != null) {
      tempPrefixIcon = Icon(
        widget.prefixIcon,
        size: Dimensions.iconSm,
        color: Colors.black,
      );
    }

    return TextFormField(
      enabled: widget.enabled,
      validator: widget.validator,
      autofocus: widget.autoFocus,
      textInputAction: widget.inputAction,
      keyboardType: TextInputType.text,
      obscureText: obscureText,
      maxLines: 1,
      style: bodyTextStyle,
      controller: widget.controller,
      onFieldSubmitted: (value) {
        widget.onSubmitted!();
      },
      decoration: customInputDecoration(
        label: widget.label,
        prefixIcon: tempPrefixIcon,
        suffixIcon: IconButton(
          icon: Icon(
            obscureText == true
                ? Icons.hide_source_rounded
                : Icons.remove_red_eye_outlined,
            size: Dimensions.iconSm,
            color: Colors.black,
          ),
          onPressed: () {
            setState(() {
              obscureText = !obscureText;
            });
          },
        ),
      ),
    );
  }
}
