import '../../domain/entities/sign_in_response_entity.dart';

class SignInResponseModel extends SignInResponseEntity {
  const SignInResponseModel({super.id, super.token, super.activeProfile});

  factory SignInResponseModel.fromJson(Map<String, dynamic> map) {
    final respData = map['Data'][0];
    final formattedToken = respData['token'].replaceAll('Bearer ', '').trim();
    return SignInResponseModel(
      id: respData['id'],
      token: formattedToken,
      activeProfile: respData['activeProfile'],
    );
  }
}
