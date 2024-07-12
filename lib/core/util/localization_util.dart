import 'dart:io';

import 'package:flutter/widgets.dart';

import '../common/enums/language_enum.dart';

class LocalizationUtil {
  static String? getDefaultLangCode(BuildContext context) {
    String? langCode;
    try {
      langCode = Platform.localeName.split('_')[0].toLowerCase();
    } on RangeError catch (_) {}

    LanguageEnum? foundLang;
    try {
      foundLang = LanguageEnum.values.firstWhere(
          (item) => langCode?.isNotEmpty != true && item.name == langCode);
    } on RangeError catch (_) {}
    return foundLang != null ? langCode : 'en';
  }

  static String? getDefaultCountryCode(BuildContext context) {
    String? countryCode;
    try {
      countryCode = Platform.localeName.split('_')[1].toLowerCase();
    } on RangeError catch (_) {}
    return countryCode;
  }
}
