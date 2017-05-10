package maximmakarov.anylist.core.utils

import android.app.Fragment
import android.app.FragmentManager
import android.support.annotation.IdRes
import maximmakarov.anylist.R

/**
 * @author Maxim Makarov
 * @since 10.05.2017
 */
object FragmentUtils {

    /*@JvmOverloads fun slideLeft(manager: FragmentManager, fragment: Fragment, backstack: Boolean = true) {
        replace(manager, fragment, backstack, R.id.container, null, R.anim.slide_left, R.anim.slide_right, R.anim.slide_left, R.anim.slide_right)
    }

    @JvmOverloads fun slideTop(manager: FragmentManager, fragment: Fragment, backstack: Boolean = true) {
        replace(manager, fragment, backstack, R.id.container, null, R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down)
    }

    @JvmOverloads fun slideBottom(manager: FragmentManager, fragment: Fragment, backstack: Boolean = true) {
        replace(manager, fragment, backstack, R.id.container, null, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down, R.anim.slide_up)
    }*/

    fun fade(manager: FragmentManager, fragment: Fragment) {
        replace(manager, fragment, true, R.id.container, null, android.R.animator.fade_in, android.R.animator.fade_out)
    }

    fun add(manager: FragmentManager, fragment: Fragment) {
        manager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
    }

    @JvmOverloads fun replace(manager: FragmentManager, fragment: Fragment,
                              backstack: Boolean = true, @IdRes container: Int = R.id.container, name: String? = null,
                              enter: Int = 0, exit: Int = 0, popEnter: Int = 0, popExit: Int = 0) {
        val transaction = manager.beginTransaction()

        if (enter != 0 && exit != 0) {
            if (popEnter != 0 && popExit != 0) {
                transaction.setCustomAnimations(enter, exit, popEnter, popExit)
            } else {
                transaction.setCustomAnimations(enter, exit)
            }
        }
        transaction.replace(container, fragment, null)

        if (backstack) transaction.addToBackStack(name)

        transaction.commit()
    }

    fun remove(manager: FragmentManager, fragment: Fragment) {
        manager.beginTransaction().remove(fragment).commit()
    }

    fun getTopFragment(manager: FragmentManager): Fragment {
        return manager.findFragmentById(R.id.container)
    }

    fun hasOnTop(manager: FragmentManager, clazz: Class<out Fragment>): Boolean {
        return clazz.isInstance(getTopFragment(manager))
    }
}

