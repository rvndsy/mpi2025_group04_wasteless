package lv.makeitgreen.wasteless.ui.icons.recycling

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val RecyclingCode79: ImageVector
    get() {
        if (_RecyclingCode79 != null) {
            return _RecyclingCode79!!
        }
        _RecyclingCode79 = ImageVector.Builder(
            name = "RecyclingCode79",
            defaultWidth = 100.dp,
            defaultHeight = 100.dp,
            viewportWidth = 100f,
            viewportHeight = 100f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(31.63f, 31.5f)
                lineTo(44.78f, 9.57f)
                curveToRelative(0f, 0f, 5.28f, -5.12f, 9.92f, -0.49f)
                lineTo(66.95f, 29.86f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(45.95f, 70f)
                lineTo(20.38f, 69.57f)
                curveToRelative(0f, 0f, -7.08f, -2.02f, -5.38f, -8.34f)
                lineToRelative(11.87f, -21f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveToRelative(72.13f, 38.35f)
                lineToRelative(12.41f, 22.35f)
                curveToRelative(0f, 0f, 1.79f, 7.14f, -4.53f, 8.83f)
                lineToRelative(-24.12f, 0.22f)
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(46.05f, 69.82f)
                lineToRelative(14.67f, -8.64f)
                lineToRelative(0f, 17.01f)
                lineToRelative(-14.67f, -8.37f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(17.25f, 40.27f)
                lineToRelative(14.67f, -8.64f)
                lineToRelative(0f, 17.01f)
                lineToRelative(-14.67f, -8.37f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(57.28f, 29.99f)
                lineToRelative(14.67f, -8.64f)
                lineToRelative(0f, 17.01f)
                lineToRelative(-14.67f, -8.37f)
                close()
            }
        }.build()

        return _RecyclingCode79!!
    }

@Suppress("ObjectPropertyName")
private var _RecyclingCode79: ImageVector? = null
