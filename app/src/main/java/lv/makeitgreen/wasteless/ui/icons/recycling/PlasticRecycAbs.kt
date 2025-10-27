package lv.makeitgreen.wasteless.ui.icons.recycling

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val PlasticRecycAbs: ImageVector
    get() {
        if (_PlasticRecycAbs != null) {
            return _PlasticRecycAbs!!
        }
        _PlasticRecycAbs = ImageVector.Builder(
            name = "PlasticRecycAbs",
            defaultWidth = 100.dp,
            defaultHeight = 100.dp,
            viewportWidth = 100f,
            viewportHeight = 100f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7.8844748f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(29.89f, 37.58f)
                lineTo(44.73f, 12.93f)
                curveToRelative(0f, 0f, 5.96f, -5.76f, 11.19f, -0.55f)
                lineToRelative(13.82f, 23.37f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7.8844748f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(46.04f, 80.88f)
                lineTo(17.2f, 80.4f)
                curveToRelative(0f, 0f, -7.98f, -2.27f, -6.07f, -9.38f)
                lineTo(24.51f, 47.4f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 7.8844748f,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveToRelative(75.58f, 45.29f)
                lineToRelative(14f, 25.14f)
                curveToRelative(0f, 0f, 2.02f, 8.03f, -5.11f, 9.93f)
                lineToRelative(-27.21f, 0.25f)
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(46.16f, 80.68f)
                lineToRelative(16.55f, -9.72f)
                lineToRelative(0f, 19.13f)
                lineToRelative(-16.55f, -9.42f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(13.67f, 47.44f)
                lineToRelative(16.55f, -9.72f)
                lineToRelative(0f, 19.13f)
                lineToRelative(-16.55f, -9.42f)
                close()
            }
            path(fill = SolidColor(Color.Black)) {
                moveToRelative(58.82f, 35.88f)
                lineToRelative(16.55f, -9.72f)
                lineToRelative(0f, 19.13f)
                lineToRelative(-16.55f, -9.42f)
                close()
            }
        }.build()

        return _PlasticRecycAbs!!
    }

@Suppress("ObjectPropertyName")
private var _PlasticRecycAbs: ImageVector? = null
