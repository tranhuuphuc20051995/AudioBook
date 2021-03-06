package com.allsoftdroid.audiobook.feature_mini_player.utils

import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.allsoftdroid.audiobook.feature_mini_player.R
import com.allsoftdroid.common.base.extension.CreateImageOverlay
import com.allsoftdroid.common.base.network.ArchiveUtils
import com.allsoftdroid.common.base.utils.BindingUtils.getNormalizedText
import com.allsoftdroid.common.base.utils.BindingUtils.getSignatureForImageLoading
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import timber.log.Timber


@BindingAdapter("trackTitle")
fun TextView.setTrackTitle(item: String?){
    item?.let {
        text = getNormalizedText(item,30)
    }
}

@BindingAdapter("controlIconPlayPause")
fun setPlayPauseIcon(view: Button, shouldPlay: Boolean){

    val currentIcon:Int = if(shouldPlay){
        R.drawable.ic_pause_white_24dp
    }else{
        R.drawable.ic_play_arrow_white_24dp
    }

    view.setBackgroundResource(currentIcon)
    Timber.d("Should it play is $shouldPlay")
}

@BindingAdapter("bookImage")
fun setImageUrl(imageView: ImageView, bookId:String?) {

    bookId?.let {
        val url = ArchiveUtils.getThumbnail(bookId)

        Glide
            .with(imageView.context)
            .asBitmap()
            .load(url)
            .override(250,250)
            .apply(
                RequestOptions()
                    .signature(ObjectKey(getSignatureForImageLoading(bookId)))
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.loading_animation)
                    .error(
                        CreateImageOverlay
                            .with(imageView.context)
                            .buildOverlay(front = R.drawable.ic_book_play,back = R.drawable.gradiant_background)
                    )
            )
            .dontAnimate()
            .into(imageView)
    }
}

