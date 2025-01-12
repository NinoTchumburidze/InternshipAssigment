package com.Assigment.assigment.service;

import com.Assigment.assigment.model.Transfer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class service {
    private List<Transfer> transferList;
    private List<Transfer> result;
    private int maxWeight;

    public service(){
        transferList = new ArrayList<>();
        result = new ArrayList<>();
    }

    public List<Transfer> createList(String input){
        transferList.clear();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(input);

            JsonNode transfers = root.get("availableTransfers");
            maxWeight = root.get("maxWeight").asInt();

            for (JsonNode node : transfers) {
                int weight = node.get("weight").asInt();
                int cost = node.get("cost").asInt();
                transferList.add(new Transfer(weight, cost));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = optimizeData(transferList);

        return transferList;
    }

    public List<Transfer> optimizeData(List<Transfer> transferList) {
        return result;
    }

    public String resultToString(){
        return "result";
    }
}
