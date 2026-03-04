package com.joaorosa.conectandoapidummyjson

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaorosa.conectandoapidummyjson.adapter.DummyImageAdapter
import com.joaorosa.conectandoapidummyjson.model.ProductResponse
import com.joaorosa.conectandoapidummyjson.api.RetrofitService
import com.joaorosa.conectandoapidummyjson.databinding.ActivityMainBinding
import com.joaorosa.conectandoapidummyjson.model.DummyImages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var jobDummy: Job? = null
    private var dummyImageAdapter = DummyImageAdapter()
    var gridLayoutManager: GridLayoutManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.rvDummy.adapter = dummyImageAdapter
        binding.rvDummy.layoutManager = LinearLayoutManager(this)


        recoveryImageDummy()
    }

    private fun recoveryImageDummy() {

        jobDummy = CoroutineScope(Dispatchers.IO).launch {
            var resposta: Response<ProductResponse>? = null

            try {
                resposta = RetrofitService.ApiDummy.recoveryImagesDummy()
            }catch (e: Exception){
                exibirMensagem("Erro ao fazer a requisição")
            }

            if( resposta != null ){
                if (resposta.isSuccessful) {
                    val dummyResponseApi = resposta.body()

                    if (dummyResponseApi != null) {
                        val listaParaAdapter = dummyResponseApi.products // <--- Ajuste para o nome correto da variável no seu Model

                        withContext(Dispatchers.Main) {
                            dummyImageAdapter.addList(listaParaAdapter)
                        }
                    }
                }else{
                    exibirMensagem("Não foi possível recuperar o filme recente CODIGO: ${resposta.code()}")
                }
            }else{
                exibirMensagem("Não foi possível fazer a requisição")
            }

        }
    }

    private fun exibirMensagem( mensagem: String ) {
        Toast.makeText(
            applicationContext,
            mensagem,
            Toast.LENGTH_LONG
        ).show()
    }
}