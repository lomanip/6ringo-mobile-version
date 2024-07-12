import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

import '../../../../core/common/cubits/user_session/user_session_cubit.dart';
import '../../../../core/common/entities/user_entity.dart';
import '../../../../core/network/response_state.dart';

part 'home_event.dart';
part 'home_state.dart';

class HomeBloc extends Bloc<HomeEvent, HomeState> {

  HomeBloc()
      :
        super(HomeInitial()) {
    /// Event
    on<HomeGetUserEvent>((event, emit) async {
      // TODO
    });
  }
}
