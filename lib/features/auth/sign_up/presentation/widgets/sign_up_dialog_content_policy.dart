import 'package:flutter/material.dart';

import '../../../../../core/common/widgets/buttons/custom_button_fill_width.dart';
import '../../../../../core/common/widgets/check_box/custom_check_box_with_label.dart';
import '../../../../../core/config/dimens/dimensions.dart';
import '../../../../../core/config/theme/color.dart';
import '../../../../../core/config/theme/type.dart';

class SignUpDialogContentPolicy extends StatefulWidget {
  final bool isTermsAccepted;
  final bool isPolicyAccepted;
  final Function(bool, bool)? onSubmit;
  final Function()? onClose;

  const SignUpDialogContentPolicy(
      {super.key,
      this.isTermsAccepted = false,
      this.isPolicyAccepted = false,
      this.onSubmit,
      this.onClose});

  @override
  State<SignUpDialogContentPolicy> createState() =>
      _SignUpDialogContentPolicyState();
}

class _SignUpDialogContentPolicyState extends State<SignUpDialogContentPolicy> {
  bool _isTermsAccepted = false;
  bool _isPolicyAccepted = false;

  @override
  void initState() {
    super.initState();
    setState(() {
      _isTermsAccepted = widget.isTermsAccepted;
      _isPolicyAccepted = widget.isPolicyAccepted;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.min,
      children: [
        const Text(
          'Terms & conditions',
          style: titleTextStyle,
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          textAlign: TextAlign.center,
        ),
        const SizedBox(height: Dimensions.spacingXs),
        Text(
          'It\'s crucial to read them carefully before using an application to understand your rights and responsibilities.',
          style: bodyTextStyle.copyWith(color: Colors.black.withOpacity(0.5)),
          maxLines: 3,
          overflow: TextOverflow.ellipsis,
          textAlign: TextAlign.center,
        ),
        const SizedBox(height: Dimensions.spacingMd),
        Row(
          children: [
            Expanded(
                child: CustomCheckBoxWithLabel(
                    checked: _isTermsAccepted,
                    label: 'I accept  ',
                    link: 'Terms & Conditions',
                    onChanged: (isChecked) {
                      setState(() {
                        _isTermsAccepted = isChecked ?? false;
                      });
                    },
                    onLinkClicked: () {
                      //  TODO show terms
                    })),
          ],
        ),
        const SizedBox(height: Dimensions.spacingXs),
        Row(
          children: [
            Expanded(
                child: CustomCheckBoxWithLabel(
                    checked: _isPolicyAccepted,
                    label: 'I agree to respect  ',
                    link: 'Privacy Policy',
                    onChanged: (isChecked) {
                      setState(() {
                        _isPolicyAccepted = isChecked ?? false;
                      });
                    },
                    onLinkClicked: () {
                      //  TODO show policy
                    })),
          ],
        ),
        const SizedBox(height: Dimensions.spacingMd),
        CustomButtonFillWidth(
          isLoading: false,
          text: 'Accept',
          backgroundColor: primaryColor,
          foregroundColor: Colors.white,
          onPressed: () {
            widget.onSubmit!(_isTermsAccepted, _isPolicyAccepted);
          },
        ),
        const SizedBox(height: Dimensions.spacingXs),
        CustomButtonFillWidth(
          isLoading: false,
          text: 'Cancel',
          backgroundColor: Colors.transparent,
          foregroundColor: primaryColor,
          onPressed: () {
            widget.onClose!();
          },
        ),
      ],
    );
  }
}
