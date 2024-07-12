import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';
import 'models/custom_drop_down_item.dart';

class CustomDropDownMenu extends StatelessWidget {
  final bool enabled;
  final CustomDropDownItem? initialSelection;
  final List<DropdownMenuEntry<CustomDropDownItem>> dropdownMenuEntries;
  final IconData? leadingIcon;
  final String label;
  final TextEditingController? controller;

  const CustomDropDownMenu(
      {super.key,
      this.enabled = true,
      this.initialSelection,
      required this.dropdownMenuEntries,
      this.leadingIcon,
      required this.label,
      this.controller});

  @override
  Widget build(BuildContext context) {
    Icon? tempLeadingIcon;
    if (leadingIcon != null) {
      tempLeadingIcon = Icon(
        leadingIcon,
        size: Dimensions.iconSm,
        color: Colors.black,
      );
    }

    return DropdownMenu(
        enabled: enabled,
        initialSelection: initialSelection,
        inputDecorationTheme: InputDecorationTheme(
          border: OutlineInputBorder(
            borderSide: BorderSide.none,
            borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
          ),
          enabledBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.black.withOpacity(0.05),
              width: 1,
            ),
            borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
          ),
          focusedBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.black.withOpacity(0.05),
              width: 1,
            ),
            borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
          ),
          focusedErrorBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: Colors.black.withOpacity(0.05),
              width: 1,
            ),
            borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
          ),
          filled: true,
          fillColor: grayColor,
        ),
        menuStyle: MenuStyle(
          backgroundColor: WidgetStateProperty.all(Colors.white),
          elevation: WidgetStateProperty.all(4),
          shape: WidgetStateProperty.all(RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd))),
        ),
        dropdownMenuEntries: dropdownMenuEntries,
        leadingIcon: tempLeadingIcon,
        label: Text(
          label,
          style: bodyTextStyle.copyWith(color: Colors.black45),
          maxLines: 1,
          overflow: TextOverflow.ellipsis,
        ),
        expandedInsets: EdgeInsets.zero,
        controller: controller);
  }
}
