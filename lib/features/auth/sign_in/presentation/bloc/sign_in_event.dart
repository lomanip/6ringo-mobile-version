part of 'sign_in_bloc.dart';

@immutable
sealed class SignInEvent {}

final class SignInWithEmailEvent extends SignInEvent {
  final String email;
  final String password;

  SignInWithEmailEvent(
      {required this.email, required this.password});
}
