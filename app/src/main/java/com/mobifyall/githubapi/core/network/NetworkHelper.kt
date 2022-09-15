package com.mobifyall.githubapi.core.network

import android.util.Log
import com.google.gson.Gson
import com.mobifyall.githubapi.commons.convertToJson
import com.mobifyall.githubapi.commons.getIntOrZero
import com.mobifyall.githubapi.core.models.ErrorResponse
import retrofit2.HttpException

@Throws(GithubException::class)
suspend fun <T> asyncCallOrThrowsNetworkException(apiName: String, block: suspend () -> T): T {
    return try {
        block.invoke()
    } catch (e: HttpException) {
        val json = e.response()?.errorBody()?.source()?.readUtf8()
        Log.d(
            "API",
            "API $apiName error ${e.message}" +
                    "\n" +
                    "message: ${e.response()?.message()}" +
                    "\n" +
                    "message: ${e.response()?.code()}" +
                    "\n" +
                    "source: ${e.response()?.errorBody()?.source()}" + "\n" +
                    "json: ${e.response()?.errorBody()?.convertToJson()}"
        )

        val error = Gson().fromJson(json, ErrorResponse::class.java)

        throw (GithubException(
            error.getMessageText(),
            e.response()?.code().getIntOrZero(),
        ))
    }
}
