import 'package:equatable/equatable.dart';

class SignInResponseEntity extends Equatable {
  const SignInResponseEntity({this.id, this.token, this.activeProfile});

  final int? id;
  final String? token;
  final String? activeProfile;

  @override
  List<Object?> get props => [
        id,
        token,
        activeProfile,
      ];
}
