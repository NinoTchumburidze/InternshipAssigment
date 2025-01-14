# Cheapest Transfer Route

## Description
A logistics company is optimizing its package delivery network. Packages
need to be transferred between multiple cities, and each transfer has an
associated cost. Your task is to help the company find the best
combination of transfers while ensuring that the total package weight
stays within a given limit.

## Prerequisites

Before you begin, make sure you have the following installed on your machine:

- **Java 17 or later** 
- **Maven** 
- **Git**

  ### 1. Clone the Repository
First, clone the project repository to your local machine

## To build the application using Maven, run the following command in the project directory

mvn clean install

## Once the build process is complete, you can run the application locally using the following command:

java -jar target/assigment.jar

## Once the application is running, you can access the API by navigating to: http://localhost:8080

## Example CURL Requests and Responses(For Windows, cdm or Powershell)
```nash
1)input:

curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":5,\"cost\":10},{\"weight\":10,\"cost\":20},{\"weight\":3,\"cost\":5},{\"weight\":8,\"cost\":15}]}'

output:

{
"selectedTransfers": [
{"weight": 10, "cost": 20},{"weight": 5, "cost": 10}
],
"totalCost": 30,
"totalWeight": 15
}

2)input
curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":20,\"availableTransfers\":[{\"weight\":10,\"cost\":30},{\"weight\":15,\"cost\":25},{\"weight\":5,\"cost\":15},{\"weight\":12,\"cost\":18}]}'

output:
{
"selectedTransfers": [
{"weight": 5, "cost": 15},{"weight": 10, "cost": 30}
],
"totalCost": 45,
"totalWeight": 15
}

3)input:
curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":4,\"cost\":8},{\"weight\":6,\"cost\":12},{\"weight\":7,\"cost\":14},{\"weight\":5,\"cost\":10}]}'

output:
{
"selectedTransfers": [
{"weight": 5, "cost": 10},{"weight": 6, "cost": 12},{"weight": 4, "cost": 8}
],
"totalCost": 30,
"totalWeight": 15
}

4)input:
curl.exe -X POST http://localhost:8080/processData -H "Content-Type: application/json" -d '{\"maxWeight\":15,\"availableTransfers\":[{\"weight\":20,\"cost\":10},{\"weight\":25,\"cost\":20},{\"weight\":18,\"cost\":5},{\"weight\":30,\"cost\":15}]}'

output:
{
"selectedTransfers": [

],
"totalCost": 0,
"totalWeight": 0
}



