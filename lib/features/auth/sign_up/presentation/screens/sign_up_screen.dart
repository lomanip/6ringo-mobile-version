import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:six_ringo/core/util/navigator_util.dart';
import 'package:six_ringo/features/auth/sign_up/domain/entities/sign_up_request_entity.dart';
import 'package:six_ringo/features/auth/sign_up/presentation/widgets/sign_up_dialog_content_policy.dart';
import 'package:six_ringo/features/auth/sign_up/presentation/widgets/sign_up_form.dart';

import '../../../../../core/common/widgets/app_bars/custom_centered_app_bar.dart';
import '../../../../../core/common/widgets/auth_headline.dart';
import '../../../../../core/common/widgets/buttons/custom_button_fill_width.dart';
import '../../../../../core/common/widgets/dialogs/custom_dialog.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/config/theme/color.dart';
import '../../../../../core/util/network_http_error_util.dart';
import '../../../../../core/util/snack_bar_util.dart';
import '../bloc/sign_up_bloc.dart';
import '../widgets/sign_up_bottom_section.dart';

class SignUpScreen extends StatefulWidget {
  const SignUpScreen({super.key});

  @override
  State<SignUpScreen> createState() => _SignUpScreenState();
}

class _SignUpScreenState extends State<SignUpScreen> {
  final _formKey = GlobalKey<FormState>();
  final _firstNameController = TextEditingController();
  final _lastNameController = TextEditingController();
  final _emailController = TextEditingController();
  final _phoneNumberController = TextEditingController();
  final _password1Controller = TextEditingController();
  final _password2Controller = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: customCenteredAppBar(
          title: '',
          withLeadingIcon: true,
          onLeadingIconClicked: () {
            NavigatorUtil.pop(context);
          }),
      body: SafeArea(
        child: SingleChildScrollView(
          child: Padding(
            padding: const EdgeInsets.only(
              top: 0,
              left: Dimensions.spacingMd,
              right: Dimensions.spacingMd,
              bottom: Dimensions.spacingMd,
            ),
            child: BlocConsumer<SignUpBloc, SignUpState>(
              listener: (context, state) {
                if (state is SignUpFailed) {
                  SnackBarUtil.showSimpleSnackBarWithHideAction(
                      context: context,
                      message: errorMessageFromStatusCode(
                          state.response.exception?.response?.statusCode));
                }
                if (state is SignUpSuccess) {
                  // TODO
                }
              },
              builder: (context, state) {
                return Form(
                    key: _formKey,
                    child: Column(
                      mainAxisSize: MainAxisSize.max,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const AuthHeadline(
                            title: 'Sign up', subtitle: 'Create a new account'),
                        const SizedBox(height: Dimensions.spacingLg),
                        SignUpForm(
                          isLoading: state is SignUpLoading,
                          formKey: _formKey,
                          firstNameController: _firstNameController,
                          lastNameController: _lastNameController,
                          emailController: _emailController,
                          phoneNumberController: _phoneNumberController,
                          password1Controller: _password1Controller,
                          password2Controller: _password2Controller,
                          onSubmitted: () {
                            if (_formKey.currentState?.validate() ==
                                true &&
                                state is! SignUpLoading) {
                              startSignUpProcess(state);
                            }
                          },
                        ),
                        const SizedBox(height: Dimensions.spacingMd),
                        CustomButtonFillWidth(
                            text: 'Sign up',
                            backgroundColor: primaryColor,
                            foregroundColor: Colors.white,
                            isLoading: state is SignUpLoading,
                            onPressed: () {
                              if (_formKey.currentState?.validate() == true) {
                                startSignUpProcess(state);
                              }
                            }),
                        const SizedBox(height: Dimensions.spacingMd),
                        SignUpBottomSection(
                            isLoading: state is SignUpLoading,
                            onSignInClicked: () {
                              NavigatorUtil.pop(context);
                            })
                      ],
                    ));
              },
            ),
          ),
        ),
      ),
    );
  }

  void signUp() {
    context.read<SignUpBloc>().add(SignUpWithEmailEvent(
            user: SignUpRequestEntity(
          firstName: _firstNameController.text.trim(),
          lastName: _lastNameController.text.trim(),
          email: _emailController.text.trim(),
          phoneNumber: _phoneNumberController.text.trim(),
          password: _password2Controller.text.trim(),
        )));
  }

  String errorMessageFromStatusCode(int? statusCode) {
    String message = NetworkHttpErrorUtil.messageFromHttpStatusCode(statusCode);
    switch (statusCode) {
      case HttpStatus.found:
        message =
            'This Email/Phone is already taken ! Please try another ones.';
        break;
      case HttpStatus.forbidden || HttpStatus.notFound:
        message = 'Please fill all fields correctly !';
        break;
    }

    return message;
  }

  void startSignUpProcess(SignUpState state) {
    showDialog(
      context: context,
      builder: (dialogContext) =>
          CustomDialogWidget(
            content: StatefulBuilder(
              builder:
                  (BuildContext statefulBuilderContext,
                  StateSetter setStateDialog) =>
                  SignUpDialogContentPolicy(
                      isTermsAccepted: false,
                      isPolicyAccepted: false,
                      onSubmit: (isTermsAccepted,
                          isPolicyAccepted) {
                        if (isTermsAccepted &&
                            isPolicyAccepted &&
                            state
                            is! SignUpLoading) {
                          NavigatorUtil.pop(
                              dialogContext);
                          signUp();
                        } else {
                          SnackBarUtil
                              .showSimpleSnackBarWithHideAction(
                              context: context,
                              message:
                              'You need to accept Terms & Policy before !');
                        }
                      },
                      onClose: () {
                        NavigatorUtil.pop(
                            dialogContext);
                      }),
            ),
          ),
    );
  }
}
