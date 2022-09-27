# Contact List Web App

A simple contact list app which lists all the contacts from the database using React for UI and Spring Boot for the backend service.


## Technologies Used

**Server:** Java 8, Spring Boot (2.7.x), Spring Batch, H2 In memory database

**Client:** React (18.2.X)

**Build Tool:** Gradle
## Running contact list app locally

Clone the repository to a directory and run the below commands.

```bash
  git clone https://github.com/abhilash28abhi/contact-list-app.git
  cd contact-list-app
  ./gradlew bootrun
```

Access the application from browser at :
```bash
http://localhost:8080
```
Access the API documentation at :
```bash
http://localhost:8080/swagger-ui.html
```

## Build

To make changes in React UI code navigate to below folder within the project and follow the below steps:

```
1.  cd contact-list-app/src/frontend
2.  Run npm run build, command after making the changes
3.  Copy the contents from /src/frontend/build folder and paste it to /src/main/resources/public folder
```

To build the spring boot app, run the below gradle command

```
./gradlew clean build
```