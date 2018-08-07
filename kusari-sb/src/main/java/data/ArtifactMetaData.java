package data;

import block.Block;
import data.jenkins.JenkinsData;
import data.jfrog.JfrogData;

public class ArtifactMetaData {
    private JenkinsData jenkinsData ;
    private JfrogData jfrogData ;

    public ArtifactMetaData(JenkinsData jenkinsData, JfrogData jfrogData) {
        this.jenkinsData = jenkinsData ;
        this.jfrogData = jfrogData;
    }

    public ArtifactMetaData() {

    }

    public JenkinsData getJenkinsData() {
        return jenkinsData;
    }

    public void setJenkinsData(JenkinsData jenkinsData) {
        this.jenkinsData = jenkinsData;
    }

    public JfrogData getJfrogData() {
        return jfrogData;
    }

    public void setJfrogData(JfrogData jfrogData) {
        this.jfrogData = jfrogData;
    }

    @Override

    public String toString() {
        return "{" +
                jenkinsData.toString() +
                jfrogData.toString() ;
    }
}
