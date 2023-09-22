package com.example.stackwidget

import android.content.Intent
import android.widget.RemoteViewsService
import android.widget.RemoteViewsService.RemoteViewsFactory

class StackWidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory = StackRemoteViewsFactory(this.applicationContext)

}