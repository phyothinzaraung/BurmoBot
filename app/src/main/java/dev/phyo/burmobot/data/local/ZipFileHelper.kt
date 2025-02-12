package dev.phyo.burmobot.data.local

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

object ZipFileHelper {

    private const val ZIP_FILE_NAME = "dictionaryjson.zip"
    private const val JSON_FILE_NAME = "dictionary.json"

    fun extractJsonFromZip(context: Context): File?{
        val assetsManager = context.assets
        val outputFile = File(context.filesDir, JSON_FILE_NAME)

        if (outputFile.exists()) return outputFile

        try {
            assetsManager.open(ZIP_FILE_NAME).use { zipInputStream ->
                ZipInputStream(zipInputStream).use { zis ->
                    var entry: ZipEntry?
                    while (zis.nextEntry.also { entry = it } != null){
                        if (entry!!.name == JSON_FILE_NAME){
                            FileOutputStream(outputFile).use { fos ->
                                zis.copyTo(fos)
                            }
                            return outputFile
                        }
                    }
                }
            }
        }catch (e: IOException){
            e.printStackTrace()
        }
        return null
    }
}