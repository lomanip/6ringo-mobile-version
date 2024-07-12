import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

import '../../../../../core/network/response_state.dart';
import '../../domain/entities/sign_up_request_entity.dart';
import '../../domain/entities/sign_up_response_entity.dart';
import '../../domain/use_cases/sign_up_use_case.dart';

part 'sign_up_event.dart';
part 'sign_up_state.dart';

class SignUpBloc extends Bloc<SignUpEvent, SignUpState> {
  final SignUpUseCase _signUpUseCase;

  SignUpBloc({
    required SignUpUseCase signUpUseCase,
  })  : _signUpUseCase = signUpUseCase,
        super(SignUpInitial()) {
    /// Event sign up
    on<SignUpWithEmailEvent>((event, emit) async {
      emit(SignUpLoading());
      final response = await _signUpUseCase.execute(event.user);
      if (response is ResponseSuccess) {
        emit(SignUpSuccess(response: response));
      } else {
        emit(SignUpFailed(response: response));
      }
    });
  }
}
