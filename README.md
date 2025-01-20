# Finance Tracker 
## Overview
The Finance Tracker project is a comprehensive application designed to manage and track financial transactions, including incomes, expenses, and budgets. The project leverages Hibernate ORM for database interactions, ensuring efficient and seamless data management.

## Table of Contents

1. Overview
2. Project Structure
3. Setting Up the Project
4. Running the Project
5. Using the Application
6. Hibernate ORM
7. Contributing
8. Reporting Issues
9. License

## Project Structure
The project is organized into several packages, each serving a specific purpose:

- **models:** Contains entity classes representing database tables.
- **dao:** Data Access Object interfaces for CRUD operations.
- **daoImpl:** Implementations of DAO interfaces.
- **service:** Business logic and service layer.
- **util:** Utility classes, including Hibernate configuration.
- **exceptionHandling:** Custom exceptions for error handling.

## Models
The `models` package contains entity classes annotated with JPA annotations to map them to database tables. Key classes include:

- **BankAccount:** Represents a bank account.
- **Income:** Represents an income record.
- **Expense:** Represents an expense record.
- **IncomeExpenseSources:** Represents sources of income and expenses.
- **BudgetTracker:** Represents budget tracking information.
- **AccountTransaction:** Represents a financial transaction.

### DAO and DAOImpl
The `dao` package defines interfaces for CRUD operations, while the `daoImpl` package provides implementations of these interfaces using Hibernate.

### Services
The `service` package contains classes that implement business logic and interact with DAOs.

### Exception Handling
The `exceptionHandling` package contains custom exceptions to handle specific error scenarios.

### Utility
The `util` package contains utility classes, including Hibernate configuration.

## Setting Up the Project

**Prerequisites**

Before you can run the project, ensure you have the following installed:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [MySQL](https://www.mysql.com/products/community/)
- [Maven](https://maven.apache.org)

### Clone the Repository
Clone the project repository from GitHub to your local machine:
```shell
git clone https://github.com/yourusername/finance-tracker.git
cd finance-tracker
```

### Configure the Database

1. **Create a MySQL Database:** Create a database named finance_tracker.
2. **Update Hibernate Configuration:** Update the hibernate.cfg.xml file with your MySQL database connection details, including the URL, username, and password.

### Build the Project
Use Maven to build the project:
```shell
mvn clean install
```

## Running the Project
**Using an IDE**

1. **Import the Project:** Open your preferred IDE (e.g., IntelliJ IDEA, Eclipse) and import the project as a Maven project.
2. **Run the Main Class:** Locate the App.java file in the java directory and run it.

### Using the Command Line
Navigate to the target directory and run the compiled JAR file:
```shell
cd target
java -jar finance-tracker-1.0-SNAPSHOT.jar
```

## Using the Application
**Main Menu**

Upon running the application, you will be presented with a main menu. The menu provides options to manage bank accounts, incomes, expenses, and budgets.

**Adding a Bank Account**

1. Select the option to add a bank account.
2. Enter the required details, such as the bank name.

**Adding an Income**

1. Select the option to add an income.
2. Enter the required details, such as the income name, bank account, amount, and source.

**Adding an Expense**

1. Select the option to add an expense.
2. Enter the required details, such as the expense name, bank account, amount, and category.

**Viewing Transactions**

1. Select the option to view transactions.
2. Choose the type of transactions you want to view (e.g., incomes, expenses).

## Hibernate ORM
Hibernate ORM (Object-Relational Mapping) is a powerful framework for mapping Java objects to database tables. It abstracts the complexities of database interactions, allowing developers to focus on the business logic. Hibernate provides features like:

- Automatic table creation
- Transparent persistence
- HQL (Hibernate Query Language) for database queries
- Transaction management

**Configuration**

The Hibernate configuration is defined in the `hibernate.cfg.xml` file. This file includes database connection settings, dialect, and mappings for entity classes.

**Entity Classes**

Entity classes are annotated with JPA annotations to map them to database tables. Each entity class represents a table in the database and includes fields that correspond to columns in the table.

## Contributing
We welcome contributions to the Finance Tracker project. To contribute, follow these steps:

1. **Fork the Repository:** Fork the project repository on GitHub.
2. **Create a Branch:** Create a new branch for your feature or bug fix.
3. **Make Changes:** Make your changes in the new branch.
4. **Submit a Pull Request:** Submit a pull request to the main repository.

## Reporting Issues
If you encounter any issues or bugs, please report them on the project's GitHub issue tracker.

## License
This project is licensed under the MIT License. 
