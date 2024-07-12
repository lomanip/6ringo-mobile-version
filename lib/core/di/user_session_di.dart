import '../common/cubits/user_session/user_session_cubit.dart';
import 'init.dart';

void registerUserSessionDependencies() {
  serviceLocator.registerLazySingleton(() => UserSessionCubit());
}
