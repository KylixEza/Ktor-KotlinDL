package com.kylix.deep_learning

import io.jhdf.HdfFile
import org.jetbrains.kotlinx.dl.api.core.Sequential
import org.jetbrains.kotlinx.dl.api.core.loss.Losses
import org.jetbrains.kotlinx.dl.api.core.metric.Metrics
import org.jetbrains.kotlinx.dl.api.core.optimizer.Adam
import org.jetbrains.kotlinx.dl.api.inference.keras.loadWeights
import org.jetbrains.kotlinx.dl.api.summary.printSummary
import java.io.File

object KotlinDLHelper {

    private var modelConfig: File = File("src/main/resources/model.json")
    private var weight: File = File("src/main/resources/weights")
    private var model: Sequential = Sequential.loadModelConfiguration(modelConfig)

    fun init() {
        model.compile(
            optimizer = Adam(learningRate = 1e-4f),
            loss = Losses.SOFT_MAX_CROSS_ENTROPY_WITH_LOGITS,
            metric = Metrics.ACCURACY
        )
        model.loadWeights(HdfFile(weight))
    }

    fun predict(image: FloatArray): String = run {
        val prediction = model.predict(image)
        labels[prediction] ?: "Unknown"
    }
}