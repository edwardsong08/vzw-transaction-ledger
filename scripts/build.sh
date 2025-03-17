#!/usr/bin/env bash
# build.sh
# This script cleans, builds, and packages the application using Gradle.

echo "=== Starting Build Script ==="

# Step 1: Clean the project
./gradlew clean || { echo "Gradle clean failed"; exit 1; }

# Step 2: Build the project (compile + test)
./gradlew build || { echo "Gradle build failed"; exit 1; }

echo "=== Build completed successfully! ==="
