package com.allsoftdroid.common.base.store.downloader

sealed class DownloadEvent{
    abstract val bookId:String
    abstract val chapterIndex:Int
}

data class Download(
    val url:String,
    val name:String,
    val description:String,
    val subPath:String,
    override val bookId:String,
    val chapter:String,
    override val chapterIndex:Int):DownloadEvent()

data class Downloading(
    val url:String,
    override val bookId: String,
    override val chapterIndex: Int) : DownloadEvent()

data class Progress(
    val url:String,
    override val bookId: String,
    override val chapterIndex: Int,
    val percent: Long) : DownloadEvent()

data class Downloaded(
    val url:String,
    override val bookId: String,
    override val chapterIndex: Int) : DownloadEvent()

data class Failed(
    override val bookId: String,
    override val chapterIndex: Int,
    val message:String) : DownloadEvent()

data class Cancel(
    override val bookId: String,
    override val chapterIndex: Int,
    val fileUrl:String) : DownloadEvent()

data class DownloadNothing(override val bookId: String="",override val chapterIndex: Int=-1) : DownloadEvent()

data class OpenDownloadActivity(override val bookId: String="",override val chapterIndex: Int=-1) : DownloadEvent()