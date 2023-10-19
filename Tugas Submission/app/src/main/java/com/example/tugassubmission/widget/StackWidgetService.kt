
import android.content.Intent
import android.widget.RemoteViewsService
import com.example.tugassubmission.widget.StackRemoteViewsFactory

class StackWidgetService: RemoteViewsService() {

    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory =
        StackRemoteViewsFactory(this.applicationContext)
}