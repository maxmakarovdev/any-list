package maximmakarov.anylist.core.di

import dagger.Component
import maximmakarov.anylist.list.ListPresenter
import javax.inject.Singleton

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))

interface AppComponent {
    fun inject(presenter: ListPresenter)
}