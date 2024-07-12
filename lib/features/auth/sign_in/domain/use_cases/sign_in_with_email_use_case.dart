import '../../../../../core/network/response_state.dart';
import '../entities/sign_in_response_entity.dart';
import '../repositories/sign_in_repository.dart';

class SignInWithEmailUseCase {
  SignInWithEmailUseCase(this._repository);

  final SignInRepository _repository;

  Future<ResponseState<SignInResponseEntity>> execute(
      String email, String password) {
    return _repository.signInWithEmail(email, password);
  }
}
