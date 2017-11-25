package com.meetme.test

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.android.androidActivityScope
import com.github.salomonbrys.kodein.android.autoAndroidModule
import com.github.salomonbrys.kodein.lazy
import com.meetme.test.foosball.di.foosballLocaleModule
import com.meetme.test.foosball.di.foosballPreferencesModule
import com.twitter.sdk.android.core.Twitter

class TMGTestApplication : Application(), KodeinAware {

    override val kodein by Kodein.lazy {
        import(autoAndroidModule(this@TMGTestApplication))
        import(foosballLocaleModule)
        import(foosballPreferencesModule)
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(androidActivityScope.lifecycleManager)
        initTwitter()
    }

    private fun initTwitter() {
        Twitter.initialize(this)
    }
}