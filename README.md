To start the application:
1. Clone the repo and open it in IntelliJ
2. Go to File -> Project Structure -> Project -> Project SDK and select:
    - Java SDK version (from 8 to 11 should work)
    - Project Language Level (8 - Lambdas, type annotations etc.)
3. Go to Run -> Edit Configuration and make sure Program arguments field is set to '--init-db'
3. Hit Play in Main file. The database will be initialized automatically.
