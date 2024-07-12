class InputValidator {
  static bool validateUserName(String? value) {
    return (value?.length ?? 0) >= 3;
  }

  static bool validateAddress(String? value) {
    return (value?.length ?? 0) >= 2;
  }

  static bool validateEmail(String? value) {
    return (value?.length ?? 0) >= 3;
  }

  static bool validatePhoneNumber(String? value) {
    return (value?.length ?? 0) >= 6;
  }

  static bool validateCode(String? value) {
    return (value?.length ?? 0) >= 4;
  }

  static validatePassword(String? value) {
    return (value?.length ?? 0) >= 3;
  }

  static validatePasswordMatching(String? password1, String? password2) {
    return password1 == password2 &&
        password1?.isNotEmpty == true &&
        password2?.isNotEmpty == true;
  }

  static validateText(String? value) {
    return (value?.length ?? 0) >= 2;
  }

  static validateAmount(String? value) {
    return (value?.length ?? 0) >= 2;
  }

  static validateDate(String? value) {
    return (value?.length ?? 0) >= 2;
  }
}
