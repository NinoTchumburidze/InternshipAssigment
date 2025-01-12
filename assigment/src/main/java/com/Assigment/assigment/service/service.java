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

        result = optimizeData(transferList, 0, 0, maxWeight);

        return transferList;
    }

    public List<Transfer> optimizeData(List<Transfer> transferList, int index, int totalCost, int capacity) {
        //Base case: no more items in list or the capacity is full
        if (index == transferList.size() || capacity == 0) {
            return new ArrayList<>();
        }

        Transfer currentTransfer = transferList.get(index);

        // Case 1: not including current Transfer and moving to the next
        if (currentTransfer.getWeight() > capacity) {
            return optimizeData(transferList, index + 1, totalCost, capacity);
        } else {
            // Including the current transfer
            List<Transfer> included = optimizeData(transferList,index + 1,totalCost + currentTransfer.getCost(), capacity - currentTransfer.getWeight());
            // Exclude the current transfer and proceed with the same capacity
            List<Transfer> excluded = optimizeData(transferList, index + 1, totalCost, capacity);

            // Compare which option gives us more cost
            if (calculateTotalCost(included) + currentTransfer.getCost() > calculateTotalCost(excluded)) {
                included.add(currentTransfer);
                return included;
            } else {
                return excluded;
            }
        }
    }

    //Helper function for calculating total cost of Selected transfers
    private int calculateTotalCost(List<Transfer> transferList) {
        int total = 0;
        for (Transfer t : transferList) {
            total += t.getCost();
        }
        return total;
    }

    //Converting Result into JSON format
    public String resultToString(){
        int totalCost = 0;
        int totalWeight = 0;

        StringBuilder builder = new StringBuilder();
        for(Transfer t : result){
            totalCost += t.getCost();
            totalWeight += t.getWeight();

            if(builder.length() > 0){
                builder.append(",");
            }

            builder.append(t.toString());
        }


        return "{\n\"selectedTransfers\": [\n" + builder.toString() + "\n],\n" +
                "\"totalCost\": " + totalCost + ",\n" +
                "\"totalWeight\": " + totalWeight + "\n}";
    }

}
