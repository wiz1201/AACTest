package com.sumin.aactest.utilities

import android.content.Context
import com.sumin.aactest.api.APIService
import com.sumin.aactest.data.AppDatabase
import com.sumin.aactest.data.LocalReposirtory
import com.sumin.aactest.data.SearchRepository
import com.sumin.aactest.viewmodel.APIViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getLocalReposirtory(context: Context): LocalReposirtory {
        return LocalReposirtory.getInstance(
            AppDatabase.getDatabase(context.applicationContext).userDao())
    }

    fun getAPIViewModelFactory(
        context: Context) : APIViewModelFactory {
        val searchRepo = SearchRepository(APIService.create())
        return APIViewModelFactory(searchRepo, getLocalReposirtory(context))
    }
    /*
    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {
        return GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))
    }

    fun providePlantListViewModelFactory(fragment: Fragment): PlantListViewModelFactory {
        return PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
                getGardenPlantingRepository(context), plantId)
    }

    fun provideGalleryViewModelFactory(): GalleryViewModelFactory {
        val repository = UnsplashRepository(UnsplashService.create())
        return GalleryViewModelFactory(repository)
    }*/
}
