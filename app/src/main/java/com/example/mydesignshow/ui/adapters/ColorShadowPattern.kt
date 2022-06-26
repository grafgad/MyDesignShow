package com.example.mydesignshow.ui.adapters

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.SweepGradient
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.view.View
import android.view.animation.Animation
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import com.example.mydesignshow.R

class ColorShadowPattern {

    private fun createShadowDrawable(
        @ColorInt colors: IntArray,
        cornerRadius: Float,
        elevation: Float,
        centerX: Float,
        centerY: Float,
        shadowColor: Int
    ): ShapeDrawable {
        val shadowDrawable = ShapeDrawable()
        val outerRadius = FloatArray(8) { cornerRadius }

        shadowDrawable.paint.setShadowLayer(
            elevation, // размер тени
            0f, // смещение тени по оси Х
            0f, // по У
            shadowColor // цвет тени
        )

        /**
         * Применяем покраску градиентом
         *
         * @param centerX - Центр SweepGradient по оси Х. Берем центр вьюхи
         * @param centerY - Центр по оси У
         * @param colors - Цвета градиента. Последний цвет должен быть равен первому,
         * иначе между ними не будет плавного перехода
         * @param position - позиции смещения градиента одного цвета относительно другого от 0 до 1.
         * В нашем случае null т.к. нам нужен равномерный градиент
         */

        shadowDrawable.paint.shader = SweepGradient(
            centerX,
            centerY,
            colors,
            null
        )

        shadowDrawable.shape = RoundRectShape(outerRadius, null, null)

        return shadowDrawable
    }

    /**
     * Создание цветного drawable с закругленными углами
     * Это будет основной цвет нашего контейнера
     */
    private fun createColorDrawable(
        @ColorInt backgroundColor: Int,
        cornerRadius: Float
    ) = GradientDrawable().apply {
        setColor(backgroundColor)
        setCornerRadius(cornerRadius)
    }

    /**
     * Устанавливаем бэкграунд с тенью на вьюху, учитывая padding
     */
    private fun View.setColorShadowBackground(
        shadowDrawable: ShapeDrawable,
        colorDrawable: Drawable,
        padding: Int
    ) {
        val drawable = LayerDrawable(arrayOf(shadowDrawable, colorDrawable))
        drawable.setLayerInset(0, padding, padding, padding, padding)
        drawable.setLayerInset(1, padding, padding, padding, padding)
        setPadding(padding, padding, padding, padding)
        background = drawable
    }

    /**
     * Анимация drawable-градиента
     */
    private fun animateShadow(
        shapeDrawable: ShapeDrawable,
        @ColorInt startColors: IntArray,
        @ColorInt endColors: IntArray,
        duration: Long,
        centerX: Float,
        centerY: Float
    ) {
        /**
         * Меняем значение с 0f до 1f для применения плавного изменения
         * цвета с помощью [ColorUtils.blendARGB]
         */
        ValueAnimator.ofFloat(0f, 1f).apply {
            // Задержка перерисовки тени. Грубо говоря, фпс анимации
            val invalidateDelay = 100
            var deltaTime = System.currentTimeMillis()

            // Новый массив со смешанными цветами
            val mixedColors = IntArray(startColors.size)

            addUpdateListener { animation ->
                if (System.currentTimeMillis() - deltaTime > invalidateDelay) {
                    val animatedFraction = animation.animatedValue as Float
                    deltaTime = System.currentTimeMillis()

                    // Смешиваем цвета
                    for (i in 0..mixedColors.lastIndex) {
                        mixedColors[i] = ColorUtils.blendARGB(
                            startColors[i],
                            endColors[i],
                            animatedFraction
                        )
                    }

                    // Устанавливаем новую тень
                    shapeDrawable.paint.shader = SweepGradient(
                        centerX,
                        centerY,
                        mixedColors,
                        null
                    )
                    shapeDrawable.invalidateSelf()
                }
            }
            repeatMode = ValueAnimator.REVERSE
            repeatCount = Animation.INFINITE
            setDuration(duration)
            start()
        }
    }


    fun setAnimatedShadow(
        view: View,
        startColors: IntArray,
        endColors: IntArray,
        cornerRadius: Float,
        padding: Int,
        backgroundColor: Int,
        shadowColor: Int,
        duration: Long
    ) {
        val centerX = view.width.toFloat() / 2 - padding
        val centerY = view.height.toFloat() / 2 - padding

        val shadowDrawable = createShadowDrawable(
            colors = startColors,
            cornerRadius = cornerRadius,
            elevation = padding.toFloat(),
            centerX = centerX,
            centerY = centerY,
            shadowColor = shadowColor
        )

        val colorDrawable = createColorDrawable(
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius
        )

        view.setColorShadowBackground(
            shadowDrawable = shadowDrawable,
            colorDrawable = colorDrawable,
            padding = padding
        )

        animateShadow(
            shapeDrawable = shadowDrawable,
            startColors = startColors,
            endColors = endColors,
            duration = duration,
            centerX = centerX,
            centerY = centerY
        )
    }
}

fun View.setAnimatedShadow(
    startColors: IntArray = intArrayOf(
        Color.WHITE,
        Color.GREEN,
        Color.WHITE
    ),
    endColors: IntArray= intArrayOf(
        Color.GREEN,
        Color.WHITE,
        Color.GREEN
    ),
    cornerRadius: Float = 20f.dp,
    padding: Int = 30.dp,
    backgroundColor: Int = R.drawable.background_gradient,
    shadowColor: Int = Color.BLACK,
    duration: Long = 2000
) {
    ColorShadowPattern().setAnimatedShadow(
        this,
        startColors,
        endColors,
        cornerRadius,
        padding,
        backgroundColor,
        shadowColor,
        duration
    )
}