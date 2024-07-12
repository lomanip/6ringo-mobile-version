import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:six_ringo/features/root_app/presentation/screens/root_app_screen.dart';

import 'core/common/cubits/user_session/user_session_cubit.dart';
import 'core/config/theme/color.dart';
import 'core/di/init.dart';
import 'features/auth/sign_in/presentation/bloc/sign_in_bloc.dart';
import 'features/auth/sign_up/presentation/bloc/sign_up_bloc.dart';
import 'features/home/presentation/bloc/home_bloc.dart';
import 'features/root_app/presentation/bloc/root_app_bloc.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  SystemChrome.setSystemUIOverlayStyle(const SystemUiOverlayStyle(
    statusBarColor: Colors.transparent,
    systemNavigationBarColor: Colors.white,
    systemNavigationBarDividerColor: Colors.white,
  ));

  // Inject all dependencies
  await initDependencies();

  runApp(MultiBlocProvider(
    providers: [
      /// User session cubit
      BlocProvider(
        create: (_) => serviceLocator<UserSessionCubit>(),
      ),

      /// Sign in bloc
      BlocProvider(
        create: (_) => serviceLocator<SignInBloc>(),
      ),

      /// Sign up bloc
      BlocProvider(
        create: (_) => serviceLocator<SignUpBloc>(),
      ),

      /// Root app bloc
      BlocProvider(
        create: (_) => serviceLocator<RootAppBloc>(),
      ),

      /// Home bloc
      BlocProvider(
        create: (_) => serviceLocator<HomeBloc>(),
      ),
    ],
    child: MaterialApp(
      title: '6ringo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: primaryColor),
        scaffoldBackgroundColor: Colors.white,
        canvasColor: Colors.white,
        useMaterial3: true,
      ),
      debugShowCheckedModeBanner: false,
      home: const RootAppScreen(),
    ),
  ));
}
