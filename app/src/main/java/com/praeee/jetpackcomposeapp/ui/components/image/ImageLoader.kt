package com.praeee.jetpackcomposeapp.ui.components.image

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.request.CachePolicy
import okhttp3.OkHttpClient
import java.io.File


fun createImageLoader(context: Context): ImageLoader {
    return ImageLoader.Builder(context)
        .components {
            add(SvgDecoder.Factory())
        }
        .okHttpClient {
            OkHttpClient.Builder().build()
        }
        .diskCache {
            DiskCache.Builder()
                .directory(File(context.cacheDir, "image_cache"))
                .maxSizePercent(0.02) // Use up to 2% of the app's available storage
                .build()
        }
        .diskCachePolicy(CachePolicy.ENABLED) // Enable disk cache for SVG
        .memoryCachePolicy(CachePolicy.ENABLED) // Enable memory cache for SVG
        .build()
}
