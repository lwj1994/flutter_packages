package io.flutter.plugins.webviewflutter.tbs

import android.content.Context
import android.util.Log
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.QbSdk.PreInitCallback
import com.tencent.smtt.sdk.TbsListener
import org.jetbrains.annotations.TestOnly


/**
 * X5 WebView API implementation.
 *
 * Handles X5 kernel initialization and configuration.
 *
 * @author luwenjie on 2024/12/31 11:28:57
 */
class X5Api(private val context: Context) : AndroidX5WebViewApi {


    companion object {
        private const val TAG = "webview_flutter_x5Api"
    }

    @TestOnly
    fun init() {
        initX5Environment() {

        }
    }

    override fun initX5Environment(callback: (Result<Boolean>) -> Unit) {
        QbSdk.initX5Environment(context.applicationContext, object : PreInitCallback {
            override fun onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
                Log.d(TAG, "onCoreInitFinished")
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * @param isX5 是否使用X5内核
             */
            override fun onViewInitFinished(isX5: Boolean) {
                Log.d(TAG, "onViewInitFinished: isX5=$isX5")
                callback(Result.success(isX5))
            }
        })
        val map = mutableMapOf<String, Any>()
        map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER] = true
        map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
        QbSdk.initTbsSettings(map)
        QbSdk.setTbsListener(object :TbsListener{
            override fun onDownloadFinish(p0: Int) {
                
            }

            override fun onInstallFinish(p0: Int) {
                
            }

            override fun onDownloadProgress(p0: Int) {
                
            }

        })
        QbSdk.preInit(context, object : QbSdk.PreInitCallback {
            override fun onCoreInitFinished() {
                
            }

            override fun onViewInitFinished(p0: Boolean) {
                
            }

        })
        QbSdk.setDownloadWithoutWifi(true)
    }

    /**
     * Installs local TBS core from the specified file path.
     *
     * @param filePath Path to the X5 kernel file
     * @param version Version of the X5 kernel
     */
    override fun install(filePath: String, version: String) {
        Log.d(TAG, "install: filePath=$filePath, version=$version")
        if (filePath.isNotEmpty()) {
            QbSdk.installLocalTbsCore(context, version.toInt(), filePath)
            
        }
    }

   
}