package me.destro.intellij.plugins.qmaven.api;

import me.destro.intellij.plugins.qmaven.api.model.SelectResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MavenOrg {

    @GET("select")
    Call<SelectResponse> select(@Query(value = "q", encoded = true) String query, @Query("rows") int rows, @Query("wt") String format);
    
    /*
    *    @staticmethod
    def search(query: str) -> requests.Response:
        url = "http://search.maven.org/solrsearch/select?q={query}&rows=20&wt=json".format(query=query)
        return requests.get(url)

    @staticmethod
    def gid(gid: str) -> requests.Response:
        url = "http://search.maven.org/solrsearch/select?q=g:{gid}&rows=20&wt=json".format(gid=gid)
        return requests.get(url)

    * */
}
