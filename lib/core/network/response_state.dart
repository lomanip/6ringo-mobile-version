import 'package:dio/dio.dart';

abstract class ResponseState<T> {
  final T? data;
  final DioException? exception;

  const ResponseState({
    this.data,
    this.exception,
  });
}

class ResponseSuccess<T> extends ResponseState<T> {
  const ResponseSuccess({required T data}) : super(data: data);
}

class ResponseFailed<T> extends ResponseState<T> {
  const ResponseFailed({required DioException exception})
      : super(exception: exception);
}
