package me.destro.intellij.plugins.qmaven.api.model;

import com.squareup.moshi.Json;

import java.util.List;

public class SelectResponse {

    public Response response;
    public Spellcheck spellcheck;
    public ResponseHeader responseHeader;

    public static class Response {
        public int start;
        public int numFound;
        public List<Response.Entry> docs;

        public static class Entry{
            public String id;
            public String g;
            public String a;
            public String latestVersion;
            public String repositoryId;
            public String p;
            public String timestamp;
            public int versionCount;
            public List<String> text;
            public List<String> ec;
        }
    }


    public static class ResponseHeader {
        public int status;
        @Json(name = "QTime")
        public int qtime;
        public ResponseHeader.Params params;
        
        public static class Params {
            public String q;
            public String spellcheck;
            public String indent;
            public String fl;
            @Json(name = "spellcheck.count")
            public String spellcheckCount;
            public String sort;
            public String rows;
            public String wt;
            public String version;
        }
    }

    public static class Spellcheck {
        public List<String> suggestions;
    }

}
