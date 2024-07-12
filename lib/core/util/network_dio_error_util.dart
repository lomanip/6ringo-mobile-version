import 'dart:io';

import 'package:dio/dio.dart';

import '../network/response_state.dart';

class NetworkDioErrorUtil {
  static ResponseState<T> responseFailedFromHttpResponse<T>(
      Response? httpResponse) {
    return ResponseFailed<T>(
      exception: DioException(
        requestOptions: httpResponse?.requestOptions ?? RequestOptions(),
        type: DioExceptionType.unknown,
        message: httpResponse?.statusMessage,
        error: null,
        stackTrace: null,
        response: Response(
          requestOptions: httpResponse?.requestOptions ?? RequestOptions(),
          statusCode: httpResponse?.statusCode,
          statusMessage: httpResponse?.statusMessage,
          data: httpResponse?.data,
          isRedirect: httpResponse?.isRedirect ?? false,
          redirects: httpResponse?.redirects ?? [],
          extra: httpResponse?.extra,
          headers: httpResponse?.headers,
        ),
      ),
    );
  }

  static ResponseState<T> responseFailedFromDioException<T>(DioException e) {
    int tempStatusCode =
        e.response?.statusCode ?? statusCodeFromStatusType(e.type);
    String tempStatusMessage = e.response?.statusMessage ?? e.message ?? '';
    return ResponseFailed<T>(
      exception: DioException(
        requestOptions: e.requestOptions,
        type: e.type,
        message: e.message,
        error: e.error,
        stackTrace: e.stackTrace,
        response: Response(
          requestOptions: e.requestOptions,
          statusCode: tempStatusCode,
          statusMessage: tempStatusMessage,
          data: e.response?.data,
          isRedirect: e.response?.isRedirect ?? false,
          redirects: e.response?.redirects ?? [],
          extra: e.response?.extra,
          headers: e.response?.headers,
        ),
      ),
    );
  }

  static int statusCodeFromStatusType(DioExceptionType type) {
    int statusCode = HttpStatus.notFound;
    if (type == DioExceptionType.connectionTimeout) {
      statusCode = HttpStatus.networkConnectTimeoutError;
    } else if (type == DioExceptionType.sendTimeout) {
      statusCode = HttpStatus.requestTimeout;
    } else if (type == DioExceptionType.receiveTimeout) {
      statusCode = HttpStatus.gatewayTimeout;
    } else if (type == DioExceptionType.connectionError) {
      statusCode = HttpStatus.connectionClosedWithoutResponse;
    } else if (type == DioExceptionType.cancel) {
      statusCode = HttpStatus.clientClosedRequest;
    } else {
      statusCode = HttpStatus.httpVersionNotSupported;
    }
    return statusCode;
  }
}
