package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.SelectResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MavenOrg {

    @GET("select")
    Call<SelectResponse> select(@Query(value = "q", encoded = true) String query, @Query("rows") int rows, @Query("wt") String format);
}
