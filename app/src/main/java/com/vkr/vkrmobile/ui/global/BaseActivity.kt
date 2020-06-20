package com.vkr.vkrmobile.ui.global

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.vkr.vkrmobile.model.system.SystemMessageNotifier
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import java.util.concurrent.TimeUnit
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var systemMessageNotifier: SystemMessageNotifier

    private var messageNotifierDisposable = CompositeDisposable()

    protected abstract val layoutRes: Int
    protected abstract val navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
        messageNotifierDisposable.add(systemMessageNotifier.notifier
            .throttleFirst(50, TimeUnit.MILLISECONDS)
            .subscribe({
                val toast = Toast.makeText(this, it, Toast.LENGTH_LONG)
                (toast.view.findViewById<View>(android.R.id.message) as TextView).gravity =
                    Gravity.CENTER
                toast.show()
            }, { })
        )
    }

    override fun onPause() {
        messageNotifierDisposable.dispose()
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}
