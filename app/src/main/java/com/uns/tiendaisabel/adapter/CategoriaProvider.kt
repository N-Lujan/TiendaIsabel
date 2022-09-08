package com.uns.tiendaisabel.adapter

import com.uns.tiendaisabel.model.Categoria

class CategoriaProvider {
    companion object {
        val categoriaList = listOf<Categoria>(
            Categoria(
                "Aceites",
                "https://www.funcion.info/wp-content/uploads/2018/08/funcion-de-los-aceites.jpg"
            ),
            Categoria(
                "Conservas",
                "https://www.alamar.co/wp-content/uploads/2020/07/latas-atun.jpg"
            ),
            Categoria(
                "Golosinas",
                "https://m.media-amazon.com/images/I/613aFYFpXxL._SX425_.jpg"
            ),
            Categoria(
                "Arroz",
                "https://corporacionleosar.com/wp-content/uploads/2020/06/444-arroz-campero-x50kg.jpg"
            ),
            Categoria(
                "Azúcar",
                "https://dojiw2m9tvv09.cloudfront.net/56396/product/whatsappimage2021-06-26at10-55-18am2967.jpeg"
            ),
            Categoria(
                "Embutidos",
                "https://www.laiberica.com/wp-content/uploads/2019/12/HOT-DOG-1-320x389.jpg"
            ),
            Categoria(
                "Leche",
                "https://estaticos.muyinteresante.es/media/cache/500x375_thumb/uploads/images/dossier/555f4f41582c75822962ee3b/slide-leche.jpg"
            )
        )
    }
}