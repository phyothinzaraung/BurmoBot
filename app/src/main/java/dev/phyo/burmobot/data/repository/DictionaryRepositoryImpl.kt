package dev.phyo.burmobot.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.phyo.burmobot.data.local.ZipFileHelper
import dev.phyo.burmobot.data.local.dao.DictionaryDao
import dev.phyo.burmobot.data.model.DictionaryEntry
import dev.phyo.burmobot.domain.repository.DictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class DictionaryRepositoryImpl(
    private val context: Context,
    private val dictionaryDao: DictionaryDao
): DictionaryRepository {
    override suspend fun getDictionary(): List<DictionaryEntry> {
        return withContext(Dispatchers.IO){
            val cachedWords = dictionaryDao.getAllWords()
            if (cachedWords.isNotEmpty()){
                return@withContext cachedWords
            }
            val jsonFile: File? = ZipFileHelper.extractJsonFromZip(context)
            jsonFile?.let {
                val jsonString = it.readText()
                val type = object : TypeToken<List<DictionaryEntry>>() {}.type
                val words: List<DictionaryEntry> = Gson().fromJson(jsonString, type)
                val wordsWithUniqueIds = words.mapIndexed { index, entry ->
                    entry.copy(id = index.toLong())
                }
                try {
                    dictionaryDao.insertAll(wordsWithUniqueIds)
                } catch (e: Exception) {
                    Log.e("DictionaryRepository", "Error inserting words into database", e)
                }
                return@withContext words
            } ?: run {
                Log.d("DictionaryRepository", "No JSON file found")
                return@withContext emptyList()
            }
        }
    }
}