package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.GroupIndexResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class GoogleMavenHelper {
    GoogleMaven service;

    public GoogleMavenHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://search.maven.org/solrsearch/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        service = retrofit.create(GoogleMaven.class);
    }

    public Call<GroupIndexResponse> groupIndex(String groupPath) {
        return service.groupIndex(groupPath.replace(".", "/"));
    }
}
