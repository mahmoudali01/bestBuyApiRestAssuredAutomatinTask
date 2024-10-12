# bestBuyApiPlayGroundRestAssuredAutomatinTask

This repository contains automation tests for the Best Buy API Playground. The tests are implemented using Java with TestNG for testing framework and are designed to verify the API functionality, including product creation, listing, updating, deletion, and retrieval by ID.

## Requirements

To get started with this project, you will need:

- **Node.js**: Make sure you have Node.js installed on your machine.
- **Allure Commandline**: To view test results in a formatted report
   ```bash
   npm install -g allure-commandline
  
## Getting Started

Follow these steps to set up the project on your local machine:

1. **Clone the Best Buy API Repository**:
   ```bash
   git clone https://github.com/BestBuy/api-playground.git
3. Navigate to the API Playground Directory:
   ```bash
   cd api-playground
5. Install Dependencies:
   ```bash
   npm install
6. Start the API Server on  http://localhost:3030:
    ```bash
    npm start
8. Run the TestNG XML File in the project root to run ProductTest class

## Project Structure
The project is structured as follows:
   ```bash
    bestBuyApiPlaygroudRestAssuredAutomationTask/
    ├── src/
    │   ├── test/
    │   │   └── java/
    │   │       ├── endpoints/        # Contains methods for creating, listing, updating, deleting, and retrieving products by ID.
    │   │       ├── routes/           # Contains the base URL and API paths.
    │   │       ├── payload/          # Contains Product class with attributes and their getters/setters.
    │   │       ├── test/             # Contains ProductTest class with all test methods.
    │   │       └── utils/            # Contains utility classes.
    │   │       └── faker/            # Contains ProductRandomDataGenerator for generating random product data.
    └── allure-results/                # Contains the results of the test execution.
```
## Breakdown

### Endpoints Package
- **Products**: Methods for creating, listing, updating, deleting, and getting products by ID.

### Routes Class
- Contains the base URL: `http://localhost:3030`
- API paths for products and products by ID.

### Payload Package
- **Products**: Class with all product attributes and their getter/setter methods.

### Test Package
- **ProductTest**: Class with all test methods to validate API functionality.

### Utils Package
- Contains utility methods to support testing operations.

### Faker Package
- Contains **ProductRandomDataGenerator**: Class with methods to generate random product data for testing.

### Allure Reports
After running the tests, the test results are saved in the `allure-results` directory at the project root. To view the test results, run the following command from the terminal:

```bash
allure generate allure-results --clean -o allure-report
allure serve
