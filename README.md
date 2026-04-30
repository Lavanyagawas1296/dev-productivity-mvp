# Developer Productivity MVP

A full-stack MVP that transforms raw engineering metrics into meaningful developer productivity insights.
The application combines a React frontend with a Spring Boot backend to surface metric interpretations and actionable next steps for engineering teams.

## Features

- Display 5 key developer metrics: Lead Time, Cycle Time, PR Throughput, Deployment Frequency, and Bug Rate
- Provide clear interpretation of developer performance metrics
- Suggest actionable next steps based on metric health

## Tech Stack

- **Frontend:** React
- **Backend:** Spring Boot
- **Data:** Mock / hardcoded data

## How to Run the Project

### Backend

1. Open a terminal in the project root:

   ```bash
   cd dev-productivity
   ```

2. Start the Spring Boot backend using the Maven wrapper:

   ```bash
   mvnw.cmd spring-boot:run
   ```

3. The backend runs on:

   ```text
   http://localhost:8080
   ```

### Frontend

1. Open a terminal in the frontend folder:

   ```bash
   cd dev-productivity/frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the React app:

   ```bash
   npm run dev
   ```

4. The frontend runs on:

   ```text
   http://localhost:3001
   ```

## API Endpoint

```http
GET /api/metrics?developerId=DEV-001&month=2026-04
```

Example local URL:

```text
http://localhost:8080/api/metrics?developerId=DEV-001&month=2026-04
```

## Project Structure

```text
dev-productivity/
├── frontend/
│   ├── src/
│   ├── index.html
│   └── package.json
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/lavanya/dev_productivity/
│   │   └── resources/
│   └── test/
├── pom.xml
├── mvnw.cmd
└── README.md
```

## Future Improvements

- Add a database to store developer metrics over time
- Add trend charts for month-over-month comparison
- Support authentication and role-based access
- Add filters by team and metric health status
- Expand recommendations with richer rule-based or AI-assisted insights
