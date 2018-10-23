package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.GroupIndexResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoogleMaven {
    @GET("maven.google.com/{group_path}/group-index.xml")
    Call<GroupIndexResponse> groupIndex(@Path("group_path") String groupPath);
}
