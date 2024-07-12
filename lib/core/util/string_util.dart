class StringUtil {
  static truncateString(
      {required String wordString,
      required int maxLength,
      String prefix = '',
      String suffix = '...'}) {
    return wordString.length > maxLength
        ? '$prefix${wordString.substring(0, maxLength)}$suffix'
        : wordString;
  }
}
