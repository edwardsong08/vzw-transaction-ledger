#!/usr/bin/env bash
# run.sh
# This script starts the Spring Boot app from the built .jar.

echo "=== Starting Application ==="

# Check if container exists
#if [ "$(docker ps -a -q -f name=^local-postgres$)" ]; then
  # If it's not running, start it
    #docker start local-postgres
#else
  # Create a new one if none found
    #docker run -d --name local-postgres -p 5432:5432 \
    #-e POSTGRES_USER=vzwuser -e POSTGRES_PASSWORD=vzwpass \
    #-e POSTGRES_DB=vzwledger \
    #postgres:latest
#fi

# Step 1: Make sure we've built the .jar. 
# You could call build.sh first if you like:
.scripts/build.sh

# Step 2: Run the .jar
JAR_FILE=./build/libs/vzw-transaction-ledger-0.0.1-SNAPSHOT.jar

if [ ! -f "$JAR_FILE" ]; then
  echo "JAR file not found: $JAR_FILE"
  echo "Please run build.sh first or verify your jar location."
  exit 1
fi

echo "Running JAR: $JAR_FILE"
java -jar $JAR_FILE
