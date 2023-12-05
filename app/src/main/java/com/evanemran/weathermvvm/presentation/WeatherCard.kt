package com.evanemran.weathermvvm.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.LineType
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import com.evanemran.weathermvvm.R
import com.evanemran.weathermvvm.presentation.ui.theme.DarkBlue
import com.evanemran.weathermvvm.presentation.ui.theme.DeepBlue
import com.evanemran.weathermvvm.presentation.ui.theme.SkyBlue
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    val steps = 4
    val pointsData: List<Point> =
        listOf(Point(0f, 40f), Point(1f, 90f), Point(2f, 0f), Point(3f, 60f), Point(4f, 10f))

    val xAxisData = AxisData.Builder()
        .axisStepSize(100.dp)
//        .backgroundColor(Color.Blue)
//        .steps(pointsData.size - 1)
//        .labelData { i -> i.toString() }
//        .labelAndAxisLinePadding(15.dp)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(steps)
        /*.backgroundColor(Color.Red)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val yScale = 100 / steps
            (i * yScale).toString()
        }*/.build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        lineType = LineType.SmoothCurve(),
                        width = 2f,
                        color = Color.DarkGray,
                        blendMode = BlendMode.Clear
                    ),
                    IntersectionPoint(
                        radius = 3.dp
                    ),
                    SelectionHighlightPoint(),
                    ShadowUnderLine(),
                    SelectionHighlightPopUp()
                )
            ),
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(
            enableHorizontalLines = false,
            enableVerticalLines = false
        ),
        backgroundColor = SkyBlue
    )

    state.weatherInfo?.currentWeatherData?.let {data->
        Card(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.Transparent),
            shape = RoundedCornerShape(10.dp),

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(SkyBlue)
                    .paint(
                        painter = painterResource(R.drawable.clouds),
                        contentScale = ContentScale.Fit
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Rounded.LocationOn, contentDescription = null, tint = DarkBlue, modifier = Modifier.size(25.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Gotham", color = DarkBlue, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        text = "Today ${data.time.format(DateTimeFormatter.ofPattern("HH:mm"))}",
                        color = DarkBlue
                    )
                }
                //Spacer(modifier = Modifier.height(16.dp))
                //Image(painter = painterResource(id = data.weatherType.iconRes), contentDescription = null, modifier = Modifier.width(200.dp))
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius.roundToInt()}°C",
                    fontSize = 60.sp,
                    color = DarkBlue
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    fontSize = 20.sp,
                    color = DarkBlue
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(value = data.pressure.roundToInt(), unit = " hpa", icon = ImageVector.vectorResource(id = R.drawable.ic_pressure), iconTint = DarkBlue, textStyle = TextStyle(color = DarkBlue))
                    WeatherDataDisplay(value = data.humidity.roundToInt(), unit = " %", icon = ImageVector.vectorResource(id = R.drawable.ic_drop), iconTint = DarkBlue, textStyle = TextStyle(color = DarkBlue))
                    WeatherDataDisplay(value = data.windSpeed.roundToInt(), unit = " km/h", icon = ImageVector.vectorResource(id = R.drawable.ic_wind), iconTint = DarkBlue, textStyle = TextStyle(color = DarkBlue))
                }
                Spacer(modifier = Modifier.height(16.dp))
//                LineChart(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.Transparent)
//                        .height(100.dp),
//                    lineChartData = lineChartData
//                )
            }

        }
    }

}