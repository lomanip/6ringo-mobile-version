part of 'sign_up_bloc.dart';

@immutable
sealed class SignUpState {}

final class SignUpInitial extends SignUpState {}

final class SignUpLoading extends SignUpState {}

final class SignUpSuccess extends SignUpState {
  SignUpSuccess({required this.response});

  final ResponseState<SignUpResponseEntity> response;
}

final class SignUpFailed extends SignUpState {
  SignUpFailed({required this.response});

  final ResponseState<SignUpResponseEntity> response;
}
