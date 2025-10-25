package lv.makeitgreen.wasteless.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

// Icons here are created by converting drawable XML resource files into ImageVector Jetpack Compose
// code using the Valkyrie plugin
object MyIcons {
    val OutlineBarcodeScanner24: ImageVector
        get() {
            if (_OutlineBarcodeScanner24 != null) {
                return _OutlineBarcodeScanner24!!
            }
            _OutlineBarcodeScanner24 = ImageVector.Builder(
                name = "OutlineBarcodeScanner24",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(fill = SolidColor(Color.Black)) {
                    moveTo(40f, 840f)
                    lineTo(40f, 640f)
                    lineTo(120f, 640f)
                    lineTo(120f, 760f)
                    lineTo(240f, 760f)
                    lineTo(240f, 840f)
                    lineTo(40f, 840f)
                    close()
                    moveTo(720f, 840f)
                    lineTo(720f, 760f)
                    lineTo(840f, 760f)
                    lineTo(840f, 640f)
                    lineTo(920f, 640f)
                    lineTo(920f, 840f)
                    lineTo(720f, 840f)
                    close()
                    moveTo(160f, 720f)
                    lineTo(160f, 240f)
                    lineTo(240f, 240f)
                    lineTo(240f, 720f)
                    lineTo(160f, 720f)
                    close()
                    moveTo(280f, 720f)
                    lineTo(280f, 240f)
                    lineTo(320f, 240f)
                    lineTo(320f, 720f)
                    lineTo(280f, 720f)
                    close()
                    moveTo(400f, 720f)
                    lineTo(400f, 240f)
                    lineTo(480f, 240f)
                    lineTo(480f, 720f)
                    lineTo(400f, 720f)
                    close()
                    moveTo(520f, 720f)
                    lineTo(520f, 240f)
                    lineTo(640f, 240f)
                    lineTo(640f, 720f)
                    lineTo(520f, 720f)
                    close()
                    moveTo(680f, 720f)
                    lineTo(680f, 240f)
                    lineTo(720f, 240f)
                    lineTo(720f, 720f)
                    lineTo(680f, 720f)
                    close()
                    moveTo(760f, 720f)
                    lineTo(760f, 240f)
                    lineTo(800f, 240f)
                    lineTo(800f, 720f)
                    lineTo(760f, 720f)
                    close()
                    moveTo(40f, 320f)
                    lineTo(40f, 120f)
                    lineTo(240f, 120f)
                    lineTo(240f, 200f)
                    lineTo(120f, 200f)
                    lineTo(120f, 320f)
                    lineTo(40f, 320f)
                    close()
                    moveTo(840f, 320f)
                    lineTo(840f, 200f)
                    lineTo(720f, 200f)
                    lineTo(720f, 120f)
                    lineTo(920f, 120f)
                    lineTo(920f, 320f)
                    lineTo(840f, 320f)
                    close()
                }
            }.build()

            return _OutlineBarcodeScanner24!!
        }
    // don't think this is mandatory, i still wouldn't touch it
    @Suppress("ObjectPropertyName")
    private var _OutlineBarcodeScanner24: ImageVector? = null
}
