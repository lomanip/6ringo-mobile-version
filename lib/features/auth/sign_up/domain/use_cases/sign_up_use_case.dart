import '../../../../../core/network/response_state.dart';
import '../entities/sign_up_request_entity.dart';
import '../entities/sign_up_response_entity.dart';
import '../repositories/sign_up_repository.dart';

class SignUpUseCase {
  SignUpUseCase(this._repository);

  final SignUpRepository _repository;

  Future<ResponseState<SignUpResponseEntity>> execute(
      SignUpRequestEntity user) {
    return _repository.signUp(user);
  }
}
