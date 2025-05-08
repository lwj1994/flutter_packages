// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'src/android_webkit.g.dart';

export 'src/android_webview_controller.dart';
export 'src/android_webview_cookie_manager.dart';
export 'src/android_webview_platform.dart';

/// Initialize the X5 WebView environment.
Future<void> initX5Environment() async {
  return AndroidX5WebViewApi().initX5Environment();
}
