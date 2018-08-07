package data.jenkins;

import com.google.gson.annotations.SerializedName;
import data.jenkins.ChangeSet;

import java.util.Arrays;

public class JenkinsData {

    @SerializedName("id")
    private String buildId;

    @SerializedName("timestamp")
    private String buildTimestamp;

    @SerializedName("url")
    private String buildUrl;

    @SerializedName("changeSets")
    private ChangeSet[] changeSets;

    public String getBuildId() {
        return buildId;
    }

    public String getBuildTimestamp() {
        return buildTimestamp;
    }

    public String getBuildUrl() {
        return buildUrl;
    }

    @Override
    public String toString() {
        return "\"buildId\":\"" + buildId + '\"' +
                ", \"buildTimestamp\":" + "\"" + buildTimestamp + '\"' +
                ", \"buildUrl\":\"" + buildUrl + '\"' +
                ", " + changeSets[0].toString();
    }
}