import 'package:flutter/material.dart';

import '../../../../../core/common/widgets/text_fields/custom_password_text_field.dart';
import '../../../../../core/common/widgets/text_fields/custom_simple_text_field.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/util/input_validator.dart';

class SignInForm extends StatelessWidget {
  final GlobalKey<FormState>? formKey;
  final bool isLoading;
  final TextEditingController? emailController;
  final TextEditingController? passwordController;
  final Function()? onSubmitted;

  const SignInForm({
    super.key,
    this.formKey,
    this.isLoading = false,
    this.emailController,
    this.passwordController,
    this.onSubmitted,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        /// Email
        CustomSimpleTextField(
          enabled: true,
          keyboardType: TextInputType.emailAddress,
          label: 'Email',
          prefixIcon: Icons.email_outlined,
          validator: (inputValue) {
            return InputValidator.validateEmail(inputValue)
                ? null
                : 'Please enter valid email!';
          },
          controller: emailController,
        ),

        /// Password
        const SizedBox(height: Dimensions.spacingMd),
        CustomPasswordTextField(
          enabled: true,
          label: 'Password',
          inputAction: TextInputAction.done,
          onSubmitted: onSubmitted,
          validator: (inputValue) {
            return InputValidator.validatePassword(inputValue)
                ? null
                : 'Please enter valid password!';
          },
          controller: passwordController,
        ),
      ],
    );
  }
}
