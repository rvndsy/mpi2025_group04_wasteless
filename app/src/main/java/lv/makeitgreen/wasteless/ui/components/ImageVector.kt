package lv.makeitgreen.wasteless.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import lv.makeitgreen.wasteless.R

@Composable
fun ImageVector(@DrawableRes id: Int) {
    ImageVector.vectorResource(R.drawable.outline_barcode_scanner_24)
}