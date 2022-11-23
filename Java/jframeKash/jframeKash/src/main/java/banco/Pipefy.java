package banco;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Pipefy {

    public static void criarCardCpu() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"Sua CPU está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"AQUI VIRÁ O SERIAL NUMBER\\\"} {field_id: \\\"uso\\\", field_value: \\\"AQUI VIRÁ O USO DA CPU\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"08/11/2022 00:00\\\"} ] } ) {clientMutationId card {id title }}}\"}");
        Request request = new Request.Builder()
                .url("https://api.pipefy.com/graphql")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQyNTF9fQ.BDmwgdA6MjMvUaD3x6l34bBKpv9-8epdwzgHOwIOV_zljVHNf1lefNgHg0--ZA_vLjSVT2cFwpwOmiIcPwqfQw")
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response);
    }
    
     public static void criarCardDisco() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"O uso de seu disco está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"AQUI VIRÁ O SERIAL NUMBER\\\"} {field_id: \\\"uso\\\", field_value: \\\"AQUI VIRÁ O USO DO DISCO\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"08/11/2022 00:00\\\"} ] } ) {clientMutationId card {id title }}}\"}");
        Request request = new Request.Builder()
                .url("https://api.pipefy.com/graphql")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQyNTF9fQ.BDmwgdA6MjMvUaD3x6l34bBKpv9-8epdwzgHOwIOV_zljVHNf1lefNgHg0--ZA_vLjSVT2cFwpwOmiIcPwqfQw")
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response);
    }
     
      public static void criarCardRam() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"Sua RAM está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"AQUI VIRÁ O SERIAL NUMBER\\\"} {field_id: \\\"uso\\\", field_value: \\\"AQUI VIRÁ O USO DA RAM\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"08/11/2022 00:00\\\"} ] } ) {clientMutationId card {id title }}}\"}");
        Request request = new Request.Builder()
                .url("https://api.pipefy.com/graphql")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyIjp7ImlkIjozMDIxNzYyNTMsImVtYWlsIjoiMjIyLTFjY28tZ3J1cG8xMEBiYW5kdGVjLmNvbS5iciIsImFwcGxpY2F0aW9uIjozMDAyMTQyNTF9fQ.BDmwgdA6MjMvUaD3x6l34bBKpv9-8epdwzgHOwIOV_zljVHNf1lefNgHg0--ZA_vLjSVT2cFwpwOmiIcPwqfQw")
                .addHeader("Content-Type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response);
    }
}
