### Objective
Your assignment is to build an internal API for a fake financial institution using Java, Spring, MongoDB.

### Brief
While modern banks have evolved to serve a plethora of functions, at their core, banks must provide certain basic features. 
Today, your task is to build a basic HTTP REST API and/or event driven async service for one of those banks! Imagine you are designing a backend API for bank employees. It could ultimately be consumed by multiple frontends (web, iOS, Android etc).

### Tasks
- Implement assignment using:
  - Language: **Java 8+**
  - Framework: **Spring** or **Apache camel**
- There should be API routes that allow them to:
  - Create a new bank account for a customer, with an initial deposit amount. A
    single customer may have multiple bank accounts.
  - Transfer amounts between any two accounts, including those owned by
    different customers.
  - Retrieve transfer history for a given account.
- Throw proper HTTP response codes from each REST service
- Write tests for your business logic
Feel free to pre-populate your customers with the following:
```json
[
  {    "id": 1,    "name": "Arisha Barron"  },
  {    "id": 2,    "name": "Branden Gibson"  },
  {    "id": 3,    "name": "Rhonda Church"  },
  {    "id": 4,    "name": "Georgina Hazel"  }
]
``` 
You are expected to design any other required models and routes for your API.
### Evaluation Criteria
- **Java** best practices
- Completeness: did you complete the features? yes
- Correctness: does the functionality act in sensible, thought-out ways? yes
- Maintainability: is it written in a clean, maintainable way? yes
- Testing: is the system adequately tested? Unit and Integration cases are pending
- Documentation: is the API well-documented
