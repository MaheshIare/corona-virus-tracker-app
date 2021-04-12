# Corona virus tracker app

## About
This is a simple demo application for show casing the stats of **[Corona Virus](https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data/csse_covid_19_time_series)** with 
**[Spring Boot](https://spring.io/projects/spring-boot)**.

## Requirements
This demo is build with with Spring boot 2.4.4.

## Usage
Just start the application with the Spring Boot maven plugin (`mvn spring-boot:run`). The application will be 
running at [Localhost](http://localhost:8080/corona-virus-tracker).

## Backend
Implemented **[spring-cache](https://spring.io/guides/gs/caching/)** for avoiding unnecessary calls to the global data services everytime and make data retrieval faster. There are three endpoint(s) developed for this demo:

```bash
GET - /confirms - Stats generation API for confirmed cases 
GET - /recoveries - Stats generation API for recovered cases
GET - /deaths - Stats generation API for death cases

```

## Questions
If you have project related questions please create a ticket with your question here [Create Issue](https://github.com/MaheshIare/corona-virus-tracker/issues)


## Author

**Mahesh Kumar Gutam**

* [Github](https://github.com/MaheshIare)

## Feedback
Please feel free to send me some feedback or questions!
