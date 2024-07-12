import 'package:equatable/equatable.dart';

class SignUpResponseEntity extends Equatable {
  const SignUpResponseEntity({
    this.id,
    this.email,
    this.dialCode,
    this.phoneNumber,
  });

  final String? id;
  final String? email;
  final String? dialCode;
  final String? phoneNumber;

  @override
  List<Object?> get props => [id];
}
