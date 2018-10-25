package me.destro.intellij.plugins.qmaven.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class GoogleMavenHelper {
    GoogleMaven service;

    public GoogleMavenHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://maven.google.com/")
                .build();

        service = retrofit.create(GoogleMaven.class);
    }

    public Call<ResponseBody> groupIndex(String groupPath) {
        return service.groupIndex(groupPath.replace(".", "/"));
    }
}
