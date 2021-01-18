package com.newton.eventgo.retrofit.callback

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.internal.EverythingIsNonNull

const val MENSAGEM_ERRO_RESPOSTA_NAO_SUCEDIDA = "Resposta não sucedida"
const val MENSAGEM_ERRO_FALHA_COMUNICAO = "Falha de Comunicação"

class CallbackWithReturn<T>(private val callback: AnswerCallback<T>) : Callback<T> {

    @EverythingIsNonNull
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            val result = response.body()
            if (result != null) {
                callback.whenSuccess(result, response.code())
            }
        } else {
            callback.whenFailure(MENSAGEM_ERRO_RESPOSTA_NAO_SUCEDIDA)
        }
    }

    @EverythingIsNonNull
    override fun onFailure(call: Call<T>, t: Throwable) {
        callback.whenFailure(MENSAGEM_ERRO_FALHA_COMUNICAO + t.message)
    }

    interface AnswerCallback<T> {
        fun whenSuccess(result: T, requestCode: Int)
        fun whenFailure(error: String)
    }

}