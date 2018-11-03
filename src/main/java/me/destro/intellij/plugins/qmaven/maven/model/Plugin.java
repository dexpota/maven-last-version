package me.destro.intellij.plugins.qmaven.maven.model;

import org.simpleframework.xml.Element;

public class Plugin {
    @Element
    // Display name for the plugin.
    String name;
    @Element
    // The plugin invocation prefix (i.e. eclipse for eclipse:eclipse)
    String prefix;
    @Element
    // The plugin artifactId
    String artifactId;
}
