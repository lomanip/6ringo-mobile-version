import '../../../../../core/network/response_state.dart';
import '../entities/sign_in_response_entity.dart';

abstract interface class SignInRepository {
  Future<ResponseState<SignInResponseEntity>> signInWithEmail(
      String email, String password);
}
