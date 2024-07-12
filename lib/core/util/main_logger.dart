import 'package:flutter/foundation.dart';

class MainLogger {
  static printMessage(String? message) {
    if (kDebugMode) {
      print('Snip information: \n-----> Message: $message');
    }
  }

  static printLogException({required Error error, String? message = ''}) {
    printLogExceptionStackTraceMessage(
      errorType: error.toString(),
      message: message,
      stackTrace: error.stackTrace,
    );
  }

  static printLogExceptionStackTraceMessage({
    required String errorType,
    required String? message,
    required StackTrace? stackTrace,
  }) {
    if (kDebugMode) {
      print(
          'Error: $errorType \n-----> Message: $message \n-----> StackTrace: $stackTrace');
    }
  }
}
