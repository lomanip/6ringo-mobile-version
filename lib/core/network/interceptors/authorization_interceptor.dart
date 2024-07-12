import 'package:dio/dio.dart';

import '../../preferences/preferences.dart';
import '../../preferences/preferences_keys.dart';

class AuthorizationInterceptor extends Interceptor {
  @override
  Future<void> onRequest(
      RequestOptions options, RequestInterceptorHandler handler) async {
    if (needAuthorizationHeader(options)) {
      options.headers['Authorization'] =
          'Bearer ${(await SecurePreferences.readString(key: PreferencesKeys.tokenKey)) ?? ''}';
    }
    super.onRequest(options, handler);
  }

  bool needAuthorizationHeader(RequestOptions options) {
    if (!options.path.contains('auth')) {
      return true;
    } else if (options.path.contains('logout')) {
      return true;
    }
    return false;
  }
}
