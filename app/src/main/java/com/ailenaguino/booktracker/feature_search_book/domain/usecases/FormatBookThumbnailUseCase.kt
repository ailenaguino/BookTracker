package com.ailenaguino.booktracker.feature_search_book.domain.usecases

import javax.inject.Inject

class FormatBookThumbnailUseCase @Inject constructor() {
    operator fun invoke(thumbnail: String): String {
        return if(thumbnail.contains("&zoom=5")){
            thumbnail.replaceAfter("img=1","")
        }else thumbnail
    }
}