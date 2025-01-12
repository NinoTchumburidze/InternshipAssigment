package com.Assigment.assigment.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.Assigment.assigment.model.Transfer;
import org.springframework.stereotype.Service;
import com.Assigment.assigment.service.service;

import java.util.List;

class ServiceTest {

    service service = new service();
    @Test
    void optimizationTest1() {

        List<Transfer> transfers1 = List.of(
                new Transfer(10, 30),
                new Transfer(20, 50)
        );
        int maxWeight = 25;

        List<Transfer> result = service.optimizeData(transfers1, 0, 0, maxWeight);

        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getWeight());
    }

    @Test
    void optimizationTest2() {

        List<Transfer> transfers2 = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        );
        int maxWeight = 15;

        List<Transfer> result = service.optimizeData(transfers2, 0, 0, maxWeight);

        assertEquals(2, result.size());
        int totalCost = 0;
        int totalWeight = 0;


        assertEquals(30, totalCost(result));
        assertEquals(15, totalWeight(result));
    }

    //This is invalid case, when maxWeight is less than each of the transfer's weight
    @Test
    void optimizationTestInvalidCase() {

        List<Transfer> transfers1 = List.of(
                new Transfer(15, 30),
                new Transfer(20, 50)
        );
        int maxWeight = 10;

        List<Transfer> result = service.optimizeData(transfers1, 0, 0, maxWeight);


        assertEquals(0, totalCost(result));
        assertEquals(0, totalWeight(result));
        assertTrue(result.isEmpty());
    }

    //Another invalid case: When initial list is empty
    @Test
    void optimizationTestInitialListEmpty() {

        List<Transfer> transfers1 = List.of();
        int maxWeight = 10;

        List<Transfer> result = service.optimizeData(transfers1, 0, 0, maxWeight);

        assertTrue(result.isEmpty());
    }

    // Testing a case when there's a single transfer that is below the maxWeight
    @Test
    void optimizationTestSingleTransfer() {

        List<Transfer> transfers = List.of(new Transfer(5, 15));
        int maxWeight = 10;

        List<Transfer> result = service.optimizeData(transfers, 0, 0, maxWeight);

        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getWeight());
        assertEquals(15, result.get(0).getCost());
    }

    // A case where the sum of all transfer weights is less than maxWeight
    @Test
    void optimizationTestLessThanMaxWeight() {
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5)
        );
        int maxWeight = 20;

        List<Transfer> result = service.optimizeData(transfers, 0, 0, maxWeight);

        assertEquals(3, result.size());
        assertEquals(35, totalCost(result));
        assertEquals(18, totalWeight(result));
    }

    //The sum of the weights is equal to maxWeight
    @Test
    void optimizationTestEqualToMaxWeight() {
        List<Transfer> transfers = List.of(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(5, 15)
        );
        int maxWeight = 20;

        List<Transfer> result = service.optimizeData(transfers, 0, 0, maxWeight);

        assertEquals(3, result.size());
        assertEquals(45, totalCost(result));
        assertEquals(20, totalWeight(result));
    }


    //these are helper methods for calculating totalCost and totalWeight
    private int totalCost(List<Transfer> transfers){
        int total = 0;
        for(Transfer transfer : transfers){
            total += transfer.getCost();
        }
        return total;
    }
    private int totalWeight(List<Transfer> transfers){
        int total = 0;
        for(Transfer transfer : transfers){
            total += transfer.getWeight();
        }
        return total;
    }


}
