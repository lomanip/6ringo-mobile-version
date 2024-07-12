part of 'sign_up_bloc.dart';

@immutable
sealed class SignUpEvent {}

final class SignUpWithEmailEvent extends SignUpEvent {
  final SignUpRequestEntity user;

  SignUpWithEmailEvent({required this.user});
}
