# Account Book 0.0.0

## Index

- [1. Project environments information](#1-project-environments-information)
- [2. Setting project environment](#2-setting-project-environments)
- [3. Build project](#3-build-project)
---
<br />

## 1. Project environments information

|   Project Name     |             OS         |  Languege(version)  |   WAS Framework  |    Package Manager   |
|--------------------|------------------------|---------------------|------------------|----------------------|
|       server       | Ubuntu:20.04 / Windows |  Java (OpenJDK-11)  |    Spring Boot   |      Gradle(6.9)     |
|        web         | Ubuntu:20.04 / Windows |  Node.js (18.16.0)  |  Vue.js(vue cli) |        Yarn          | 

---
<br />

## 2. Setting project environments

<br />

> web

- Install Node.js
  
  - Windows: [Download page (nodejs.org)](https://nodejs.org/dist/v18.16.0/node-v18.16.0-x64.msi) 
  
  - Ubuntu
  ```
    $ curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash -
    $ apt-get install -y nodejs
  ```

- Install yarn
  ```
  $ npm install -g yarn
  ```

- Install Vue cli
  ```
  $ npm install -g vue
  $ npm install -g @vue/cli
  
  # Windows (Admin)
  $ Set-ExecutionPolicy Unrestricted
  ```

- Initialize project

  ```
  $ cd ./web && yarn install
  ```

<br />

> server

- Install Java (OpenJDK-11)   

  - Windows: [Download Page (Oracle)](https://download.java.net/java/GA/jdk11/13/GPL/openjdk-11.0.1_windows-x64_bin.zip)
  - Ubuntu 
  ```
  $ sudo apt-get install openjdk-11-jdk  
  ```

- Install Gradle (Optional)
  
  - Windows: [Download Page (Gradle)](https://gradle.org/next-steps/?version=6.9.4&format=bin)

  - Ubuntu
  ```
  $ sdkman install gradle-6.9
  ```

- Initialize project
  ```
  $ cd ./accountBookBack && gradle build
  ```

--- 

<br />

## 3. Build project

<br />