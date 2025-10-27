package lv.makeitgreen.wasteless.ui.icons.recycling

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val RecyclingCode61: ImageVector
    get() {
        if (_RecyclingCode61 != null) {
            return _RecyclingCode61!!
        }
        _RecyclingCode61 = ImageVector.Builder(
            name = "RecyclingCode61",
            defaultWidth = 100.dp,
            defaultHeight = 100.dp,
            viewportWidth = 100f,
            viewportHeight = 100f
        ).apply {
            path(
                fillAlpha = 0.75f,
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(31.63f, 31.5f)
                lineTo(44.78f, 9.57f)
                curveTo(44.78f, 9.57f, 50.07f, 4.45f, 54.7f, 9.08f)
                lineTo(66.95f, 29.86f)
            }
            path(
                fillAlpha = 0.75f,
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(45.95f, 70f)
                lineTo(20.38f, 69.57f)
                curveTo(20.38f, 69.57f, 13.31f, 67.55f, 15f, 61.23f)
                lineTo(26.87f, 40.23f)
            }
            path(
                fillAlpha = 0.75f,
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(72.13f, 38.35f)
                lineTo(84.54f, 60.7f)
                curveTo(84.54f, 60.7f, 86.33f, 67.84f, 80.01f, 69.54f)
                lineTo(55.89f, 69.76f)
            }
            path(fill = SolidColor(Color.Black)) {
                moveTo(46.05f, 69.82f)
                lineTo(60.72f, 61.18f)
                lineTo(60.72f, 78.2f)
                lineTo(46.05f, 69.82f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveTo(17.25f, 40.27f)
                lineTo(31.92f, 31.63f)
                lineTo(31.92f, 48.64f)
                lineTo(17.25f, 40.27f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveTo(57.28f, 29.99f)
                lineTo(71.95f, 21.35f)
                lineTo(71.95f, 38.36f)
                lineTo(57.28f, 29.99f)
                close()
            }
        }.build()

        return _RecyclingCode61!!
    }

@Suppress("ObjectPropertyName")
private var _RecyclingCode61: ImageVector? = null
