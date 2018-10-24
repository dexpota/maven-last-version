package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.GroupIndexResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class GoogleMavenHelper {
    GoogleMaven service;

    public GoogleMavenHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://maven.google.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        service = retrofit.create(GoogleMaven.class);
    }

    public Call<ResponseBody> groupIndex(String groupPath) {
        return service.groupIndex(groupPath.replace(".", "/"));
    }
}
