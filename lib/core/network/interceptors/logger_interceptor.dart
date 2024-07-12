import 'package:dio/dio.dart';
import 'package:logger/logger.dart';

import '../../util/network_dio_error_util.dart';

final Logger logger = Logger(
    printer: PrettyPrinter(
      methodCount: 0,
      errorMethodCount: 0,
      lineLength: 75,
      colors: true,
      printEmojis: false,
      printTime: true,
      noBoxingByDefault: false,
      levelColors: {
        Level.error: const AnsiColor.fg(31),
        Level.info: const AnsiColor.fg(37),
        Level.debug: const AnsiColor.fg(34),
      },
    ),
    level: Level.debug);

class LoggerInterceptor extends Interceptor {
  @override
  void onError(DioException err, ErrorInterceptorHandler handler) {
    final options = err.requestOptions;
    int tempStatusCode = err.response?.statusCode ??
        NetworkDioErrorUtil.statusCodeFromStatusType(err.type);
    String tempStatusMessage = err.response?.statusMessage ?? err.message ?? '';
    logger.e(
        error: 'HTTP ERROR RESPONSE',
        'URL          ===> ${options.baseUrl}${options.path} \n'
        'Method       ===> ${options.method} \n'
        'Status Code  ===> $tempStatusCode \n'
        'Message      ===> $tempStatusMessage \n'
        'Response     ===> ${err.response?.data ?? err.error}');
    return super.onError(err, handler);
  }

  @override
  void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
    logger.d(
        error: 'HTTP REQUEST',
        'URL          ===> ${options.baseUrl}${options.path} \n'
        'Method       ===> ${options.method} \n'
        'Headers      ===> ${options.headers} \n'
        'Query Params ===> ${options.queryParameters} \n'
        'Query Data   ===> ${options.data}');
    return super.onRequest(options, handler);
  }

  @override
  void onResponse(Response response, ResponseInterceptorHandler handler) {
    final options = response.requestOptions;
    logger.i(
        error: 'HTTP SUCCESS RESPONSE',
        'URL          ===> ${options.baseUrl}${options.path} \n'
        'Method       ===> ${options.method} \n'
        'Status Code  ===> ${response.statusCode} \n'
        'Message      ===> ${response.statusMessage} \n'
        'Response     ===> ${response.data}');
    return super.onResponse(response, handler);
  }
}
