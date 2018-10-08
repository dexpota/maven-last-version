package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.SelectResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MavenOrgHelper {

    MavenOrg service;

    public MavenOrgHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://search.maven.org/solrsearch/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        
        service = retrofit.create(MavenOrg.class);
    }

    public Call<SelectResponse> search(String gid, String artifact) {
        return service.select(String.format("g:%s+AND+a:%s", gid, artifact), 20, "json");
    }
}
