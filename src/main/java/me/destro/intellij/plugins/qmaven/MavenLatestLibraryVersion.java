package me.destro.intellij.plugins.qmaven;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.macro.MacroBase;
import me.destro.intellij.plugins.qmaven.api.MavenOrgHelper;
import me.destro.intellij.plugins.qmaven.api.model.SelectResponse;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class MavenLatestLibraryVersion extends MacroBase {
    public MavenLatestLibraryVersion() {
        super("mavenDotOrg", "Retrieve the latest version of a library on maven.org.");
    }

    @Nullable
    @Override
    protected Result calculateResult(@NotNull Expression[] params, ExpressionContext context, boolean quick) {
        if (params.length < 2) {
            return null;
        }

        Result gid_result = params[0].calculateResult(context);
        Result artifact_result = params[1].calculateResult(context);

        if (gid_result == null || artifact_result == null) {
            return null;
        }

        String gid = gid_result.toString();
        String artifact = artifact_result.toString();

        MavenOrgHelper helper = new MavenOrgHelper();
        Call<SelectResponse> call = helper.search(gid, artifact);
        
        TextResult result;
        try {
            Response<SelectResponse> response = call.execute();
            SelectResponse select = response.body();

            if (select != null && select.response != null && select.response.numFound >= 1) {
                result = new TextResult(select.response.docs.get(0).latestVersion);
            }else {
                result = null;
            }
        } catch (IOException e) {
            result = null;
            e.printStackTrace();
        }

        return result;
    }
}
