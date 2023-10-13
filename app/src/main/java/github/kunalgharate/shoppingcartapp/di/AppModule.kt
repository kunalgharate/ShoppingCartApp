package github.kunalgharate.shoppingcartapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.kunalgharate.shoppingcartapp.data.datasource.ProductDataSource
import github.kunalgharate.shoppingcartapp.data.datasource.UserDataSource
import github.kunalgharate.shoppingcartapp.data.repositories.ProductRepository
import github.kunalgharate.shoppingcartapp.data.repositories.UserRepository

// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideProductDataSource(): ProductDataSource {
        return ProductDataSource()
    }

    @Provides
    fun provideProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }

    @Provides
    fun provideUserDataSource(): UserDataSource {
        return UserDataSource()
    }

    @Provides
    fun provideUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepository(userDataSource)
    }

}
