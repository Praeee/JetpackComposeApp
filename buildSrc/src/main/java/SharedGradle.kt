import org.gradle.api.Project
import java.util.Properties


const val NEWS_API_KEY = "NEWS_API_KEY"

fun loadPropertyFromLocalFile(key: String, rootProject: Project): String? {
    val localProperties = Properties()
    val localPropertiesFilePath = "local.properties"
    val localPropertiesFile = rootProject.file(localPropertiesFilePath)
    if (localPropertiesFile.exists()) {
        localProperties.load(localPropertiesFile.inputStream())
        return localProperties.getProperty(key)
    }
    return null
}

fun getNewsApiKey(rootProject: Project): String {
    val localApiKey = loadPropertyFromLocalFile(NEWS_API_KEY, rootProject)
    return localApiKey ?: System.getenv(NEWS_API_KEY) ?: "defaultKey"
}

