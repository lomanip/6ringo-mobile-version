import 'dart:io';

class NetworkHttpErrorUtil {
  static String messageFromHttpStatusCode(int? statusCode) {
    String message = 'Something happens ! Please try again later.';

    switch (statusCode) {
      /// 500
      case HttpStatus.networkConnectTimeoutError:
        message = 'Server response time out !';
      case HttpStatus.gatewayTimeout:
        message = 'Server response time out !';

      /// 400
      case HttpStatus.requestTimeout:
        message = 'Please check your internet connection !';
      case HttpStatus.connectionClosedWithoutResponse:
        message = 'Please check your internet connection !';
      case HttpStatus.clientClosedRequest:
        message = 'Request cancelled !';
      case HttpStatus.unauthorized:
        message = 'You are not authorized !';
    }

    return message;
  }
}
