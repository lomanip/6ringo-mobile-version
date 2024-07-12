import 'package:flutter/foundation.dart';

class EndPoints {
  static String get baseUrl {
    if (kDebugMode) {
      return 'https://snip.snigsbackend.com';
    }
    return 'https://snip.snigsbackend.com';
  }

  /// Auth endpoints
  static const String signIn = 'api/auth/login';
  static const String signUp = 'api/auth/register';
  static const String logOut = 'api/auth/logout';

  /// User profile
  static const String userProfile = 'api/marchands';

  /// Notifications endpoints
  static const String notifications = 'api/transaction/activity';
  static const String deleteNotification = 'api/transaction/activity';

  /// Transactions endpoints
  static const String transactions = 'api/transaction';

  /// Recharge endpoints
  static const String transactionToken = '/api/marchands/getTransToken';
  static const String recharge = '/api/tranfert/transfert';

  /// Subscriptions endpoints
  static const String subscribe = 'api/abonnement/subscribe';
  static const String suspendSubscription = 'api/abonnement/change';
  static const String addSubscription = 'api/abonnement/add';
  static const String deleteSubscription = 'api/abonnement/delete';
  static const String subscribers = 'api/abonnement/ListSubscriptionByAbonnement';
  static const String companySubscriptions = 'api/abonnement';
  static const String merchantSubscriptions = 'api/abonnement/ListSubscription';
  static const String recommendedSubscriptions = 'api/abonnement/listofsuggestions';
}
