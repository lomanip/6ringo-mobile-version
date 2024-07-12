import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

import '../../entities/user_entity.dart';

part 'user_session_state.dart';

class UserSessionCubit extends Cubit<UserSessionState> {
  UserSessionCubit() : super(UserSessionInitial());

  void updateUser(UserEntity? user) {
    if (user == null) {
      emit(UserSessionExpired());
    } else {
      emit(UserSessionLoaded(user: user));
    }
  }
}
