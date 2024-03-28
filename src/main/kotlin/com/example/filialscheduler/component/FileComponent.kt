package com.example.filialscheduler.component

import com.example.filialscheduler.extension.notExists
import com.example.filialscheduler.property.LocationProperty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import java.io.File

@Component
@EnableConfigurationProperties(LocationProperty::class)
class FileComponent(
    private val locationProperty: LocationProperty,
) {
    suspend fun readLastBlogId() = withContext(Dispatchers.IO) {
        val file = File(locationProperty.blogPath)
        if (file.notExists()) {
            file.createNewFile()
        }
        file.readText()
    }

    suspend fun writeLastBlogId(id: String) = withContext(Dispatchers.IO) {
        val file = File(locationProperty.blogPath)
        file.writeText(id)
    }
}