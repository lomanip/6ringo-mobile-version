import 'package:flutter/material.dart';

import '../../../../../core/common/widgets/buttons/custom_button_fill_width.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/config/theme/color.dart';
import '../../../../../core/config/theme/type.dart';

class SignInBottomSection extends StatelessWidget {
  final bool isLoading;
  final Function()? onSignUpClicked;
  final Function()? onForgotPasswordClicked;

  const SignInBottomSection({
    super.key,
    this.isLoading = false,
    this.onSignUpClicked,
    this.onForgotPasswordClicked,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        TextButton(
            onPressed: () {
              if (!isLoading) onForgotPasswordClicked!();
            },
            child: Text(
              'Forgot your password?',
              style: bodyTextStyle.copyWith(color: primaryColor),
            )),
        Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.center,
          verticalDirection: VerticalDirection.down,
          children: [
            const Text(
              'Don\'t have an account?',
              style: bodyTextStyle,
            ),
            const SizedBox(width: Dimensions.spacingSm),
            TextButton(
                onPressed: () {
                  if (!isLoading) onSignUpClicked!();
                },
                child: Text(
                  'Sign up',
                  style: bodyTextStyle.copyWith(color: primaryColor),
                ))
          ],
        ),
      ],
    );
  }
}
