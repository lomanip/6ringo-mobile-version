import 'package:flutter/cupertino.dart';

import '../../config/dimens/dimensions.dart';
import '../../config/theme/type.dart';

class AuthHeadline extends StatelessWidget {
  final String title;
  final String subtitle;

  const AuthHeadline({super.key, required this.title, required this.subtitle});

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.max,
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        Text(
          title,
          style: headlineTextStyle,
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
          textAlign: TextAlign.center,
        ),
        const SizedBox(height: Dimensions.spacingXs),
        Text(
          subtitle,
          style: bodyTextStyle,
          maxLines: 2,
          overflow: TextOverflow.ellipsis,
          textAlign: TextAlign.center,
        ),
      ],
    );
  }
}
