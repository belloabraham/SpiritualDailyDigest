package screen.favourites

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch
import org.cccsharonparish.core.data.repo.IContentRepo

class FavouriteScreenModel(
    private val contentRepo: IContentRepo
) : ScreenModel {
    val favouriteContents = contentRepo.getALiveListOfFavouriteContent()

    fun deleteFavourite(id: String) {
        screenModelScope.launch {
            contentRepo.removeFromFavouriteById(id)
        }
    }

    fun deleteAllFavourites(){
        screenModelScope.launch {
            contentRepo.deleteAllFavouriteContent()
        }
    }

}