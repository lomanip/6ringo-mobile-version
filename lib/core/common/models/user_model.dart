

import '../entities/user_entity.dart';

class UserModel extends UserEntity {
  const UserModel(
      {
        super.firstName,
        super.lastName,
        super.email,
        super.phoneNumber,
        super.country,
        super.address,
        super.currency,
        super.language,
        super.image,
        super.timeZone,
    });

  factory UserModel.fromJson(Map<String, dynamic> map) {
    final respData = map['Data'];
    return UserModel(
      firstName: respData['firstName'],
      lastName: respData['lastName'],
      email: respData['email'],
      phoneNumber: respData['phoneNumber'],
      country: respData['country'],
      address: respData['address'],
      currency: respData['currency'],
      language: respData['language'],
      image: respData['image'],
      timeZone: respData['timeZone'],
    );
  }
}
