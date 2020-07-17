package ru.nobird.android.myapplication.data

import androidx.annotation.WorkerThread
import org.json.JSONArray
import org.json.JSONObject
import ru.nobird.android.myapplication.viewmodel.Item
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object NetworkManager {
    private const val BASE_URL = "http://10.0.2.2:3000"
    private const val MOVIES = "/movies"

    private const val FIELD_ID = "id"
    private const val FIELD_NAME = "name"

    private const val TIMEOUT = 30000

    @WorkerThread
    fun getItems(): List<Item> {
        var connection: HttpURLConnection? = null

        try {
            connection = (URL(BASE_URL + MOVIES).openConnection() as HttpURLConnection)
                .apply {
                    connectTimeout = TIMEOUT
                    requestMethod = "GET"
                    doInput = true
                }

            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                return emptyList()
            }

            val text = InputStreamReader(connection.inputStream).use { it.readText() }

            val jsonArray = JSONArray(text)
            val list = mutableListOf<Item>()

            for (i in 0 until jsonArray.length()) {
                val json = jsonArray.getJSONObject(i)
                list += Item(
                    id = json.getInt(FIELD_ID),
                    name = json.getString(FIELD_NAME)
                )
            }
            return list
        } finally {
            connection?.disconnect()
        }
    }

    @WorkerThread
    fun createItem(item: Item) {
        var connection: HttpURLConnection? = null
        try {
            connection = (URL(BASE_URL + MOVIES).openConnection() as HttpURLConnection)
                .apply {
                    connectTimeout = TIMEOUT
                    requestMethod = "POST"
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json")
                }

            val data = JSONObject().apply {
                put(FIELD_ID, item.id)
                put(FIELD_NAME, item.name)
            }

            OutputStreamWriter(connection.outputStream).use {
                it.write(data.toString())
            }
            connection.responseCode
        } finally {
            connection?.disconnect()
        }
    }

    @WorkerThread
    fun deleteItem(item: Item) {
        TODO()
    }
}