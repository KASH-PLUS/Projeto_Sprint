package banco;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Pipefy {

    public static void criarCardCpu(Double usoCpu, String serialNumber, Date date) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHora = dateFormat.format(date);

        serialNumber = serialNumber.toUpperCase();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" phase_id: \\\"317555598\\\" title:\\\"Alerta de uso de CPU - Máquina %s\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"Sua CPU está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"%s\\\"} {field_id: \\\"uso\\\", field_value: \\\"%.2f\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"%s\\\"} ] } ) {clientMutationId card {id title }}}\"}", serialNumber, serialNumber, usoCpu, dataHora));
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

    public static void criarCardDisco(Long usoDisco, String serialNumber, Date date) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHora = dateFormat.format(date);

        serialNumber = serialNumber.toUpperCase();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" phase_id: \\\"317566487\\\" title:\\\"Alerta de uso de Disco - Máquina %s\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"O uso de seu disco está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"%s\\\"} {field_id: \\\"uso\\\", field_value: \\\"%d\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"%s\\\"} ] } ) {clientMutationId card {id title }}}\"}", serialNumber, serialNumber, usoDisco, dataHora));
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

    public static void criarCardRam(Double usoMemoria, String serialNumber, Date date) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String dataHora = dateFormat.format(date);

        serialNumber = serialNumber.toUpperCase();
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, String.format("{\"query\": \"mutation{ createCard( input: { pipe_id: \\\"302821637\\\" phase_id: \\\"317574217\\\" title:\\\"Alerta de uso de RAM - Máquina %s\\\" fields_attributes: [ {field_id: \\\"aviso\\\", field_value: \\\"Sua RAM está fora do ideal\\\"} {field_id: \\\"serial_number\\\", field_value: \\\"%s\\\"} {field_id: \\\"uso\\\", field_value: \\\"%.2f\\\"} {field_id: \\\"data_hora\\\", field_value: \\\"%s\\\"} ] } ) {clientMutationId card {id title }}}\"}", serialNumber, serialNumber, usoMemoria, dataHora));
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
