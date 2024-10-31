import android.os.Build
import android.os.Bundle
import androidx.navigation.NavType
import com.example.apianime.presentation.model.TitleItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val MyNavType = object : NavType<TitleItem>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): TitleItem {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, TitleItem::class.java) as TitleItem
        } else{
            bundle.getParcelable<TitleItem>(key) as TitleItem
        }
    }

    override fun parseValue(value: String): TitleItem {
        return Json.decodeFromString<TitleItem>(value)
    }

    override fun put(bundle: Bundle, key: String, value: TitleItem) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: TitleItem): String = Json.encodeToString<TitleItem>(value)

    override val name: String = TitleItem::class.java.name
}