package me.destro.intellij.plugins.qmaven.maven.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


// see https://maven.apache.org/ref/3.2.5/maven-repository-metadata/repository-metadata.html
@Root(name="metadata")
public class Metadata {
    @Attribute
    // The version of the underlying metadata model.
    String modelVersion;

    @Element(required = false)
    // The groupId that this directory represents, if any.
    String groupId;
    @Element(required = false)
    // The artifactId that this directory represents, if any.
    String artifactId;
    @Element(required = false)
    // The version that this directory represents, if any. It is used for artifact snapshots only.
    String version;

    @Element
    // Versioning information for the artifact.
    Versioning versioning;
    @ElementList
    // (Many) The set of plugin mappings for the group represented by this directory
    List<Plugin> plugins;
}