# K+N Wallets
K+N wallets RESTful Spring-Boot api and React SPA

## Endpoints `/wallets`
- GET  `/`: get all wallets data
- GET  `/{id}`: check balance (show, get all wallet data)
- POST `/{id}`: create wallet
- POST `/{id}/deposit`: perform deposit on given wallet
- POST `/{id}/withdraw`: perform withdraw on given wallet


## Project Specs
- db
  - postgresql

- gui
  - node v16.14.2
  - npm v8.5.0
  - React, Sass, Material UI

- api
  - JDK v11
  - maven v3.6
  - Spring: Web, DataJPA, Validation
  - Junit 5, Zonky, RestAssured

## Running
1. install project locally, both backend and frontend
2. generate images (could use ./build.sh)
3. run `docker-compose up` in the project base

Note: app is configured to load database on startup

## Improvements
- gui
  - User notification (show alert) on result from server-side calls
  - More concise components
  - Responsive desing
- api
  - Exception handling generating a clear consumable message to frontend
  - More test cases
