import 'dart:convert';

import 'package:flutter/services.dart';
import 'package:flutter_secure_storage/flutter_secure_storage.dart';

const prefs = FlutterSecureStorage();

abstract class SecurePreferences {
  static Future<bool?> readBool({required String key}) async {
    final encodedString = await _readKey(key);
    if (encodedString == null) return null;

    return jsonDecode(encodedString) as bool;
  }

  static writeBool({required String key, required bool value}) async {
    final encodedString = jsonEncode(value);
    await _writeValue(key, encodedString);
  }

  static Future<int?> readInt({required String key}) async {
    final encodedString = await _readKey(key);
    if (encodedString == null) return null;

    return jsonDecode(encodedString) as int?;
  }

  static writeInt({required String key, required int? value}) async {
    final encodedString = jsonEncode(value);
    await _writeValue(key, encodedString);
  }

  static Future<double?> readDouble({required String key}) async {
    final encodedString = await _readKey(key);
    if (encodedString == null) return null;

    return jsonDecode(encodedString) as double?;
  }

  static writeDouble({required String key, required double? value}) async {
    final encodedString = jsonEncode(value);
    await _writeValue(key, encodedString);
  }

  static Future<String?> readString({required String key}) async {
    return await _readKey(key);
  }

  static writeString({required String key, required String? value}) async {
    await _writeValue(key, value);
  }

  static Future<T?> readObject<T>({required String key}) async {
    final encodedString = await _readKey(key);
    if (encodedString == null) return null;

    return jsonDecode(encodedString) as T?;
  }

  static writeObject<T>({required String key, required T? value}) async {
    final encodedString = jsonEncode(value);
    await _writeValue(key, encodedString);
  }

  static Future<List<T>?> readArrayOfObjects<T>({required String key}) async {
    final encodedString = await _readKey(key);
    if (encodedString == null) return null;

    return jsonDecode(encodedString) as List<T>?;
  }

  static writeArrayOfObjects<T>(
      {required String key, required List<T>? value}) async {
    final encodedString = jsonEncode(value);
    await _writeValue(key, encodedString);
  }

  static deleteKey(String key) async {
    await _deleteKey(key);
  }

  static Future<void> _writeValue(String key, String? value) async {
    try {
      await prefs.write(key: key, value: value);
    } on PlatformException catch (_) {}
  }

  static Future<String?> _readKey(String key) async {
    try {
      return await prefs.read(key: key);
    } on PlatformException catch (_) {
      return null;
    }
  }

  static Future<void> _deleteKey(String key) async {
    try {
      await prefs.delete(key: key);
    } on PlatformException catch (_) {}
  }
}
