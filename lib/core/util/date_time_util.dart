class DateTimeUtil {
  static DateTime? parseDate(String dateStr) {
    DateTime? dateTime;
    try {
      dateTime = DateTime.parse(dateStr);
    } on FormatException catch (_) {}
    return dateTime;
  }

  static String? getSinceWeek(String date) {
    return '';
  }

  static String? getSinceDays(String date) {
    return '';
  }

  static String? getSinceHours(String date) {
    return '';
  }

  static String? getSinceMinutes(String date) {
    return '';
  }
}
