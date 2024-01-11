import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel

class ToastViewModel(application: Application) : AndroidViewModel(application) {

    fun showToast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }
}
