package banco;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Pipefy {

    public static void criarCard() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"query\":\"mutation { createCard(input:{  pipe_id: 302821637  phase_id: 317534997  title: \\\"oi\\\"  fields_attributes: [{field_id: \\\"test\\\" , field_value: \\\"value\\\" }]  }){ card { id title } } }\"}");
        Request request = new Request.Builder()
                .url("https://api.pipefy.com/graphql")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "sha512-Is5c2JND4D8MHhPH4SONqjUjTzzSFYnzGId/rXKx7Em0L+pEgjUP2Lb/9soQsNcmBvJLT9fByh1Y2+pRGsJTjw==?qfQw")
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response);
    }
}
