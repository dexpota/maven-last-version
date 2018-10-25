package me.destro.intellij.plugins.qmaven.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GoogleMaven {
    @GET("/{group_path}/group-index.xml")
    Call<ResponseBody> groupIndex(@Path("group_path") String groupPath);
}
