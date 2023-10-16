package github.kunalgharate.shoppingcartapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.kunalgharate.shoppingcartapp.data.datasource.ProductDataSource
import github.kunalgharate.shoppingcartapp.data.datasource.UserDataSource
import github.kunalgharate.shoppingcartapp.data.repositories.ProductRepository
import github.kunalgharate.shoppingcartapp.data.repositories.UserRepository
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    fun provideProductDataSource(): ProductDataSource {
        return ProductDataSource()
    }

    @Provides
    @Named("test_product")
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }

}