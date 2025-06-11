# Currency Exchange Service

A **Spring Boot** microservice that provides a **REST API** for fetching, storing, and converting currency exchange rates using live data from an external API. This project showcases a complete end-to-end workflow including:

* **Dependency Management** with Spring Boot Starters
* **Configuration** via `application.yml`
* **Domain Modeling** using JPA Entities
* **Data Access** with Spring Data JPA Repositories
* **Business Logic** in a Service Layer
* **External Integration** using WebClient
* **REST Endpoints** in Controllers
* **Error Handling** with `@ControllerAdvice`
* **Unit Testing** and **Postman** validation

---

## 🚀 Features

* **Fetch & Store Rates**: Fetch live exchange rates from ExchangeRate API and persist them in an H2 database.
* **Retrieve Latest Rate**: Query the most recent rate for any currency pair.
* **Convert Amounts**: Convert monetary values using the latest stored rate.
* **Robust Error Handling**: Returns appropriate HTTP status codes (`400`, `404`, `201`, `200`).
* **Configuration-Driven**: Externalize API keys and database settings in `application.yml`.
* **Auto-configuration & Starters**: Leverage Spring Boot’s auto-config and starters for rapid development.
* **Postman Collection**: Includes a ready-to-import collection to test all endpoints and error scenarios.

---

## 📂 Project Structure

```
currency-exchange-app/
├── src/main/java/com/drill/currencyexchangeapp
│   ├── controller       # REST controllers
│   ├── service          # Service interfaces & implementations
│   ├── repository       # Spring Data JPA repositories
│   ├── model            # JPA entity classes
│   └── dto              # External API response mappings
├── src/main/resources/
│   └── application.yml  # Configuration (DB, API keys, etc.)
├── src/test/java        # Unit tests
├── pom.xml              # Maven build file with dependencies and plugins
└── README.md            # Project documentation
```

---

## ⚙️ Getting Started

### Prerequisites

* **Java 8+**
* **Maven 3.6+**
* (Optional) **IDE**: IntelliJ IDEA, Eclipse, VS Code

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/your-username/currency-exchange-service.git
   cd currency-exchange-service
   ```
2. **Configure API key** in `src/main/resources/application.yml`

   ```yaml
   exchange:
     api:
       url: https://v6.exchangerate-api.com/v6
       key: YOUR_API_KEY
   ```
3. **Build & run** the application

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access H2 Console** (for schema inspection)

    * URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * JDBC URL: `jdbc:h2:mem:currencydb`
    * User: `sa`, Password: (blank)

---

## 📡 API Endpoints

| Method | Endpoint                         | Description                                             |
| ------ | -------------------------------- | ------------------------------------------------------- |
| GET    | `/api/ping`                      | Health check, returns `pong`.                           |
| POST   | `/api/rates/fetch?from=&to=`     | Fetches live rate, stores it, returns the saved record. |
| GET    | `/api/rates/latest?from=&to=`    | Returns the most recently stored rate.                  |
| GET    | `/api/convert?from=&to=&amount=` | Converts the given amount using the latest rate.        |

**Error Responses**:

* `400 Bad Request` for unsupported currencies.
* `404 Not Found` when no rate is stored for a pair.

---

## 🛠️ Testing

1. **Unit Tests**: Run with

   ```bash
   mvn test
   ```
2. **Postman Collection**: Import `postman/ExchangeRateService.postman_collection.json` to test all endpoints, including error cases.

---

## 🔑 Skills & Technologies

* **Java 8+**: Core language features, BigDecimal, Instant
* **Spring Boot**: Auto-configuration, starters, embedded server
* **Spring Data JPA**: Entity mapping, repositories, custom finder methods
* **Hibernate**: ORM provider, schema generation, transaction management
* **WebClient**: Reactive HTTP client for external API calls
* **H2 Database**: In-memory persistence for rapid development and testing
* **REST API Design**: Controllers, request validation, HTTP status codes
* **Error Handling**: Custom exceptions, `@ControllerAdvice`
* **Maven**: Dependency management, build lifecycle, plugins
* **Testing**: JUnit, Mockito, Spring Boot Test
* **API Documentation**: Postman collections


