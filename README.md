#Flight Booking Management System

## Overview
The AirIndia Airline Management System is a Java EE-based web application designed to manage various aspects of an airline, including customer registration, flight management, seat selection, booking management, and customer complaints. The application is built using JavaServer Faces (JSF) for the frontend and Enterprise JavaBeans (EJB) for the backend, with a MySQL database for data storage.

## Features
- **Customer Management**: Registration, login, and profile management.
- **Flight Management**: Adding, updating, and canceling flights.
- **Booking Management**: Searching for flights, selecting seats, and booking tickets.
- **Customer Complaints**: Submitting and managing customer complaints.
- **Email Notifications**: Sending email notifications for various events.
- **Admin Panel**: Managing flights, bookings, and viewing recent payments.

## Project Structure
- `src/main/java/com/controller/`: Java classes for handling various functionalities.
- `src/main/java/com/converter/`: Java classes for data conversion and validation.
- `src/main/java/com/entity/`: Java classes representing database entities.
- `src/main/java/com/model/`: Java classes for managing models.
- `src/main/webapp/`: Web application files, including XHTML pages and resources.
- `src/main/webapp/WEB-INF/lib/`: Java libraries.
- `src/test/java/com/manoj/Airindia/`: Test classes.
- `pom.xml`: Maven build configuration file.

## Prerequisites
- JDK 11 or higher
- Apache Maven
- MySQL database
- Jakarta EE or compatible application server (e.g., Apache TomEE, WildFly)

## Setup and Installation
1. **Clone the repository**:
    ```sh
    git clone <repository-url>
    cd Airindia
    ```

2. **Configure the database**:
    - Create a MySQL database.
    - Update the database connection details in the `src/main/resources/META-INF/persistence.xml` file.

3. **Build the project**:
    ```sh
    mvn clean install
    ```

4. **Deploy the application**:
    - Deploy the generated WAR file located in the `target` directory to your application server.

5. **Access the application**:
    - Open your web browser and navigate to the application URL (e.g., `http://localhost:8080/Airindia`).

## Usage
- **Customer Portal**: Customers can register, login, search for flights, book tickets, and submit complaints.
- **Admin Portal**: Admins can manage flights, view bookings, and handle customer complaints.

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

## License
This project is licensed under the MIT License.
