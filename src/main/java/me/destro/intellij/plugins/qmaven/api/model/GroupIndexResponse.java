package me.destro.intellij.plugins.qmaven.api.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class GroupIndexResponse {
    @ElementList
    public List<Library> libraries;


    public class Library {
        @Attribute(name="versions")
        public String versions;
    }
}
