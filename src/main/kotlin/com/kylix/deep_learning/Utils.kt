package com.kylix.deep_learning

import org.jetbrains.kotlinx.dl.impl.preprocessing.image.ImageConverter
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun buildProperImageSize(byteArray: ByteArray): BufferedImage {
    val image = ImageIO.read(byteArray.inputStream())
    val bufferedImage = BufferedImage(224, 224, BufferedImage.TYPE_INT_RGB)
    bufferedImage.graphics.drawImage(image, 0, 0, null)

    return bufferedImage
}

fun BufferedImage.toFloatArray() = ImageConverter.toNormalizedFloatArray(this)