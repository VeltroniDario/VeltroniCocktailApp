import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import com.google.gson.annotations.SerializedName
import retrofit2.http.Query
import java.lang.Exception

data class DrinkResponse(
    @SerializedName("drinks") val drinks: List<Drink>?
)

data class Drink(
    @SerializedName("idDrink") val id: String,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strDrinkAlternate") val nameAlternate: String?,
    @SerializedName("strTags") val tag: String?,
    @SerializedName("strVideo") val videoUrl: String?,
    @SerializedName("strCategory") val category: String?,
    @SerializedName("strIBA") val IBA: String?,
    @SerializedName("strAlcoholic") val alcoholic: String?,
    @SerializedName("strGlass") val glass: String?,
    @SerializedName("strInstructions") val instructions: String?,
    @SerializedName("strInstructionsIT") val instructionsIT: String?,
    @SerializedName("strDrinkThumb") val imageUrl: String?,
    @SerializedName("strIngredient1") val ingredient1: String?,
    @SerializedName("strIngredient2") val ingredient2: String?,
    @SerializedName("strIngredient3") val ingredient3: String?,
    @SerializedName("strIngredient4") val ingredient4: String?,
    @SerializedName("strIngredient5") val ingredient5: String?,
    @SerializedName("strIngredient6") val ingredient6: String?,
    @SerializedName("strIngredient7") val ingredient7: String?,
    @SerializedName("strIngredient8") val ingredient8: String?,
    @SerializedName("strIngredient9") val ingredient9: String?,
    @SerializedName("strIngredient10") val ingredient10: String?,
    @SerializedName("strIngredient11") val ingredient11: String?,
    @SerializedName("strIngredient12") val ingredient12: String?,
    @SerializedName("strIngredient13") val ingredient13: String?,
    @SerializedName("strIngredient14") val ingredient14: String?,
    @SerializedName("strIngredient15") val ingredient15: String?,
    @SerializedName("strMeasure1") val measure1: String?,
    @SerializedName("strMeasure2") val measure2: String?,
    @SerializedName("strMeasure3") val measure3: String?,
    @SerializedName("strMeasure4") val measure4: String?,
    @SerializedName("strMeasure5") val measure5: String?,
    @SerializedName("strMeasure6") val measure6: String?,
    @SerializedName("strMeasure7") val measure7: String?,
    @SerializedName("strMeasure8") val measure8: String?,
    @SerializedName("strMeasure9") val measure9: String?,
    @SerializedName("strMeasure10") val measure10: String?,
    @SerializedName("strMeasure11") val measure11: String?,
    @SerializedName("strMeasure12") val measure12: String?,
    @SerializedName("strMeasure13") val measure13: String?,
    @SerializedName("strMeasure14") val measure14: String?,
    @SerializedName("strMeasure15") val measure15: String?,
    @SerializedName("strImageSource") val ImageSourse: String?,
    @SerializedName("strImageAttribution") val ImageAttribution: String?,
    @SerializedName("strCreativeCommonsConfirmed") val CommonsConfirmed: String?
)

// Servizio per   Retrofit
interface CocktailApiService {
    @GET
    suspend fun getCocktails(@Url url: String): DrinkResponse
}

object CocktailApiClient {
    private const val BASE_URL = "https://www.thecocktaildb.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: CocktailApiService = retrofit.create(CocktailApiService::class.java)
}

// Funzione principale
suspend fun fetchCocktails(searchType: String, query: String): List<Drink>? {
    val baseUrl = when (searchType) {
        "s" -> "https://www.thecocktaildb.com/api/json/v1/1/search.php?s="
        "f" -> "https://www.thecocktaildb.com/api/json/v1/1/search.php?f="
        "i" -> "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i="

        else -> throw IllegalArgumentException("Tipo di ricerca non valido: $searchType")
    }

    val fullUrl = "$baseUrl$query"
    Log.d("fetchCocktails", fullUrl)
    return try {
        val response = CocktailApiClient.service.getCocktails(fullUrl)
        response.drinks
    } catch (e: Exception) {
        null
    }
}