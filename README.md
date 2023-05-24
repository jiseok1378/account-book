# Account Book 0.0.0

## Index

- [1. Project environments information](#1-project-environments-information)
- [2. Setting project environment](#2-setting-project-environments)
- [3. Build project](#3-build-project)
- [4. Skill Set](#4-skill-set)
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

### 2-1. Common

> docker & docker-compose install

- Install Docker

  ```bash
  $ sudo apt update
  $ sudo apt install apt-transport-https ca-certificates curl software-properties-common
  $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
  $ sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" 
  $ sudo apt update
  $ sudo apt install docker-ce
  $ sudo systemctl status docker
  # Active: active (running)
  ```
- Install Docker Compose

  ```bash
  $  sudo curl -L "https://github.com/docker/compose/releases/download/v2.5.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
  $ sudo chmod +x /usr/local/bin/docker-compose
  $ docker-compose -v
  ```
- Create Postgres Container

  ```bash
  $ sudo docker-compose up -d
  ```

<br />

### 2-2. Local 

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

> postgres

- docker-compse 

  ```bash
  $ docker-compose up -d
  ```

<br />

### 2-3. Docker Development

#### **🚨 Improtant 🚨**

**In the Docker environment, Vue hot reload works only on the Linux operating system. If running on Windows, please install and use docker-compse on WSL Ubuntu or other Linux operating systems.**

> Init 

- docker-compose

  ```bash
  $ sudo ./docker-compose-dev up -d
  ```

> Rebuild project
  
- docker-compose 

  ```bash
  $ sudo ./docker-compose-dev restart
  ```

--- 

<br />

## 3. Build project

> Build docker container 

- docker-compose 

  ```bash
  $ sudo ./docker-compose-prd up -d
  ```


<br />

## 4. Skill Set

> server

- Spring book framework  
  - WAS(Web Application Server)

- Spring web soket
  - Pair Message Alarm (Web Socket)

- Mybatis
  - ORM 

- JWT
  - Authentication, Authorization

- Lombok
  - Java code diet library

- Junit
  - Java unit test

> web


- Node.js + Typescript
  - Web language

- Vue.js
  - UI Interface framework

- Yarn
  - Node.js package manager
  
- Vuex 
  - Magnagement state + pattern library 

- Vuetify
  - UI component Framework

- Axios
  - Promise based HTTP client

- Web socket client
  - web socket client
  
- Nginx
  - load-balancer, reverse-proxy
