package me.destro.intellij.plugins.qmaven.maven;

import me.destro.intellij.plugins.qmaven.maven.model.Metadata;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface MavenRepository {
    @GET("/{group}/{artifact}/maven-metadata.xml")
    Call<Metadata> mavenMetadata(@Path("group") String group, @Path("artifact") String artifact);
}
