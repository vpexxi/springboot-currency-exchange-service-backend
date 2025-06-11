# Currency Exchange Service

A **Spring Boot** microservice that provides a **REST API** for fetching, storing, and converting currency exchange rates using live data from an external API. This project demonstrates a complete end-to-end workflow, from project setup to production-ready API.

---

## 🚀 Features

* **Fetch & Store Rates**: Retrieve live exchange rates from the [ExchangeRate API](https://www.exchangerate-api.com/) and persist them in an H2 database.
* **Retrieve Latest Rate**: Query the most recently stored rate for any currency pair.
* **Convert Amounts**: Convert monetary values using the latest stored rate.
* **Error Handling**: Returns meaningful HTTP status codes (`400`, `404`, `201`, `200`).
* **Configuration-Driven**: Externalize API keys and database settings in `application.yml`.
* **Auto-Configuration & Starters**: Leverage Spring Boot’s auto-configuration and starter dependencies for rapid development.
* **Postman Collection**: Includes a ready-to-import collection to test all endpoints and error scenarios.

---

## 📂 Project Structure

```
currency-exchange-app/
├── src/main/java/com/drill/currencyexchangeapp/
│   ├── controller/       # REST controllers
│   ├── service/          # Service interfaces & implementations
│   ├── repository/       # Spring Data JPA repositories
│   ├── model/            # JPA entity classes
│   └── dto/              # External API response mappings
├── src/main/resources/
│   └── application.yml   # Application configuration (DB, API keys, etc.)
├── src/test/java/        # Unit tests for service layer
├── pom.xml               # Maven build file (dependencies & plugins)
└── README.md             # Project documentation
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
   git clone https://github.com/your-username/springboot-currency-exchange-service-backend.git
   cd springboot-currency-exchange-service-backend
   ```
2. **Obtain and configure your API key**

   1. Sign up for a free key at [ExchangeRate API](https://www.exchangerate-api.com).
   2. In your account dashboard, copy your **API Key**.
   3. Open `src/main/resources/application.yml` and replace `YOUR_API_KEY`:

      ```yaml
      exchange:
        api:
          url: https://v6.exchangerate-api.com/v6  # Base URL for all requests
          key: <YOUR_API_KEY>                     # ← Paste your key here
      ```
   4. Verify you can fetch live rates:

      ```bash
      curl https://v6.exchangerate-api.com/v6/$YOUR_API_KEY/latest/USD
      ```

```yaml
   exchange:
     api:
       url: https://v6.exchangerate-api.com/v6
       key: YOUR_API_KEY   # Replace with your key from ExchangeRate API
```

3. **Build & run** the application

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. **Access H2 Console** (for database exploration)

   * URL: `http://localhost:8080/h2-console`
   * JDBC URL: `jdbc:h2:mem:currencydb`
   * User: `sa`, Password: *(leave blank)*

---

## 📡 API Endpoints

| Method | Endpoint                                   | Description                                   |
| ------ | ------------------------------------------ | --------------------------------------------- |
| GET    | `/api/ping`                                | Health check, returns `pong`.                 |
| POST   | `/api/rates/fetch?from={from}&to={to}`     | Fetches live rate, stores it, returns entity. |
| GET    | `/api/rates/latest?from={from}&to={to}`    | Returns the most recently stored rate.        |
| GET    | `/api/convert?from={from}&to={to}&amount=` | Converts the amount using the stored rate.    |

**Error Responses**:

* `400 Bad Request`: Invalid or unsupported currency codes.
* `404 Not Found`: No rate stored for the specified currency pair.

---

## 🛠️ Testing

1. **Unit Tests**

   ```bash
   mvn test
   ```
2. **Postman Collection**

   * Import `postman/ExchangeRateService.postman_collection.json` to execute happy-path and error-case scenarios.

---

## 🔑 Skills & Technologies

* **Java 8+**: `BigDecimal`, `Instant`, Java 8 streams
* **Spring Boot**: Auto-configuration, starter dependencies, embedded Tomcat
* **Spring Data JPA**: Entity mapping, CRUD, custom finder methods
* **Hibernate**: ORM, schema generation, transaction management
* **WebClient**: Reactive HTTP client for external API integrations
* **H2 Database**: In-memory persistence for rapid development
* **REST API Design**: Controllers, validation, HTTP status codes
* **Error Handling**: Custom exceptions, `@ControllerAdvice`
* **Maven**: Dependency & build management, plugins
* **Testing**: JUnit 5, Mockito, Spring Boot Test
* **API Documentation**: Postman collections for endpoint validation


