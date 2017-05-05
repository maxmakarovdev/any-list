package maximmakarov.anylist.core.di

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
object Injector {

    lateinit var appComponent: AppComponent

    fun init(component: AppComponent) {
        appComponent = component
    }
}
