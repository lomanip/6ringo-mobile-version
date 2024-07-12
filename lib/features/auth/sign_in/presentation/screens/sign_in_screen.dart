import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:six_ringo/features/root_app/presentation/screens/root_app_screen.dart';

import '../../../../../core/common/widgets/auth_headline.dart';
import '../../../../../core/common/widgets/buttons/custom_button_fill_width.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/config/theme/color.dart';
import '../../../../../core/util/image_util.dart';
import '../../../../../core/util/navigator_util.dart';
import '../../../../../core/util/network_http_error_util.dart';
import '../../../../../core/util/snack_bar_util.dart';
import '../../../sign_up/presentation/screens/sign_up_screen.dart';
import '../bloc/sign_in_bloc.dart';
import '../widgets/sign_in_bottom_section.dart';
import '../widgets/sign_in_form.dart';

class SignInScreen extends StatefulWidget {
  final String? toastErrorMessage;

  const SignInScreen({super.key, this.toastErrorMessage});

  @override
  State<SignInScreen> createState() => _SignInScreenState();
}

class _SignInScreenState extends State<SignInScreen> {
  final _formKey = GlobalKey<FormState>();
  final _emailController = TextEditingController();
  final _passwordController = TextEditingController();

  @override
  void initState() {
    super.initState();

    WidgetsBinding.instance.addPostFrameCallback((_) {
      showErrorMessageSnackBar();
    });
  }

  @override
  Widget build(BuildContext context) {
    SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
      statusBarColor: Colors.transparent,
      systemNavigationBarColor: Colors.white,
      systemNavigationBarDividerColor: Colors.white,
    ));

    return Scaffold(
      body: SafeArea(
      child: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.only(
            top: 0,
            left: Dimensions.spacingMd,
            right: Dimensions.spacingMd,
            bottom: Dimensions.spacingMd,
          ),
          child: Column(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              const SizedBox(height: Dimensions.spacingXxl),
              const SizedBox(height: Dimensions.spacingXxl),
              Image.asset(
                ImageUtil.appLogo,
                height: 100,
              ),
              const SizedBox(height: Dimensions.spacingMd),
              BlocConsumer<SignInBloc, SignInState>(
                listener: (context, state) async {
                  if (state is SignInFailed) {
                    SnackBarUtil.showSimpleSnackBarWithHideAction(
                        context: context,
                        message: errorMessageFromStatusCode(
                            state.response.exception?.response?.statusCode));
                  }
                  if (state is SignInSuccess) {
                    NavigatorUtil.replace(context, const RootAppScreen());
                  }
                },
                builder: (context, state) {
                  return Column(
                    children: [
                      const SizedBox(height: Dimensions.spacingXl),
                      const AuthHeadline(
                          title: 'Sign in',
                          subtitle: 'Please sign in to continue'),
                      const SizedBox(height: Dimensions.spacingLg),
                      Form(
                        key: _formKey,
                        child: Column(
                          children: [
                            SignInForm(
                              formKey: _formKey,
                              isLoading: state is SignInLoading,
                              emailController: _emailController,
                              passwordController: _passwordController,
                              onSubmitted: () {
                                if (_formKey.currentState?.validate() ==
                                    true &&
                                    state is! SignInLoading) {
                                  signInUser();
                                }
                              },
                            ),
                            const SizedBox(height: Dimensions.spacingMd),
                            CustomButtonFillWidth(
                                text: 'Sign in',
                                backgroundColor: primaryColor,
                                foregroundColor: Colors.white,
                                isLoading: state is SignInLoading,
                                onPressed: () {
                                  if (_formKey.currentState?.validate() ==
                                          true &&
                                      state is! SignInLoading) {
                                    signInUser();
                                  }
                                }),
                          ],
                        ),
                      ),
                      const SizedBox(height: Dimensions.spacingMd),
                      SignInBottomSection(
                        isLoading: state is SignInLoading,
                        onSignUpClicked: () {
                          NavigatorUtil.push(context, const SignUpScreen());
                        },
                        onForgotPasswordClicked: () {
                          // TODO
                        },
                      ),
                      const SizedBox(height: Dimensions.spacingMd),
                    ],
                  );
                },
              ),
            ],
          ),
        ),
      ),
    ));
  }

  void signInUser() {
    context.read<SignInBloc>().add(SignInWithEmailEvent(
        email: _emailController.text.trim(),
        password: _passwordController.text.trim(),
    ));
  }

  String errorMessageFromStatusCode(int? statusCode) {
    String message = NetworkHttpErrorUtil.messageFromHttpStatusCode(statusCode);
    switch (statusCode) {
      case HttpStatus.forbidden || HttpStatus.notFound:
        message = 'Please enter correct email and password !';
        break;
    }
    return message;
  }

  void showErrorMessageSnackBar() {
    if (widget.toastErrorMessage != null &&
        widget.toastErrorMessage?.isNotEmpty == true) {
      SnackBarUtil.showSimpleSnackBar(
          context: context, message: widget.toastErrorMessage ?? '');
    }
  }
}
