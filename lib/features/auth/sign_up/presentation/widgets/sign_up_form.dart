import 'package:flutter/material.dart';

import '../../../../../core/common/widgets/text_fields/custom_password_text_field.dart';
import '../../../../../core/common/widgets/text_fields/custom_simple_text_field.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/util/input_validator.dart';

class SignUpForm extends StatelessWidget {
  final bool isLoading;
  final GlobalKey<FormState> formKey;
  final TextEditingController? firstNameController;
  final TextEditingController? lastNameController;
  final TextEditingController? emailController;
  final TextEditingController? phoneNumberController;
  final TextEditingController? password1Controller;
  final TextEditingController? password2Controller;
  final Function()? onSubmitted;

  const SignUpForm(
      {super.key,
      this.isLoading = false,
      required this.formKey,
      this.firstNameController,
      this.lastNameController,
      this.emailController,
      this.phoneNumberController,
      this.password1Controller,
      this.password2Controller,
        this.onSubmitted,
      });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        /// First name
        CustomSimpleTextField(
          enabled: !isLoading,
          keyboardType: TextInputType.name,
          label: 'First name',
          prefixIcon: Icons.person_outline_rounded,
          validator: (inputValue) {
            return InputValidator.validateUserName(inputValue)
                ? null
                : 'Please enter valid name !';
          },
          controller: firstNameController,
        ),

        const SizedBox(height: Dimensions.spacingMd),

        /// Last name
        CustomSimpleTextField(
          enabled: !isLoading,
          keyboardType: TextInputType.name,
          label: 'Last name',
          prefixIcon: Icons.person_outline_rounded,
          validator: (inputValue) {
            return InputValidator.validateUserName(inputValue)
                ? null
                : 'Please enter valid name !';
          },
          controller: lastNameController,
        ),

        const SizedBox(height: Dimensions.spacingMd),

        /// Email
        CustomSimpleTextField(
          enabled: !isLoading,
          keyboardType: TextInputType.emailAddress,
          label: 'Email',
          prefixIcon: Icons.email_outlined,
          validator: (inputValue) {
            return InputValidator.validateEmail(inputValue)
                ? null
                : 'Please enter valid email !';
          },
          controller: emailController,
        ),

        const SizedBox(height: Dimensions.spacingMd),

        /// Phone number
        CustomSimpleTextField(
          enabled: !isLoading,
          keyboardType: TextInputType.number,
          label: 'Phone number',
          prefixIcon: Icons.phone_android_rounded,
          validator: (inputValue) {
            return InputValidator.validatePhoneNumber(inputValue)
                ? null
                : 'Please enter valid phoneNumber !';
          },
          controller: phoneNumberController,
        ),

        const SizedBox(height: Dimensions.spacingMd),

        /// Password
        CustomPasswordTextField(
          enabled: !isLoading,
          label: 'Password',
          controller: password1Controller,
          validator: (inputValue) {
            return InputValidator.validatePassword(inputValue)
                ? null
                : 'Please enter valid password !';
          },
        ),

        const SizedBox(height: Dimensions.spacingMd),

        /// Confirm Password
        CustomPasswordTextField(
          enabled: !isLoading,
          label: 'Confirm Password',
          controller: password2Controller,
          inputAction: TextInputAction.done,
          onSubmitted: onSubmitted,
          validator: (inputValue) {
            return InputValidator.validatePasswordMatching(
                    password1Controller?.text.trim(), inputValue)
                ? null
                : 'Passwords doesn\'t match !';
          },
        ),
      ],
    );
  }
}
