import org.gradle.api.Project
import java.util.Properties


val COIN_API_KEY = "COIN_API_KEY"

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

fun getNewsApiKey(flavour:String, rootProject: Project): String {
    val key = when(flavour.uppercase()){
        "PROD" ->{
            COIN_API_KEY
        }
        else ->{
            COIN_API_KEY
        }

    }
    val localApiKey = loadPropertyFromLocalFile(key, rootProject)
    return localApiKey ?: System.getenv(key) ?: "defaultKey"
}

