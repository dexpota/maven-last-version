package me.destro.intellij.plugins.qmaven.maven.model;

import org.simpleframework.xml.Element;

public class SnapshotVersion {
    @Element
    // The classifier of the sub-artifact.
    String classifier;
    @Element
    // The file extension of thesub-artifact.
    String extension;
    @Element
    // The resolved snapshot version of the sub-artifact.
    String value;
    @Element
    // The timestamp when this version information was last updated. The timestamp is expressed using UTC in the format yyyyMMddHHmmss.
    String updated;
}
