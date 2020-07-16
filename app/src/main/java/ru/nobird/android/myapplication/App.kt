package ru.nobird.android.myapplication

import android.app.Application
import android.widget.Toast
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(applicationContext)) {
            val client = AndroidFlipperClient.getInstance(applicationContext)
            client.addPlugin(InspectorFlipperPlugin(applicationContext, DescriptorMapping.withDefaults()))
            client.addPlugin(DatabasesFlipperPlugin(applicationContext))
            client.addPlugin(SharedPreferencesFlipperPlugin(applicationContext))
//            client.addPlugin(NetworkFlipperPlugin())
            client.start()
        }

    }
}