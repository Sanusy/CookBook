package com.gmail.ivan.morozyk.cookbook.data.dto.rest

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class IngredientListDto(
    val number: Int,
    val offset: Int,
    val results: List<Ingredient>,
    val totalResults: Int,
) {
    @JsonClass(generateAdapter = true)
    data class Ingredient(
        val id: Int,
        val image: String,
        val name: String,
    )
}