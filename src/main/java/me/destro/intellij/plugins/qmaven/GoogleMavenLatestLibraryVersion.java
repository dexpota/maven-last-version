package me.destro.intellij.plugins.qmaven;

import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.ExpressionContext;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.TextResult;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.LoggerRt;
import me.destro.intellij.plugins.qmaven.api.GoogleMavenHelper;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import retrofit2.Call;
import retrofit2.Response;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class GoogleMavenLatestLibraryVersion extends MacroBase {
    static LoggerRt Logger = LoggerRt.getInstance(GoogleMavenLatestLibraryVersion.class);

    public GoogleMavenLatestLibraryVersion() {
        super("mavenGoogleCom", "Retrieve the latest version of a library on maven.org.");
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

        GoogleMavenHelper helper = ServiceManager.getService(MavenToolboxService.class).googleMavenHelper;
        Call<ResponseBody> call = helper.groupIndex(gid);

        TextResult result;
        try {
            Response<ResponseBody> response = call.execute();

            if (response != null && response.body() != null) {
                String lastVersion = parseResponse(response.body().string(), artifact);
                result = new TextResult(lastVersion);
            }else {
                result = null;
            }
        } catch (IOException e) {
            Logger.error(e);
            result = null;
        }

        return result;
    }

    private @Nullable String parseResponse(@NotNull String body, @NotNull String artifact) {
        Document doc = buildDocument(body);

        if (doc == null)
            return null;

        Element root = doc.getDocumentElement();
        for (int i = 0; i < root.getChildNodes().getLength(); i++) {
            switch (root.getChildNodes().item(i).getNodeType()) {
                case Node.ELEMENT_NODE:
                    Element element = (Element) root.getChildNodes().item(i);
                    String name = element.getTagName();

                    if (name.equals(artifact)) {
                        String versions = element.getAttribute("versions");
                        return extractLastVersion(versions);
                    }
                    break;
            }
        }

        return null;
    }

    private @Nullable String extractLastVersion(@NotNull String attribute) {
        String[] versions = attribute.split(",");
        return versions[versions.length - 1];
    }

    private @Nullable Document buildDocument(@NotNull String body) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Logger.error(e);
            return null;
        }

        try {
            InputSource is = new InputSource(new StringReader(body));
            return builder.parse(is);
        } catch (SAXException | IOException e) {
            Logger.error(e);
            return null;
        }
    }
}
