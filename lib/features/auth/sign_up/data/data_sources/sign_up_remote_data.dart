import 'package:six_ringo/core/constants/end_points.dart';

import '../../../../../core/network/api_client.dart';
import '../../../../../core/network/response_state.dart';
import '../../domain/entities/sign_up_request_entity.dart';
import '../models/sign_up_response_model.dart';

abstract class SignUpRemoteData {
  Future<ResponseState<SignUpResponseModel>> signUp(SignUpRequestEntity user);
}

class SignUpRemoteDataImpl implements SignUpRemoteData {
  final ApiClient apiClient;

  SignUpRemoteDataImpl({required this.apiClient});

  @override
  Future<ResponseState<SignUpResponseModel>> signUp(
      SignUpRequestEntity user) async {
    return apiClient.post(EndPoints.signUp,
        params: <String, dynamic>{
          'firstName': user.firstName,
          'lastName': user.lastName,
          'email': user.email,
          'phoneNumber': user.phoneNumber,
          'password': user.password,
        },
        fromJsonToModel: SignUpResponseModel.fromJson);
  }
}
