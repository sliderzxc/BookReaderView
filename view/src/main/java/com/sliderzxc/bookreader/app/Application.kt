package com.sliderzxc.bookreader.app

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

class Application : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }
}