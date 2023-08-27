# Cards RESTful web service 

An application named Cards that allows users to create and manage tasks in the form of cards.

## Pre-requisites

- [**Java 17**](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [**MySQL**](https://www.mysql.com/downloads/)
- [**Maven**](https://maven.apache.org/download.cgi)

## How to run the project

#### Setting up IDE

```sh
Open the project using IDE of you choice i.e. IntelliJ idea, spring tool suite etc.
```

#### Setting up database

- Change the database configuration `/src/main/resources/application.yml`
    `url: jdbc:mysql://127.0.0.1:3306/cards?createDatabaseIfNotExist=true`
    `username: root`
    `password: Baiks@123`
- Turn on the liquibase flag to true
    `liquibase:`
       `change-log: classpath:/database/changelog-root.xml`
       `enabled: false`


#### Run the project

- Run the project using IDE. You expect the code to run successfully and execute the database script in `src/main/resources/database/cards20230827.sql`

- Incase of challenges with liquibase, you can pick the script and run it directly on mysql database.

## Access the APIs
```sh
To access the APIs, open the link http://localhost:8080/swagger-ui/index.html
```

```sh
You can create your own user on swagger or alternatively use the already configured users.
```

#### Admin User
`Username: admin@gmail.com`
`Password: admin@123`

#### Member User
`Username: member@gmail.com`
`Password: member@123`


