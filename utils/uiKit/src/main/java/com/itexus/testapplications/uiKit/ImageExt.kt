package com.itexus.testapplications.uiKit

fun String.toImproveSmallPictureQuality(): String {
    return this.replace("100x100bb.jpg", "400x400bb.jpg")
}

fun String.toImproveBigPictureQuality(): String {
    return this.replace("100x100bb.jpg", "800x800bb.jpg")
}