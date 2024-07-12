import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../../../../core/network/response_state.dart';
import '../../../../../core/preferences/preferences.dart';
import '../../../../../core/preferences/preferences_keys.dart';
import '../../domain/entities/sign_in_response_entity.dart';
import '../../domain/use_cases/sign_in_with_email_use_case.dart';

part 'sign_in_event.dart';
part 'sign_in_state.dart';

class SignInBloc extends Bloc<SignInEvent, SignInState> {
  final SignInWithEmailUseCase _signInWithEmailUseCase;

  SignInBloc({
    required SignInWithEmailUseCase signInWithEmailUseCase,
  })  : _signInWithEmailUseCase = signInWithEmailUseCase,
        super(SignInInitial()) {
    /// Event sign in with email
    on<SignInWithEmailEvent>((event, emit) async {
      emit(SignInLoading());
      final response = await _signInWithEmailUseCase.execute(
          event.email, event.password);
      if (response is ResponseSuccess) {
        await saveTokenToPrefs(response.data);
        emit(SignInSuccess(response: response));
      } else {
        emit(SignInFailed(response: response));
      }
    });
  }

  Future<void> saveTokenToPrefs(SignInResponseEntity? data) async {
    await SecurePreferences.writeString(
        key: PreferencesKeys.tokenKey, value: data?.token);
    await SecurePreferences.writeString(
        key: PreferencesKeys.userIdKey, value: '${data?.id ?? ''}');
  }
}
