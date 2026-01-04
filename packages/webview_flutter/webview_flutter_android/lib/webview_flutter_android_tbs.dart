// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'src/android_webkit.g.dart';

export 'src/android_webview_controller.dart';
export 'src/android_webview_cookie_manager.dart';
export 'src/android_webview_platform.dart';

/// Initialize the X5 WebView environment.
Future<bool> initX5Environment() async {
  return await AndroidX5WebViewApi().initX5Environment();
}

/// Install the X5 WebView environment.
Future<bool> installX5Environment(
    {required String filePath, required String version}) async {
  await AndroidX5WebViewApi().install(filePath, version);
  await Future.delayed(const Duration(seconds: 3));
  return await initX5Environment();
}
