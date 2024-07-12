import 'package:equatable/equatable.dart';

class UserEntity extends Equatable {
  const UserEntity({
    this.firstName,
    this.lastName,
    this.email,
    this.phoneNumber,
    this.country,
    this.address,
    this.currency,
    this.language,
    this.image,
    this.timeZone,
  });

  final String? firstName;
  final String? lastName;
  final String? email;
  final String? phoneNumber;
  final String? country;
  final String? address;
  final String? currency;
  final String? language;
  final String? image;
  final String? timeZone;

  @override
  List<Object?> get props => [
        firstName,
        lastName,
        email,
        phoneNumber,
        country,
        address,
        currency,
        language,
        image,
        timeZone,
      ];
}
