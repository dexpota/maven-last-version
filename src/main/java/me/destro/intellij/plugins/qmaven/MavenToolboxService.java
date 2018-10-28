package me.destro.intellij.plugins.qmaven;

import com.intellij.openapi.components.ServiceManager;
import me.destro.intellij.plugins.qmaven.api.GoogleMavenHelper;
import me.destro.intellij.plugins.qmaven.api.MavenOrgHelper;

public class MavenToolboxService {
    public final MavenOrgHelper mavenOrgHelper;
    public final GoogleMavenHelper googleMavenHelper;

    MavenToolboxService() {
        mavenOrgHelper = new MavenOrgHelper();
        googleMavenHelper = new GoogleMavenHelper();
    }

    public static MavenToolboxService getInstance() {
        return ServiceManager.getService(MavenToolboxService.class);
    }
}
