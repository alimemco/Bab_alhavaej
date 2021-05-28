package com.alirnp.babalhavaej.viewModel

import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.alirnp.babalhavaej.model.HomeItem
import com.alirnp.babalhavaej.model.ReportCard
import timber.log.Timber
import java.util.*


class HomeViewModel {

    private var needsMeet = 2140
    private var charities = 35632
    private var supporter = 38352

    val mHomeItems: MutableLiveData<List<HomeItem>> = MutableLiveData(getHomeItems())
    val mReportCard: MutableLiveData<ReportCard> = MutableLiveData(getReportCard())

    init {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                needsMeet += 12

                mReportCard.postValue(ReportCard(charities,needsMeet,supporter))

                Timber.i("counter $needsMeet")
            }
        }, 1000,1000)
    }



    private fun getReportCard(): ReportCard {
        return ReportCard(charities,needsMeet,supporter)
    }

    private fun getHomeItems(): List<HomeItem> {
        return listOf(
            HomeItem("کفاره"),
            HomeItem("صدقه"),
            HomeItem("قرض الحسنه"),
            HomeItem("کمک های معیشتی"),
            HomeItem("نذورات"),
            HomeItem("فطریه"),
            HomeItem("گلریزان"),
            HomeItem("کمک های تحصیلی"),
            HomeItem("کاریابی"),
            HomeItem("کمک درمان"),
            HomeItem("جهیزیه"),
            HomeItem("گوسفند نذری ماهیانه"),
            HomeItem("گوسفند عقیقه"),
            HomeItem("دیوار مهربانی")
        )
    }
}