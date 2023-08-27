# Cards RESTful web service 

An application named Cards that allows users to create and manage tasks in the form of cards.

## Pre-requisites

#### Java 17
#### MySQL
#### maven

## How to run the project

#### Setting up IDE

``` http
1. Open the project using IDE of you choice i.e. IntelliJ idea, spring tool suite etc.
```

#### Setting up database

```http
  2. Change the database configuration ##/src/main/resources/application.yml
    a) url: jdbc:mysql://127.0.0.1:3306/cards?createDatabaseIfNotExist=true
    b) username: root
    c) password: Baiks@123
  3. Turn on the liquibase flag to true
    liquibase:
    change-log: classpath:/database/changelog-root.xml
    enabled: false
```

#### Run the project

```http
Run the project using IDE. You expect the code to run successfully and execute the database script in 'src/main/resources/database/cards20230827.sql'

Incase of challenges with liquibase, you can pick the script and run it directly on mysql database.
```


