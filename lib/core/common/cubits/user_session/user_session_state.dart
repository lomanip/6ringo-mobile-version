part of 'user_session_cubit.dart';

@immutable
sealed class UserSessionState {}

final class UserSessionInitial extends UserSessionState {}

final class UserSessionLoaded extends UserSessionState {
  final UserEntity? user;

  UserSessionLoaded({this.user});
}

final class UserSessionExpired extends UserSessionState {}
