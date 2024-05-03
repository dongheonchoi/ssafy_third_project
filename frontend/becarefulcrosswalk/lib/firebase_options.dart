// File generated by FlutterFire CLI.
// ignore_for_file: type=lint
import 'package:becarefulcrosswalk/env/env.dart';
import 'package:firebase_core/firebase_core.dart' show FirebaseOptions;
import 'package:flutter/foundation.dart'
    show defaultTargetPlatform, kIsWeb, TargetPlatform;

/// Default [FirebaseOptions] for use with your Firebase apps.
///
/// Example:
/// ```dart
/// import 'firebase_options.dart';
/// // ...
/// await Firebase.initializeApp(
///   options: DefaultFirebaseOptions.currentPlatform,
/// );
/// ```
class DefaultFirebaseOptions {
  static FirebaseOptions get currentPlatform {
    if (kIsWeb) {
      return web;
    }
    switch (defaultTargetPlatform) {
      case TargetPlatform.android:
        return android;
      case TargetPlatform.iOS:
        return ios;
      case TargetPlatform.macOS:
        return macos;
      case TargetPlatform.windows:
        return windows;
      case TargetPlatform.linux:
        throw UnsupportedError(
          'DefaultFirebaseOptions have not been configured for linux - '
          'you can reconfigure this by running the FlutterFire CLI again.',
        );
      default:
        throw UnsupportedError(
          'DefaultFirebaseOptions are not supported for this platform.',
        );
    }
  }

  static const FirebaseOptions web = FirebaseOptions(
    apiKey: Env.webFirebaseApiKey,
    appId: Env.webFirebaseAppId,
    messagingSenderId: Env.firebaseMessagingSenderId,
    projectId: Env.firebaseProjectId,
    authDomain: Env.firebaseAuthDomain,
    storageBucket: Env.firebaseStorageBucket,
    measurementId: Env.webFirebaseMeasurementId,
  );

  static const FirebaseOptions android = FirebaseOptions(
    apiKey: Env.androidFirebaseApiKey,
    appId: Env.androidFirebaseAppId,
    messagingSenderId: Env.firebaseMessagingSenderId,
    projectId: Env.firebaseProjectId,
    storageBucket: Env.firebaseStorageBucket,
  );

  static const FirebaseOptions ios = FirebaseOptions(
    apiKey: Env.iosFirebaseApiKey,
    appId: Env.iosFirebaseAppId,
    messagingSenderId: Env.firebaseMessagingSenderId,
    projectId: Env.firebaseProjectId,
    storageBucket: Env.firebaseStorageBucket,
    iosBundleId: Env.firebaseIosBundleId,
  );

  static const FirebaseOptions macos = FirebaseOptions(
    apiKey: Env.macosFirebaseApiKey,
    appId: Env.macosFirebaseAppId,
    messagingSenderId: Env.firebaseMessagingSenderId,
    projectId: Env.firebaseProjectId,
    storageBucket: Env.firebaseStorageBucket,
    iosBundleId: Env.firebaseIosBundleId,
  );

  static const FirebaseOptions windows = FirebaseOptions(
    apiKey: Env.windowsFirebaseApiKey,
    appId: Env.windowsFirebaseAppId,
    messagingSenderId: Env.firebaseMessagingSenderId,
    projectId: Env.firebaseProjectId,
    authDomain: Env.firebaseAuthDomain,
    storageBucket: Env.firebaseStorageBucket,
    measurementId: Env.windowsFirebaseMeasurementId,
  );
}
