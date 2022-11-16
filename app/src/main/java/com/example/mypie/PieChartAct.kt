package com.example.mypie

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class PieChartAct : AppCompatActivity() {
    private var mPieChart: PieChart? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pie_chart)
        /*折线饼状图*/
        //1.初始化组件
        mPieChart = findViewById<View>(R.id.mPieChart) as PieChart
        mPieChart!!.setUsePercentValues(true) //设置是否使用百分值,默认不显示
        mPieChart!!.description.isEnabled = false
        mPieChart!!.dragDecelerationFrictionCoef = 0.95f

        //是否设置中心文字
        mPieChart!!.setDrawCenterText(true)
        //绘制中间文字
        val sp = SpannableString("时间安排")
        mPieChart!!.centerText = sp
        mPieChart!!.setExtraOffsets(20f, 0f, 20f, 0f)

        //设置是否为实心图，以及空心时 中间的颜色
        mPieChart!!.isDrawHoleEnabled = true
        mPieChart!!.setHoleColor(Color.WHITE)

        //设置圆环信息
        mPieChart!!.setTransparentCircleColor(Color.WHITE) //设置透明环颜色
        mPieChart!!.setTransparentCircleAlpha(110) //设置透明环的透明度
        mPieChart!!.holeRadius = 55f //设置内圆的大小
        mPieChart!!.transparentCircleRadius = 60f //设置透明环的大小
        mPieChart!!.rotationAngle = 0f
        // 触摸旋转
        mPieChart!!.isRotationEnabled = true
        //选中变大
        mPieChart!!.isHighlightPerTapEnabled = true

        //模拟数据
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(80F, "睡眠"))
        entries.add(PieEntry(100F, "吃饭"))
        entries.add(PieEntry(50F, "工作"))
        entries.add(PieEntry(50F, "学习"))
        entries.add(PieEntry(20F, "娱乐"))

        //设置数据
        setData(entries)

        //默认动画
        mPieChart!!.animateY(1400, Easing.EasingOption.EaseInOutQuad)

        //设置图例
        val l = mPieChart!!.legend
        //设置显示的位置，低版本用的是setPosition();
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        //设置是否显示图例
        l.setDrawInside(false)
        l.isEnabled = true

        // 输入图例标签样式
        mPieChart!!.setEntryLabelColor(Color.BLUE)
        mPieChart!!.setEntryLabelTextSize(18f)
    }

    //设置数据
    private fun setData(entries: ArrayList<PieEntry>) {
        val dataSet = PieDataSet(entries, "")
        //设置个饼状图之间的距离
        dataSet.sliceSpace = 0f
        //颜色的集合，按照存放的顺序显示
        val colors = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
        colors.add(ColorTemplate.getHoloBlue())
        dataSet.colors = colors

        //设置折线
        dataSet.valueLinePart1OffsetPercentage = 80f
        //设置线的长度
        dataSet.valueLinePart1Length = 0.3f
        dataSet.valueLinePart2Length = 0.3f
        //设置文字和数据图外显示
        dataSet.xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        dataSet.yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        val data = PieData(dataSet)
        //百分比设置
        data.setValueFormatter(PercentFormatter())
        //文字的颜色
        data.setValueTextSize(14f)
        data.setValueTextColor(Color.BLACK)
        mPieChart!!.data = data
        // 撤销所有的亮点
        mPieChart!!.highlightValues(null)
        mPieChart!!.invalidate()
    }
}
