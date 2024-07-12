import '../../../../features/auth/sign_in/data/data_sources/sign_in_remote_data.dart';
import '../../../../features/auth/sign_in/data/repositories/sign_in_repository_impl.dart';
import '../../../../features/auth/sign_in/domain/repositories/sign_in_repository.dart';
import '../../../../features/auth/sign_in/domain/use_cases/sign_in_with_email_use_case.dart';
import '../../../../features/auth/sign_in/presentation/bloc/sign_in_bloc.dart';
import '../../init.dart';

void registerSignInDependencies() {
  // Remote data source(API) Impl
  serviceLocator
    ..registerFactory<SignInRemoteData>(
        () => SignInRemoteDataImpl(apiClient: serviceLocator()))

    // Repository Impl
    ..registerFactory<SignInRepository>(
        () => SignInRepositoryImpl(serviceLocator()))

    // Use cases
    ..registerFactory<SignInWithEmailUseCase>(
        () => SignInWithEmailUseCase(serviceLocator()))

    // BLoC
    ..registerLazySingleton<SignInBloc>(() => SignInBloc(
        signInWithEmailUseCase: serviceLocator(),
    ));
}
