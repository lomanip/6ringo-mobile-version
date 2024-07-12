import '../../../../../core/network/response_state.dart';
import '../../domain/repositories/sign_in_repository.dart';
import '../data_sources/sign_in_remote_data.dart';
import '../models/sign_in_response_model.dart';

class SignInRepositoryImpl implements SignInRepository {
  SignInRepositoryImpl(this._remoteData);

  final SignInRemoteData _remoteData;

  @override
  Future<ResponseState<SignInResponseModel>> signInWithEmail(
      String email, String password) {
    return _remoteData.signInWithEmail(email, password);
  }
}
