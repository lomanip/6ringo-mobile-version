import 'package:flutter/material.dart';

abstract class NavigatorUtil {
  static pop(BuildContext context) {
    Navigator.pop(
      context,
    );
  }

  static replace(BuildContext context, Widget screen) {
    Navigator.pushReplacement(
      context,
      MaterialPageRoute(
        builder: (context) => screen,
      ),
    );
  }

  static push(BuildContext context, Widget screen) {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => screen,
      ),
    );
  }
}
