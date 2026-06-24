# BFHL API – Complete A-to-Z Guide

---

## STEP 0 — Prerequisites (install these first)

| Tool | Download link | Why needed |
|------|--------------|------------|
| Java 17 JDK | https://adoptium.net | Run Java code |
| Maven | https://maven.apache.org/download.cgi | Build the project |
| IntelliJ IDEA (Community, free) | https://www.jetbrains.com/idea/download | Write code easily |
| Git | https://git-scm.com/downloads | Push code to GitHub |
| Postman | https://www.postman.com/downloads | Test your API |

After installing Java, open a terminal and verify:
```
java -version       ← should show "17"
mvn -version        ← should show Maven version
git --version       ← should show git version
```

---

## STEP 1 — Put your details in the code

Open this file:
```
src/main/java/com/chitkara/bfhl/service/BfhlServiceImpl.java
```

Find these 4 lines at the top and **change them to YOUR details**:

```java
private static final String FULL_NAME   = "john_doe";       // ← your name, lowercase, underscore
private static final String DOB         = "17091999";        // ← your DOB ddmmyyyy
private static final String EMAIL       = "john@xyz.com";    // ← your college email
private static final String ROLL_NUMBER = "ABCD123";         // ← your roll number
```

Example — if your name is Rahul Sharma, born 05 March 2003, roll no 22BCE1234:
```java
private static final String FULL_NAME   = "rahul_sharma";
private static final String DOB         = "05032003";
private static final String EMAIL       = "rahul.sharma@chitkara.edu.in";
private static final String ROLL_NUMBER = "22BCE1234";
```

---

## STEP 2 — Run it locally

Open terminal inside the `bfhl` folder and run:

```bash
mvn clean install       ← downloads all dependencies (first time takes ~2 min)
mvn spring-boot:run     ← starts the server on port 8080
```

You should see:
```
Tomcat started on port(s): 8080
Started BfhlApplication
```

---

## STEP 3 — Test locally with Postman

1. Open Postman
2. Set method to **POST**
3. URL: `http://localhost:8080/bfhl`
4. Go to **Body → raw → JSON**
5. Paste this:
```json
{
  "data": ["a", "1", "334", "4", "R", "$"]
}
```
6. Click **Send**

Expected response:
```json
{
  "is_success": true,
  "user_id": "rahul_sharma_05032003",
  "email": "rahul.sharma@chitkara.edu.in",
  "roll_number": "22BCE1234",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

Also test Example B and C from the question paper.

---

## STEP 4 — Run the test cases

```bash
mvn test
```

You should see:
```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

---

## STEP 5 — Push to GitHub

1. Go to https://github.com → Sign in → Click "New repository"
2. Name it `bfhl-api` → Keep it **Public** → Click "Create repository"
3. In your terminal (inside the bfhl folder):

```bash
git init
git add .
git commit -m "Initial BFHL API"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/bfhl-api.git
git push -u origin main
```

---

## STEP 6 — Deploy on Render (Free)

1. Go to https://render.com → Sign up with GitHub
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub account → Select the `bfhl-api` repo
4. Fill in these settings:

| Field | Value |
|-------|-------|
| Name | bfhl-api |
| Environment | Java |
| Build Command | `mvn clean package -DskipTests` |
| Start Command | `java -jar target/bfhl-0.0.1-SNAPSHOT.jar` |

5. Click **"Create Web Service"**
6. Wait ~3-5 minutes for the build to finish
7. You will get a URL like: `https://bfhl-api.onrender.com`

---

## STEP 7 — Test the live API

In Postman:
- URL: `https://bfhl-api.onrender.com/bfhl`
- Method: POST
- Same body as Step 3

Make sure you get the correct response.

---

## STEP 8 — Submit

Submit this URL in the form:
```
https://bfhl-api.onrender.com/bfhl
```

---

## Project Structure (for reference)

```
bfhl/
├── pom.xml                                          ← Maven config (dependencies)
├── render.yaml                                      ← Render hosting config
└── src/
    ├── main/
    │   ├── java/com/chitkara/bfhl/
    │   │   ├── BfhlApplication.java                 ← Main entry point
    │   │   ├── controller/
    │   │   │   └── BfhlController.java              ← POST /bfhl endpoint
    │   │   ├── dto/
    │   │   │   ├── BfhlRequest.java                 ← Input JSON structure
    │   │   │   └── BfhlResponse.java                ← Output JSON structure
    │   │   ├── service/
    │   │   │   ├── BfhlService.java                 ← Interface (required!)
    │   │   │   └── BfhlServiceImpl.java             ← All the logic lives here
    │   │   └── exception/
    │   │       └── GlobalExceptionHandler.java      ← Handles all errors
    │   └── resources/
    │       └── application.properties               ← Server config
    └── test/
        └── java/com/chitkara/bfhl/
            └── BfhlServiceTest.java                 ← 5 test cases
```

---

## Common Errors and Fixes

| Error | Fix |
|-------|-----|
| `Port 8080 already in use` | Kill the old process or change port in application.properties |
| `BUILD FAILURE` on Render | Make sure Java version is set to 17 in Render settings |
| `404 Not Found` | Make sure you're hitting `/bfhl` not `/` |
| `500 Internal Server Error` | Check your data array is not null |
| Render URL not working | First request after idle takes ~30s (free tier spins down) |
