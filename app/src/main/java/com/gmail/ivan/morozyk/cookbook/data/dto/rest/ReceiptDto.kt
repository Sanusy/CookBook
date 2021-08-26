package com.gmail.ivan.morozyk.cookbook.data.dto.rest

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReceiptDto(
    val aggregateLikes: Int,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val creditsText: String,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val license: String,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val originalId: Any?,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val winePairing: WinePairing,
){
    @JsonClass(generateAdapter = true)
    data class AnalyzedInstruction(
        val name: String,
        val steps: List<Step>,
    )

    @JsonClass(generateAdapter = true)
    data class ExtendedIngredient(
        val aisle: String,
        val amount: Double,
        val consistency: String,
        val id: Int,
        val image: String,
        val measures: Measures,
        val meta: List<String>,
        val metaInformation: List<String>,
        val name: String,
        val nameClean: String,
        val original: String,
        val originalName: String,
        val originalString: String,
        val unit: String,
    ) {
        @JsonClass(generateAdapter = true)
        data class Measures(
            val metric: MetricMeasures,
            val us: UsMeasures,
        ) {
            @JsonClass(generateAdapter = true)
            data class MetricMeasures(
                val amount: Double,
                val unitLong: String,
                val unitShort: String,
            )

            @JsonClass(generateAdapter = true)
            data class UsMeasures(
                val amount: Double,
                val unitLong: String,
                val unitShort: String,
            )
        }
    }

    @JsonClass(generateAdapter = true)
    data class WinePairing(
        val pairedWines: List<String>,
        val pairingText: String,
        val productMatches: List<ProductMatches>,
    ) {
        @JsonClass(generateAdapter = true)
        data class ProductMatches(
            val averageRating: Double,
            val description: String,
            val id: Int,
            val imageUrl: String,
            val link: String,
            val price: String,
            val ratingCount: Double,
            val score: Double,
            val title: String,
        )
    }

    @JsonClass(generateAdapter = true)
    data class Step(
        val equipment: List<Equipment>,
        val ingredients: List<Ingredient>,
        val number: Int,
        val step: String,
    ) {
        @JsonClass(generateAdapter = true)
        data class Equipment(
            val id: Int,
            val image: String,
            val localizedName: String,
            val name: String,
        )

        @JsonClass(generateAdapter = true)
        data class Ingredient(
            val id: Int,
            val image: String,
            val localizedName: String,
            val name: String,
        )
    }
}