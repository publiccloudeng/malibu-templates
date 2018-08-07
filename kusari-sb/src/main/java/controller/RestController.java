package controller;

import block.Block;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import data.ArtifactMetaData;
import data.jenkins.JenkinsData;
import data.jfrog.JfrogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class RestController {

    private static final Logger logger = LoggerFactory.getLogger(RestController.class);
    public ArrayList<Block> blockchain ;
    private static ArtifactMetaData artifactMetaData = new ArtifactMetaData() ;
    @ResponseBody
    @RequestMapping("/artifactMeta/{buildNumber}")
    public String jenkins(
            @PathVariable("buildNumber") int buildNumber) {
        HttpResponse<String> response = null;
        blockchain = new ArrayList<Block>();
        try {
            response = Unirest
                    .get("https://ec2-34-229-197-127.compute-1.amazonaws.com:8082/job/blockchain-job/job/master/" + buildNumber + "/api/json")
                    .basicAuth("admin", "Hackathon")
                    .asString();
        } catch (UnirestException e) {
            logger.error(e.getMessage());
        }

        JenkinsData jenkinsData = new Gson().fromJson(response.getBody(), JenkinsData.class);
        System.out.println(jenkinsData.toString());
        artifactMetaData.setJenkinsData(jenkinsData);
        jfrog(buildNumber);
        try {
            blockchain.add(new Block("Hi im the first block", "0"));

            blockchain.add(new Block(artifactMetaData.toString(), blockchain.get(blockchain.size() - 1).hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String currentHashKeyVal = ", \"currentBlockHash\": " + "\"" + blockchain.get(0).hash + "\"";
        String previousHashKeyVal = ", \"previousBlockHash\" : " + "\"" + blockchain.get(0).previousHash + "\"}";
        return artifactMetaData.toString() + currentHashKeyVal + previousHashKeyVal;
    }

    @ResponseBody
    @RequestMapping("/jfrog/{buildNumber}")
    public String jfrog(
            @PathVariable("buildNumber") int buildNumber) {
        HttpResponse<String> response = null;
        try {
            response = Unirest
                    .get("http://ec2-35-171-187-66.compute-1.amazonaws.com:8081/artifactory/api/build/blockchain-job%20::%20master/" + buildNumber)
                    .basicAuth("admin", "Hackathon")
                    .asString();
        } catch (UnirestException e) {
            logger.error(e.getMessage());
        }

        JfrogData jfrogData = new Gson().fromJson(response.getBody(), JfrogData.class);
        System.out.println(jfrogData.toString());
        artifactMetaData.setJfrogData(jfrogData);

        return jfrogData.toString();
    }

}