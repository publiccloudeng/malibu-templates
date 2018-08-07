package data.jfrog;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class JfrogData {

    @SerializedName("buildInfo")
    private BuildInfo buildInfo;

    public BuildInfo getBuildInfo() {
        return buildInfo;
    }

    @Override
    public String toString() {
        return buildInfo.toString() ;
    }

    private class BuildInfo {
        @SerializedName("number")
        private String id;

        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public String getUrl() {
            return url;
        }

        public Modules[] getModules() {
            return modules;
        }

        @Override
        public String toString() {
            return "\"id\":" + "\"" + id + '\"' +
                    ", \"url\":" + "\"" + url + '\"' +
                    ", " + modules[0].toString();
        }

        @SerializedName("modules")
        private Modules[] modules;

        public class Modules {
            @SerializedName("artifacts")
            public Artifacts[] Artifacts;

            public Modules.Artifacts[] getArtifacts() {
                return Artifacts;
            }

            @Override
            public String toString() {
                return Artifacts[0].toString();
            }

            public class Artifacts {
                @SerializedName("name")
                private String name;

                @SerializedName("sha256")
                private String sha256;

                public String getName() {
                    return name;
                }

                public String getSha256() {
                    return sha256;
                }

                @Override
                public String toString() {
                    return "\"name\":" + "\"" + name + '\"' +
                            ", \"sha256\":" + "\"" + sha256 + '\"';
                }
            }
        }
    }
}