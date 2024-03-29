package com.example.latihanmenggambarcanvas

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.latihanmenggambarcanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mBitMap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val mCanvas = Canvas(mBitMap)
    private val mPaint = Paint()
    private val halfOfWidth = (mBitMap.width / 2).toFloat()
    private val halfOfHeight = (mBitMap.height / 2).toFloat()
    private val left = 150F
    private val top = 250F
    private val right = mBitMap.width - left
    private val bottom = mBitMap.height.toFloat() - 50F
    private val message = "Apakah kamu suka bermain?"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageBitmap(mBitMap)
        showText()
        binding.like.setOnClickListener {
            showFace()
            showMouth(true)
            showEye()
        }
        binding.dislike.setOnClickListener {
            showFace()
            showMouth(false)
            showEye()
        }
    }

    private fun showText(){
        val mPaintText =  Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50F
            color = ResourcesCompat.getColor(resources, R.color.black , null)
        }

        val mBounds = Rect()
        mPaintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halfOfWidth - mBounds.centerX()
        val y = 50F
        mCanvas.drawText(message, x, y, mPaintText)
    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        mCanvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        mCanvas.drawArc(face, 270F, 180F, false, mPaint)
    }

    private fun showEye() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.drawCircle(halfOfWidth - 100F, halfOfHeight - 10F, 50F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 100F, halfOfHeight - 10F, 50F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        mCanvas.drawCircle(halfOfWidth - 120F, halfOfHeight - 20F, 15F, mPaint)
        mCanvas.drawCircle(halfOfWidth + 80F, halfOfHeight - 20F, 15F, mPaint)
    }

    private fun showMouth(isHappy: Boolean) {
        when (isHappy) {
            true -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(
                    halfOfWidth - 200,
                    halfOfHeight - 100F,
                    halfOfWidth + 200F,
                    halfOfHeight + 400F
                )
                mCanvas.drawArc(lip,
                    25F,
                    130F,
                    false,
                    mPaint)
                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(
                    halfOfWidth - 180F,
                    halfOfHeight,
                    halfOfWidth + 180F,
                    halfOfHeight + 380F)
                mCanvas.drawArc(mouth, 25F, 130F, false, mPaint)

            }
            false -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halfOfWidth - 200F, halfOfHeight + 250F, halfOfWidth + 200F, halfOfHeight + 350F)
                mCanvas.drawArc(lip, 0F, -180F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halfOfWidth - 180F, halfOfHeight + 260F, halfOfWidth + 180F, halfOfHeight + 330F)
                mCanvas.drawArc(mouth, 0F, -180F, false, mPaint)
            }
        }
    }
}