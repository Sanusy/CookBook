package com.gmail.ivan.morozyk.cookbook.data.service

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.awaitResult
import com.github.kittinunf.fuel.moshi.moshiDeserializerOf
import com.github.kittinunf.result.Result
import com.gmail.ivan.morozyk.cookbook.BuildConfig
import com.gmail.ivan.morozyk.cookbook.data.dto.rest.ReceiptDto
import com.gmail.ivan.morozyk.cookbook.data.dto.rest.ReceiptListDto

interface ReceiptService {

    suspend fun getReceipts(query: String? = null): Result<ReceiptListDto, FuelError>

    suspend fun getReceipt(receiptId: String): Result<ReceiptDto, FuelError>
}

class ReceiptServiceImpl : ReceiptService {

    override suspend fun getReceipts(query: String?): Result<ReceiptListDto, FuelError> {
        val parameters = mutableListOf("apiKey" to API_KEY)

        if (!query.isNullOrEmpty()) {
            parameters.add("query" to query)
        }

        return Fuel.get("recipes/complexSearch/", parameters).awaitResult(
            moshiDeserializerOf(ReceiptListDto::class.java)
        )
    }

    override suspend fun getReceipt(receiptId: String): Result<ReceiptDto, FuelError> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val API_KEY = BuildConfig.COOKBOOK_API_KEY
    }
}