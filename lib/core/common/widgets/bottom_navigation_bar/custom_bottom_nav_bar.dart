import 'package:flutter/material.dart';

import '../../../config/dimens/dimensions.dart';
import '../../../config/theme/color.dart';
import '../../../config/theme/type.dart';
import 'models/custom_tab_item.dart';

class CustomBottomNavBar extends StatelessWidget {
  final int pageIndex;
  final List<TabItem> tabItems;
  final Function(int)? onPageChanged;

  const CustomBottomNavBar(
      {super.key,
      required this.pageIndex,
      required this.tabItems,
      this.onPageChanged});

  @override
  Widget build(BuildContext context) {


    return BottomAppBar(
      color: Colors.white,
      surfaceTintColor: Colors.transparent,
      height: 64,
      padding: EdgeInsets.zero,
      shape: const CircularNotchedRectangle(),
      notchMargin: Dimensions.spacingSm,
      child: Card(
        elevation: 20,
        color: Colors.white,
        shape: const RoundedRectangleBorder(
            side: BorderSide.none,
            borderRadius: BorderRadius.zero
        ),
        margin: EdgeInsets.zero,
        child: Padding(
          padding: const EdgeInsets.symmetric(
            horizontal: Dimensions.spacingMd,
          ),
          child: Row(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: tabItems.map((item) =>
            item.id == 2 ? const SizedBox(width: 50) :
            Expanded(
              child: InkWell(
                borderRadius: BorderRadius.circular(Dimensions.borderRadiusMd),
                onTap: () {
                  final tempId = item.id;
                  if(tempId != null) {
                    onPageChanged!(tempId);
                  }
                },
                child: Padding(
                  padding: const EdgeInsets.symmetric(
                    horizontal: Dimensions.spacingSm,
                  ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Icon(
                        item.iconData,
                        size: 24,
                        semanticLabel: item.label,
                        color: item.id == pageIndex
                            ? primaryColor.withOpacity(1)
                            : Colors.black.withOpacity(0.3),
                      ),
                      Text(
                        item.label ?? '',
                        style: hintTextStyle.copyWith(
                            color: item.id == pageIndex
                                ? primaryColor.withOpacity(1)
                                : Colors.black.withOpacity(0.3)),
                        maxLines: 1,
                        overflow: TextOverflow.ellipsis,
                      )
                    ],
                  ),
                ),
              ),
            ),
            ).toList(),
          ),
        ),
      ),
    );
  }
}
