
import '../../domain/entities/sign_up_response_entity.dart';

class SignUpResponseModel extends SignUpResponseEntity {
  const SignUpResponseModel({
    super.id,
    super.email,
    super.dialCode,
    super.phoneNumber,
  });

  factory SignUpResponseModel.fromJson(Map<String, dynamic> map) {
    return SignUpResponseModel(
      id: map['marchand'],
      email: map['emailPrincipal'],
      dialCode: map['dialCode'],
      phoneNumber: map['telPrincipal'],
    );
  }
}
