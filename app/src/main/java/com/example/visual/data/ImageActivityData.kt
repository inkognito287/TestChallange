package com.example.visual.data

import com.example.visual.model.ItemUrl

class ImageActivityData {
    private var list=ArrayList<ItemUrl>()
    init{
    list.add(ItemUrl("https://i1.wallbox.ru/wallpapers/main/201120/2d56741e0a7ee12673870def1d8a9856.jpg"))
    list.add(ItemUrl("https://www.nastol.com.ua/download.php?img=201812/1680x1050/nastol.com.ua-308289.jpg"))
    list.add(ItemUrl("https://nastol.net/wallpaper/big/63/8526954-gory-sneg-voda-nebo-oblaka-peyzazhi.jpg"))
    list.add(ItemUrl("https://1.bp.blogspot.com/-7XiflTmG5fo/XF5euEL731I/AAAAAAAACT8/WyPRDNGUszo4Trq5rasMa_fUAv-vMDl-QCKgBGAs/w3840-h1599-p-k-no-nu/mountain-landscape-trees-nature-forest-trees-scenery-4K-80.jpg"))
    }
    fun getList(): ArrayList<ItemUrl> {
        return list
    }
}