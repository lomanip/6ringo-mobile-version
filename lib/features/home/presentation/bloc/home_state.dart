part of 'home_bloc.dart';

@immutable
sealed class HomeState {}

final class HomeInitial extends HomeState {}

final class HomeGetUserLoading extends HomeState {}

final class HomeGetUserNoToken extends HomeState {}

final class HomeGetUserSuccess extends HomeState {
  HomeGetUserSuccess({required this.response});

  final ResponseState<UserEntity> response;
}

final class HomeGetUserFailed extends HomeState {
  HomeGetUserFailed({required this.response});

  final ResponseState<UserEntity> response;
}
