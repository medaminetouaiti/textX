package devesh.app.ocr.api;

import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AIService {
    @POST("v1/chat/completions")
    Call<AIResponse> getChatCompletion(
        @Header("Authorization") String authorization,
        @Body JsonObject body
    );
}
