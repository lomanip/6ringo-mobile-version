import '../../../../features/auth/sign_up/data/data_sources/sign_up_remote_data.dart';
import '../../../../features/auth/sign_up/data/repositories/sign_up_repository_impl.dart';
import '../../../../features/auth/sign_up/domain/repositories/sign_up_repository.dart';
import '../../../../features/auth/sign_up/domain/use_cases/sign_up_use_case.dart';
import '../../../../features/auth/sign_up/presentation/bloc/sign_up_bloc.dart';
import '../../init.dart';

void registerSignUpDependencies() {
  // Remote data source(API) Impl
  serviceLocator
    ..registerFactory<SignUpRemoteData>(
        () => SignUpRemoteDataImpl(apiClient: serviceLocator()))

    // Repository Impl
    ..registerFactory<SignUpRepository>(
        () => SignUpRepositoryImpl(serviceLocator()))

    // Use cases
    ..registerFactory<SignUpUseCase>(() => SignUpUseCase(serviceLocator()))

    // BLoC
    ..registerLazySingleton<SignUpBloc>(
        () => SignUpBloc(signUpUseCase: serviceLocator()));
}
