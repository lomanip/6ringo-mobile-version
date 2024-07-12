import 'package:six_ringo/core/constants/end_points.dart';

import '../../../../../core/network/api_client.dart';
import '../../../../../core/network/response_state.dart';
import '../models/sign_in_response_model.dart';

abstract class SignInRemoteData {
  Future<ResponseState<SignInResponseModel>> signInWithEmail(
      String email, String password);
}

class SignInRemoteDataImpl implements SignInRemoteData {
  final ApiClient apiClient;

  SignInRemoteDataImpl({required this.apiClient});

  @override
  Future<ResponseState<SignInResponseModel>> signInWithEmail(
      String email, String password) async {
    return apiClient.post(EndPoints.signIn,
        params: <String, dynamic>{
          'email': email,
          'password': password,
        },
        fromJsonToModel: SignInResponseModel.fromJson);
  }
}
