package maximmakarov.anylist.core

import android.app.Application
import maximmakarov.anylist.core.di.AppModule
import maximmakarov.anylist.core.di.DaggerAppComponent
import maximmakarov.anylist.core.di.Injector

/**
 * @author Maxim Makarov
 * @since 03.05.2017
 */
class AnyListApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        Injector.init(DaggerAppComponent.builder().appModule(AppModule(this)).build())
    }
}