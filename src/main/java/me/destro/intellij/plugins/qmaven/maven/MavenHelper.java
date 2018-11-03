package me.destro.intellij.plugins.qmaven.maven;

import me.destro.intellij.plugins.qmaven.maven.model.Metadata;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.Path;

public class MavenHelper {
    MavenRepository repository;

    public MavenHelper(String root) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(root)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        repository = retrofit.create(MavenRepository.class);
    }

    Call<Metadata> mavenMetadata(@Path("group") String group, @Path("artifact") String artifact) {
        return repository.mavenMetadata(group, artifact);
    }

}
