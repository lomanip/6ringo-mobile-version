import 'package:bloc/bloc.dart';
import 'package:flutter/material.dart';

part 'root_app_event.dart';
part 'root_app_state.dart';

class RootAppBloc extends Bloc<RootAppEvent, RootAppState> {
  RootAppBloc() : super(RootAppInitial()) {
    on<RootAppChangePageIndexEvent>((event, emit) {
      emit(RootAppPageChanged(pageIndex: event.index));
    });
  }
}
