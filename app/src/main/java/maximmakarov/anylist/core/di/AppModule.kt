package maximmakarov.anylist.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import maximmakarov.anylist.storage.DataProvider
import javax.inject.Singleton

/**
 * @author Maxim Makarov
 * @since 05.05.2017
 */
@Module
class AppModule(mContext: Context) {

    private val context: Context = mContext

    @Singleton
    @Provides
    fun getContext() = context

    @Singleton
    @Provides
    fun getDataProvider() = DataProvider(context)
}