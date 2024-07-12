import '../../../../../core/network/response_state.dart';
import '../../domain/entities/sign_up_request_entity.dart';
import '../../domain/repositories/sign_up_repository.dart';
import '../data_sources/sign_up_remote_data.dart';
import '../models/sign_up_response_model.dart';

class SignUpRepositoryImpl implements SignUpRepository {
  SignUpRepositoryImpl(this._remoteData);

  final SignUpRemoteData _remoteData;

  @override
  Future<ResponseState<SignUpResponseModel>> signUp(SignUpRequestEntity user) {
    return _remoteData.signUp(user);
  }
}
