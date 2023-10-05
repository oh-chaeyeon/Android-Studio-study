package com.example.myapplication0909

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MyView : View {
    private var selectedShape: Shape = Shape.RECTANGLE
    private var shapesList: MutableList<ShapeData> = mutableListOf()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    // 선택한 도형을 설정합니다.
    fun setSelectedShape(shape: Shape) {
        selectedShape = shape
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()

        // 이전에 그려진 도형들을 그립니다.
        for (shapeData in shapesList) {
            paint.color = shapeData.color
            when (shapeData.shape) {
                Shape.RECTANGLE -> canvas.drawRect(shapeData.rect, paint)
                Shape.CIRCLE -> {
                    val centerX = shapeData.rect.centerX()
                    val centerY = shapeData.rect.centerY()
                    val radius = (shapeData.rect.right - shapeData.rect.left) / 2
                    canvas.drawCircle(centerX, centerY, radius, paint)
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            // 새로운 도형 데이터 생성
            val shapeData = when (selectedShape) {
                Shape.RECTANGLE -> {
                    val rect = RectF(x, y, x + 100, y + 100)
                    ShapeData(rect, Color.BLUE, Shape.RECTANGLE)
                }
                Shape.CIRCLE -> {
                    val radius = 50.0f
                    val circleRect = RectF(x - radius, y - radius, x + radius, y + radius)
                    ShapeData(circleRect, Color.RED, Shape.CIRCLE)
                }
            }

            // 이전 도형을 삭제하고 새 도형을 추가합니다.
            shapesList.clear()
            shapesList.add(shapeData)

            invalidate() // 화면을 다시 그립니다.
            return true
        }
        return super.onTouchEvent(event)
    }

    enum class Shape {
        RECTANGLE, CIRCLE
    }

    data class ShapeData(
        val rect: RectF,
        val color: Int,
        val shape: Shape
    )
}
