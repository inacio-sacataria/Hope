
import androidx.lifecycle.MutableLiveData
import com.decode.hope.data.db.entities.Note

object ClikedNotes {
    var note = MutableLiveData<Note?>()
}