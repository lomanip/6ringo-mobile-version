import 'package:flutter/material.dart';

import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/config/theme/color.dart';
import '../../../../../core/config/theme/type.dart';

class SignUpBottomSection extends StatelessWidget {
  final bool isLoading;
  final Function()? onSignInClicked;

  const SignUpBottomSection(
      {super.key, this.isLoading = false, this.onSignInClicked});

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      verticalDirection: VerticalDirection.down,
      children: [
        const Text(
          'Already have account?',
          style: bodyTextStyle,
        ),
        const SizedBox(width: Dimensions.spacingSm),
        TextButton(
            onPressed: () {
              if (!isLoading) onSignInClicked!();
            },
            child: Text(
              'Sign in',
              style: bodyTextStyle.copyWith(color: primaryColor),
            ))
      ],
    );
  }
}
