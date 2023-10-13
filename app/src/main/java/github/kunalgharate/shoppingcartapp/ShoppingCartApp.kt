package github.kunalgharate.shoppingcartapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ShoppingCartApp :Application() {

    override fun onCreate() {
        super.onCreate()
    }
}