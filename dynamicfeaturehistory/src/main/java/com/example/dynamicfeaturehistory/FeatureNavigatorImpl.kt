package com.example.dynamicfeaturehistory

import android.content.Context
import android.content.Intent
import com.example.core.FeatureNavigator

// FeatureNavigatorImpl.kt (в :feature:myfeature)
class FeatureNavigatorImpl(private val context: Context) : FeatureNavigator {
    override fun openFeatureActivity() {
        val intent = Intent(context, HistoryActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}