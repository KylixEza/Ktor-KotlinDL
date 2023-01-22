package com.kylix.deep_learning

import org.jetbrains.kotlinx.dl.impl.preprocessing.image.ImageConverter
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun ByteArray.buildProperImageSize(
    width: Int = 224,
    height: Int = 224,
    imageType: Int = BufferedImage.TYPE_INT_RGB
) = run {
    val image = ImageIO.read(this.inputStream())
    val bufferedImage = BufferedImage(width, height, imageType)
    bufferedImage.graphics.drawImage(image, 0, 0, null)

    bufferedImage
}

fun BufferedImage.toFloatArray() = ImageConverter.toNormalizedFloatArray(this)