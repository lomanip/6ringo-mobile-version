import 'package:flutter/material.dart';

import '../../../config/theme/type.dart';

PreferredSizeWidget customCenteredAppBar({
  String? title,
  required bool withLeadingIcon,
  required Function()? onLeadingIconClicked,
}) {
  return AppBar(
    forceMaterialTransparency: true,
    backgroundColor: Colors.transparent,
    leadingWidth: withLeadingIcon ? 75 : 40,
    leading: withLeadingIcon
        ? IconButton(
            icon: const Icon(
              Icons.chevron_left_rounded,
              size: 24,
              color: Colors.black,
            ),
            color: Colors.black,
            onPressed: () {
              onLeadingIconClicked!();
            },
          )
        : null,
    title: Text(
      title ?? '',
      style: subtitleTextStyle,
      maxLines: 1,
      overflow: TextOverflow.ellipsis,
      textAlign: TextAlign.center,
    ),
    centerTitle: true,
  );
}
