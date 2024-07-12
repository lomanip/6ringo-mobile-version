import '../../../../../core/network/response_state.dart';
import '../entities/sign_up_request_entity.dart';
import '../entities/sign_up_response_entity.dart';

abstract class SignUpRepository {
  Future<ResponseState<SignUpResponseEntity>> signUp(SignUpRequestEntity user);
}
