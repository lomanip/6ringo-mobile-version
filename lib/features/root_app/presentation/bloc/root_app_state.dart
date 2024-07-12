part of 'root_app_bloc.dart';

@immutable
sealed class RootAppState {}

final class RootAppInitial extends RootAppState {}

final class RootAppPageChanged extends RootAppState {
  final int pageIndex;

  RootAppPageChanged({required this.pageIndex});
}
