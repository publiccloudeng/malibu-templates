package data.jenkins;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class ChangeSet {
    public Items[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items[0].toString();
    }

    public void setItems(Items[] items) {
        this.items = items;
    }

    @SerializedName("items")
    private Items[] items;

    public class Items {
        @SerializedName("commitId")
        private String commitId;

        @SerializedName("author")
        private Author author;

        @SerializedName("affectedPaths")
        private String[] affectedPaths;

        @Override
        public String toString() {
            return "\"commitId\":" + "\"" + commitId + '\"' +
                    ", " + author.toString() +
                    ", \"affectedPaths\":" + "\"" + Arrays.toString(affectedPaths) + "\"" + ", ";
        }

        public String getCommitId() {
            return commitId;
        }

        public void setCommitId(String commitId) {
            this.commitId = commitId;
        }

        public String[] getAffectedPaths() {
            return affectedPaths;
        }

        public void setAffectedPaths(String[] affectedPaths) {
            this.affectedPaths = affectedPaths;
        }

        public class Author {
            @Override
            public String toString() {
                return "\"authorUrl\":" + "\"" + absoluteUrl + '\"' +
                        ", \"fullName\":" + "\"" + fullName + '\"';
            }

            @SerializedName("absoluteUrl")
            private String absoluteUrl;

            public String getAbsoluteUrl() {
                return absoluteUrl;
            }

            public void setAbsoluteUrl(String absoluteUrl) {
                this.absoluteUrl = absoluteUrl;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            @SerializedName("fullName")
            private String fullName;
        }
    }
}
