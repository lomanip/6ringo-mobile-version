import 'dart:io';

import 'package:dio/dio.dart';
import 'package:six_ringo/core/network/response_state.dart';

import '../constants/end_points.dart';
import '../util/main_logger.dart';
import '../util/network_dio_error_util.dart';
import 'interceptors/authorization_interceptor.dart';
import 'interceptors/logger_interceptor.dart';

class ApiClient {
  late final Dio _dio;
  final List<int> successHttpStatusCodes = [
    HttpStatus.ok, // 200
    HttpStatus.created, // 201
    HttpStatus.accepted, // 202
    HttpStatus.nonAuthoritativeInformation, // 203
    HttpStatus.noContent, // 204
    HttpStatus.resetContent, // 205
    HttpStatus.partialContent, // 206
    HttpStatus.multiStatus, // 207
    HttpStatus.alreadyReported, // 208
    HttpStatus.imUsed, // 226
  ];

  ApiClient()
      : _dio = Dio(
          BaseOptions(
            baseUrl: EndPoints.baseUrl,
            contentType: Headers.jsonContentType,
            sendTimeout: const Duration(seconds: 30000),
            connectTimeout: const Duration(seconds: 30000),
            receiveTimeout: const Duration(seconds: 30000),
            responseType: ResponseType.json,
          ),
        )..interceptors
            .addAll([AuthorizationInterceptor(), LoggerInterceptor()]);

  /// GET
  Future<ResponseState<T>> get<T>(
    String endpoint, {
    Map<String, dynamic>? params,
    T Function(Map<String, dynamic> map)? fromJsonToModel,
    Map<String, dynamic>? customHeaders,
  }) async {
    Response? httpResponse;
    try {
      httpResponse = await _dio.get(
        endpoint,
        options: addCustomHeaders(_dio.options, customHeaders),
        queryParameters: params,
      );
    } on DioException catch (e) {
      return NetworkDioErrorUtil.responseFailedFromDioException<T>(e);
    }
    return _castResponseToModel<T>(httpResponse,
        fromJsonToModel: fromJsonToModel);
  }

  /// POST
  Future<ResponseState<T>> post<T>(
    String endpoint, {
    Map<String, dynamic>? params,
    dynamic formData,
    T Function(Map<String, dynamic> map)? fromJsonToModel,
    Map<String, dynamic>? customHeaders,
  }) async {
    Response? httpResponse;
    try {
      httpResponse = await _dio.post(
        endpoint,
        options: addCustomHeaders(_dio.options, customHeaders),
        data: params ?? formData,
      );
    } on DioException catch (e) {
      return NetworkDioErrorUtil.responseFailedFromDioException<T>(e);
    }
    return _castResponseToModel<T>(httpResponse,
        fromJsonToModel: fromJsonToModel);
  }

  /// PUT
  Future<ResponseState<T>> put<T>(
    String endpoint, {
    Map<String, dynamic>? params,
    dynamic formData,
    T Function(Map<String, dynamic> map)? fromJsonToModel,
    Map<String, dynamic>? customHeaders,
  }) async {
    Response? httpResponse;
    try {
      httpResponse = await _dio.put(
        endpoint,
        options: addCustomHeaders(_dio.options, customHeaders),
        data: params ?? formData,
      );
    } on DioException catch (e) {
      return NetworkDioErrorUtil.responseFailedFromDioException<T>(e);
    }
    return _castResponseToModel<T>(httpResponse,
        fromJsonToModel: fromJsonToModel);
  }

  /// DELETE
  Future<ResponseState<T>> delete<T>(
    String endpoint, {
    Map<String, dynamic>? params,
    dynamic formData,
    T Function(Map<String, dynamic> map)? fromJsonToModel,
    Map<String, dynamic>? customHeaders,
  }) async {
    Response? httpResponse;
    try {
      httpResponse = await _dio.delete(
        endpoint,
        options: addCustomHeaders(_dio.options, customHeaders),
        data: params ?? formData,
      );
    } on DioException catch (e) {
      return NetworkDioErrorUtil.responseFailedFromDioException<T>(e);
    }
    return _castResponseToModel<T>(httpResponse,
        fromJsonToModel: fromJsonToModel);
  }

  ResponseState<T> _castResponseToModel<T>(Response? httpResponse,
      {Function(Map<String, dynamic> map)? fromJsonToModel}) {
    try {
      final isSuccessHttpStatus =
          successHttpStatusCodes.contains(httpResponse?.statusCode);
      if (isSuccessHttpStatus) {
        T? parsedData;
        try {
          parsedData = fromJsonToModel != null
              ? fromJsonToModel(httpResponse?.data)
              : null;
        }catch(e) {
          MainLogger.printLogException(error: e as Error);
        }
        return parsedData == null ? NetworkDioErrorUtil.responseFailedFromHttpResponse<T>(httpResponse):
        ResponseSuccess<T>(data: parsedData);
      } else {
        return NetworkDioErrorUtil.responseFailedFromHttpResponse(httpResponse);
      }
    } on DioException catch (e) {
      return NetworkDioErrorUtil.responseFailedFromDioException<T>(e);
    }
  }

  Options addCustomHeaders(
      BaseOptions options, Map<String, dynamic>? customHeaders) {
    var tempOptions = Options(
      method: options.method,
      sendTimeout: options.sendTimeout,
      receiveTimeout: options.receiveTimeout,
      extra: options.extra,
      headers: options.headers,
      preserveHeaderCase: options.preserveHeaderCase,
      responseType: options.responseType,
      validateStatus: options.validateStatus,
      followRedirects: options.followRedirects,
      maxRedirects: options.maxRedirects,
      requestEncoder: options.requestEncoder,
      responseDecoder: options.responseDecoder,
      listFormat: options.listFormat,
    );
    if (customHeaders != null) tempOptions.headers?.addAll(customHeaders);

    return tempOptions;
  }
}
