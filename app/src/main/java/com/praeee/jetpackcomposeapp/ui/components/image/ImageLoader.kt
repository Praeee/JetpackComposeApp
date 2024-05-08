package com.praeee.jetpackcomposeapp.ui.components.image

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.praeee.jetpackcomposeapp.R

//@Composable
//fun LoadImageWithGlide(url: String, contentDescription: String?, modifier: Modifier = Modifier) {
//    val requestOptions = remember {
//        RequestOptions()
//            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
//            .centerCrop()
//        // Add any other options you need here
//    }
//
//    val context = LocalContext.current
//
//    Glide.with(context)
//        .load(url)
//        .apply(requestOptions)
//        .transition(DrawableTransitionOptions.withCrossFade())
//        .error(R.drawable.ic_launcher_foreground)
//        .placeholder(R.drawable.ic_launcher_foreground)
//        .into(
//            object : CustomTarget<Drawable>() {
//                override fun onResourceReady(
//                    resource: Drawable,
//                    transition: Transition<in Drawable>?
//                ) {
//                    val painter = remember(resource) {
//                        BitmapPainter(bitmap = (resource as BitmapDrawable).bitmap)
//                    }
//                    Image(
//                        painter = painter,
//                        contentDescription = contentDescription,
//                        contentScale = ContentScale.Crop,
//                        modifier = modifier
//                    )
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {}
//            }
//        )
//}