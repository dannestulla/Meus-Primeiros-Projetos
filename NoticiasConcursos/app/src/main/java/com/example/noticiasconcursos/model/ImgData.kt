package com.example.noticiasconcursos.api

// related to
// https://jobconcursosbr.com/wp-json/wp/v2/media?_fields=media_details.sizes.medium.source_url

class ImgData : ArrayList<ImgDataItem>()

data class ImgDataItem(
    val media_details: MediaDetails
)

data class MediaDetails(
    val `file`: String,
    val height: Int,
    val image_meta: ImageMeta,
    //val sizes: Sizes,
    val width: Int
)

data class ImageMeta(
    val aperture: String,
    val camera: String,
    val caption: String,
    val copyright: String,
    val created_timestamp: String,
    val credit: String,
    val focal_length: String,
    val iso: String,
    val keywords: List<Any>,
    val orientation: String,
    val shutter_speed: String,
    val title: String
)


data class AlmThumbnail(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Full(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Medium(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MediumLarge(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MhMagazineContent(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MhMagazineLarge(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MhMagazineMedium(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MhMagazineSlider(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class MhMagazineSmall(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)

data class Thumbnail(
    val `file`: String,
    val height: Int,
    val mime_type: String,
    val source_url: String,
    val width: Int
)