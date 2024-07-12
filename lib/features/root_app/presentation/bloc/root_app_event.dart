part of 'root_app_bloc.dart';

@immutable
sealed class RootAppEvent {}

final class RootAppChangePageIndexEvent extends RootAppEvent {
  final int index;

  RootAppChangePageIndexEvent({required this.index});
}
