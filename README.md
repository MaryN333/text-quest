# Text Quest Game (Servlets + JSP + Tomcat)

A browser-based text adventure game built using Java Servlets, JSP, JSTL, and Apache Tomcat 9. The project demonstrates a simple MVC-like architecture without Spring.

---
## Features
- Story quests loaded from JSON files
- Session-based game state
- Win / Lose outcomes
- Game statistics (games played, wins, losses)
- Player name persistence
- Switch player — change name, reset statistics for new player
- Game history tracking
- Restart current quest or return to main menu
- Filter-protected routes
- Logging with Logback
- Test coverage (JUnit, Mockito)

## Technologies

- Java 11+
- Java Servlets (javax, Tomcat 9)
- JSP + JSTL
- Apache Tomcat 9
- Maven
- Jackson (JSON parsing)
---

## Project Structure
```text
text-quest/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── cz/wz/marysidy/quest/
│   │   │       ├── controller/
│   │   │       │   ├── GameServlet.java
│   │   │       │   ├── HomeServlet.java
│   │   │       │   ├── ResetServlet.java
│   │   │       │   ├── ResultServlet.java
│   │   │       │   └── StartServlet.java
│   │   │       ├── filter/
│   │   │       │   ├── GameFilter.java
│   │   │       │   └── StartFilter.java
│   │   │       ├── model/
│   │   │       │   ├── Option.java
│   │   │       │   ├── Quest.java
│   │   │       │   ├── ResultType.java
│   │   │       │   └── Step.java
│   │   │       ├── repository/
│   │   │       │   └── QuestRepository.java
│   │   │       └── service/
│   │   │           └── GameService.java
│   │   │       
│   │   ├── resources/
│   │   │   ├── forest.json
│   │   │   ├── logback.xml
│   │   │   ├── negotiation.json
│   │   │   └── prague-night.json
│   │   └── webapp/
│   │       ├── css/
│   │       │   └── style.css
│   │       └── WEB-INF/
│   │           └── views/
│   │               ├── game.jsp
│   │               ├── home.jsp
│   │               ├── result.jsp
│   │               └── start.jsp
│   └── test/
│       └── java/
│           └── cz/wz/marysidy/quest/
│               ├── filter/
│               │   ├── GameFilterTest.java
│               │   └── StartFilterTest.java
│               ├── repository/
│               │   └── QuestRepositoryTest.java
│               └── service/
│                   └── GameServiceTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## 🚀 How to Run

### Prerequisites
- Java 11+
- Maven 3.x
- Tomcat 9 (or use embedded Maven plugin)

### Option 1: IntelliJ IDEA
**Ultimate version:**
- Built-in Tomcat support — no additional installation needed

**Community version:**
- Requires manual Tomcat configuration (see Option 3 for installation)
- Or use Maven plugin (see Option 2)
1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/text-quest.git
   cd text-quest
   ```
2. **Open in IntelliJ IDEA**

   File → Open → Select project folder

3. **Configure Tomcat 9**

    - Run → Edit Configurations → + → Tomcat Server → Local

    - Server tab: Set HTTP port to 8080

    - Deployment tab: + → Artifact → text-quest:war exploded

    - Application context: /quest

4. **Run**

    - Click the green triangle (Run)

    - Open: http://localhost:8080/quest/home


### Option 2: Run with Maven (without installing Tomcat)
1. Add Tomcat plugin to `pom.xml`:

You can run the project using a Maven plugin instead of installing Tomcat.

Add the following plugin to your `pom.xml`:

```xml
<plugin>
   <groupId>org.apache.tomcat.maven</groupId>
   <artifactId>tomcat7-maven-plugin</artifactId>
   <version>2.2</version>
   <configuration>
      <port>8080</port>
      <path>/quest</path>
   </configuration>
</plugin>
   ```
Note: This plugin is not included in the project by default.
It is shown here as an optional way to run the application.
It also works with Tomcat 9.

Then run:
```bash
mvn clean tomcat7:run
# Then open: http://localhost:8080/quest/home
```

### Option 3: Manual WAR Deployment
1. Download Tomcat 9 from https://tomcat.apache.org/
2. Extract to a folder
   - **Mac:** e.g., `~/Development/tomcat9`
   - **Windows:** `C:\tomcat9`
3. Build WAR:
```bash
# Build WAR
mvn clean package


# Deploy to Tomcat
# - Mac:
cp target/text-quest-1.0-SNAPSHOT.war ~/Development/tomcat9/webapps/quest.war
# - Windows:
copy target\text-quest-1.0-SNAPSHOT.war C:\tomcat9\webapps\quest.war

# Start Tomcat
# - Mac:
~/Development/tomcat9/bin/startup.sh
# - Windows:
C:\tomcat9\bin\startup.bat

# Open: http://localhost:8080/quest/home

# Stop Tomcat
# - Mac:
~/Development/tomcat9/bin/shutdown.sh
# - Windows:
C:\tomcat9\bin\shutdown.bat
```
