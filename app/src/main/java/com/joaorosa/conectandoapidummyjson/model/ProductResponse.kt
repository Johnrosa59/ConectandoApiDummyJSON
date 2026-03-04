package com.joaorosa.conectandoapidummyjson.model

data class ProductResponse(
    val products: List<DummyImages>, // Aqui está a lista de 20 ou 50 itens
    val total: Int,
    val skip: Int,
    val limit: Int
)