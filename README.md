# Swag Labs Automation Test Framework 🚀

An automated end-to-end testing framework built for the **Swag Labs (SauceDemo)** e-commerce web application. This project follows industry-standard design patterns and incorporates robust logging and advanced reporting mechanisms.

---

## 🛠️ Tech Stack & Tools

* **Language:** Java (JDK 26)
* **Automation Tool:** Selenium WebDriver
* **Design Pattern:** Page Object Model (POM) with Singleton WebDriver Factory
* **Test Framework:** TestNG
* **Reporting:** Allure Reports
* **Logging:** Log4j2
* **Build Management:** Maven

---

## 📂 Project Architecture

```text
SwagLabsAutomation/
│
├── src/
│   ├── main/java/
│   │   ├── base/               # Base class for driver initialization & teardown
│   │   ├── pages/              # Page Object classes (LoginPage, ProductsPage, CartPage, CheckoutPage, etc.)
│   │   └── utils/              # Utilities (Logger, ConfigاHandler, etc.)
│   │
│   └── test/
│       ├── java/
│       │   └── tests/          # Test script classes (LoginTests, E2ETest, CheckoutTest, etc.)
│       └── resources/
│           ├── log4j2.xml      # Logging configuration
│           └── testng.xml      # Test suite configuration file
│
├── allure-results/             # Generated Allure raw results
├── pom.xml                     # Maven dependencies and plugins
└── README.md

```

---

## 🧪 Scenarios Covered

1. **Login Test:**
* Validates successful login with standard credentials.
* Handles invalid login attempts and error message validations.


2. **Add to Cart & Cart Validation:**
* Selects products from the inventory page and adds them to the cart.
* Verifies item details (name, price, quantity) inside the cart.


3. **Price & Tax Calculation Verification:**
* Computes the subtotal of items in the cart.
* Validates that the calculated tax matches the expected percentage.
* Verifies that the final Total ($Item Total + Tax$) is accurate.


4. **End-to-End (E2E) Checkout Scenario:**
* Complete journey: Login $\rightarrow$ Add product to cart $\rightarrow$ Go to cart $\rightarrow$ Checkout (Enter First Name, Last Name, Postal Code) $\rightarrow$ Review Order & Totals $\rightarrow$ Finish Order $\rightarrow$ Success Confirmation.



---

## ⚙️ Prerequisites

Before running the project, ensure you have the following installed on your machine:

* **Java JDK** (v26 or higher)
* **Maven** (v3.11+)
* **Google Chrome** (latest version) and matching ChromeDriver (or let Selenium Manager handle drivers automatically)
* **Allure Commandline** (for viewing reports)

---

## 🚀 How to Run the Tests

1. Clone the repository:
```bash
git clone https://github.com/your-username/swag-labs-automation.git
cd swag-labs-automation

```


2. Run the tests using Maven via the TestNG suite XML file:
```bash
mvn clean test

```



---

## 📊 Generating & Viewing Allure Reports

This framework uses Allure for detailed, visually appealing test reports containing execution steps, logs, screenshots on failure, and environment details.

1. After test execution, generate and serve the Allure report:
```bash
mvn allure:serve

```


2. Alternatively, generate the report manually:
```bash
mvn allure:report

```


The report folder will be created under `target/site/allure-maven-plugin`.

---



## 📝 Logging

* **Log4j2** is integrated throughout the framework to track test execution steps, driver actions, and assertion results in real time.
* Logs are printed directly to the console and stored in the designated log file configured in `log4j2.xml`.
