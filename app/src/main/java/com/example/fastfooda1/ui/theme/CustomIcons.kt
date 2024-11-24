package com.example.fastfooda1.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Storefront: ImageVector
    get() {
        if (_Storefront != null) {
            return _Storefront!!
        }
        _Storefront = ImageVector.Builder(
            name = "Storefront",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(841f, 442f)
                verticalLineToRelative(318f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(761f, 840f)
                horizontalLineTo(201f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(121f, 760f)
                verticalLineToRelative(-318f)
                quadToRelative(-23f, -21f, -35.5f, -54f)
                reflectiveQuadToRelative(-0.5f, -72f)
                lineToRelative(42f, -136f)
                quadToRelative(8f, -26f, 28.5f, -43f)
                reflectiveQuadToRelative(47.5f, -17f)
                horizontalLineToRelative(556f)
                quadToRelative(27f, 0f, 47f, 16.5f)
                reflectiveQuadToRelative(29f, 43.5f)
                lineToRelative(42f, 136f)
                quadToRelative(12f, 39f, -0.5f, 71f)
                reflectiveQuadTo(841f, 442f)
                moveToRelative(-272f, -42f)
                quadToRelative(27f, 0f, 41f, -18.5f)
                reflectiveQuadToRelative(11f, -41.5f)
                lineToRelative(-22f, -140f)
                horizontalLineToRelative(-78f)
                verticalLineToRelative(148f)
                quadToRelative(0f, 21f, 14f, 36.5f)
                reflectiveQuadToRelative(34f, 15.5f)
                moveToRelative(-180f, 0f)
                quadToRelative(23f, 0f, 37.5f, -15.5f)
                reflectiveQuadTo(441f, 348f)
                verticalLineToRelative(-148f)
                horizontalLineToRelative(-78f)
                lineToRelative(-22f, 140f)
                quadToRelative(-4f, 24f, 10.5f, 42f)
                reflectiveQuadToRelative(37.5f, 18f)
                moveToRelative(-178f, 0f)
                quadToRelative(18f, 0f, 31.5f, -13f)
                reflectiveQuadToRelative(16.5f, -33f)
                lineToRelative(22f, -154f)
                horizontalLineToRelative(-78f)
                lineToRelative(-40f, 134f)
                quadToRelative(-6f, 20f, 6.5f, 43f)
                reflectiveQuadToRelative(41.5f, 23f)
                moveToRelative(540f, 0f)
                quadToRelative(29f, 0f, 42f, -23f)
                reflectiveQuadToRelative(6f, -43f)
                lineToRelative(-42f, -134f)
                horizontalLineToRelative(-76f)
                lineToRelative(22f, 154f)
                quadToRelative(3f, 20f, 16.5f, 33f)
                reflectiveQuadToRelative(31.5f, 13f)
                moveTo(201f, 760f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-282f)
                quadToRelative(-5f, 2f, -6.5f, 2f)
                horizontalLineTo(751f)
                quadToRelative(-27f, 0f, -47.5f, -9f)
                reflectiveQuadTo(663f, 442f)
                quadToRelative(-18f, 18f, -41f, 28f)
                reflectiveQuadToRelative(-49f, 10f)
                quadToRelative(-27f, 0f, -50.5f, -10f)
                reflectiveQuadTo(481f, 442f)
                quadToRelative(-17f, 18f, -39.5f, 28f)
                reflectiveQuadTo(393f, 480f)
                quadToRelative(-29f, 0f, -52.5f, -10f)
                reflectiveQuadTo(299f, 442f)
                quadToRelative(-21f, 21f, -41.5f, 29.5f)
                reflectiveQuadTo(211f, 480f)
                horizontalLineToRelative(-4.5f)
                quadToRelative(-2.5f, 0f, -5.5f, -2f)
                close()
                moveToRelative(560f, 0f)
                horizontalLineTo(201f)
                close()
            }
        }.build()
        return _Storefront!!
    }

private var _Storefront: ImageVector? = null
