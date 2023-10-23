
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.story.preferences.UserPreference
import kotlinx.coroutines.launch

class LogoutViewModel(private val pref: UserPreference) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            pref.discardToken()
            pref.logout()
        }
    }

}