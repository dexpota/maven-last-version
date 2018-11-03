package me.destro.intellij.plugins.qmaven.maven.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Versioning {
    @Element
    // What the latest version in the directory is, including snapshots
    String latest;
    @Element
    // What the latest version in the directory is, of the releases only
    String release;
    @ElementList
    // (Many) Versions available of the artifact (both releases and snapshots)
    List<String> versions;
    @Element
    // When the metadata was last updated
    String lastUpdated;
    @Element
    // The current snapshot data in use for this version (artifact snapshots only)
    Snapshot snapshot;
    @ElementList
    // (Many) Information for each sub-artifact available in this artifact snapshot.
    List<SnapshotVersion> snapshotVersions;
}
