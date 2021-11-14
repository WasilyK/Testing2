package com.wasilyk.app.testing2

class DatasourceImpl : Datasource {

    private val data = mutableListOf<String>(
        "Маша",
        "Паша",
        "Даша",
        "Саша",
        "Наташа",
    )

    override fun getData() = data
}