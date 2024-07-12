import '../../../features/root_app/presentation/bloc/root_app_bloc.dart';
import '../init.dart';

void registerRootDependencies() {
  // BLoC
  serviceLocator.registerLazySingleton<RootAppBloc>(() => RootAppBloc());
}
