package me.destro.intellij.plugins.qmaven.maven.model;

import org.simpleframework.xml.Element;

public class Snapshot {
    @Element
    // The time it was deployed
    String timestamp;
    @Element
    // The incremental build number. Default value is: 0.
    int buildNumber;
    @Element
    // Whether to use a local copy instead (with filename that includes the base version). Default value is: false.
    boolean localCopy;
}
