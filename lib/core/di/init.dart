import 'package:get_it/get_it.dart';

import '../network/api_client.dart';
import 'features/root_app_di.dart';
import 'features/auth/sign_in_di.dart';
import 'features/auth/sign_up_di.dart';
import 'user_session_di.dart';

final serviceLocator = GetIt.instance;

Future<void> initDependencies() async {
  /// API Client
  final apiClient = ApiClient();
  serviceLocator.registerLazySingleton(() => apiClient);

  /// Core
  registerUserSessionDependencies();
  registerRootDependencies();

  /// Auth DI
  registerSignInDependencies();
  registerSignUpDependencies();
}
