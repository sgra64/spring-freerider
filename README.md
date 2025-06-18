# 2. Spring *Initializr*

*Spring* uses a web-site
[*"initializr"*](https://start.spring.io)
to create initial project files.

Use following input for this project:

- group id: `freerider`

- artifact: `application`

- click the button at the upper right: *"ADD DEPENDENCIES"* and add:

  - *H2 Database* and

  - *MySQL Driver*.

Compare to the filled in form below and download initial files (click:
*"GENERATE"* ), which will download files in *"application.zip"*.

<img src="https://raw.githubusercontent.com/sgra64/spring-freerider/refs/heads/markup/img/spring-initializr.png" width="1000"/>

Unzip *"application.zip"* into a temporary directory *"tmp"*:

```sh
# unzip 'application.zip' into a temporary directory 'tmp'
unzip -d tmp application.zip

ls -la tmp/application                      # show unzipped content
```

Generated project content downloaded from *initializr*:

```
total 34
drwxr-xr-x 1     0 Jun 18 17:43 ./
drwxr-xr-x 1     0 Jun 18 17:53 ../
-rw-r--r-- 1    38 Jun 18 17:43 .gitattributes
-rw-r--r-- 1   394 Jun 18 17:43 .gitignore
drwxr-xr-x 1     0 Jun 18 17:43 .mvn/
-rw-r--r-- 1   991 Jun 18 17:43 HELP.md
-rwxr-xr-x 1 10665 Jun 18 17:43 mvnw*
-rw-r--r-- 1  6912 Jun 18 17:43 mvnw.cmd
-rw-r--r-- 1  1658 Jun 18 17:43 pom.xml
drwxr-xr-x 1     0 Jun 18 17:43 src/
```

Move *pom.xml* and *src* to the project directory and remove *tmp* :

```sh
# move 'pom.xml' and 'src' to the project directory
mv tmp/application/pom.xml tmp/application/src .

rm -rf tmp                                  # remove 'tmp'
```

Content of the initial *Spring Boot* project has arrived in the project directory:

- `pom.xml` - project build file for *maven*.

- `src` - folder with initial source code:

    ```sh
    find src                                # showw 'src' content
    ```

    ```
    src
    src/main
    src/main/java
    src/main/java/freerider
    src/main/java/freerider/application
    src/main/java/freerider/application/Application.java
    src/main/resources
    src/main/resources/application.properties
    src/test
    src/test/java
    src/test/java/freerider
    src/test/java/freerider/application
    src/test/java/freerider/application/ApplicationTests.java
    ```

Build the project:

```sh
mvn compile                                 # compile project

mvn -q spring-boot:run                      # run project
```

Running the application shows the *"Spring"* banner:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.5.0)

2025-06-18T18:13:02.501+02:00  INFO 18736 --- [application] [           main] freerider.application.Application        : Starting Application using Java 21 with PID 18736 (C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider\target\classes started by svgr2 in C:\Sven1\svgr2\tmp\svgr\workspaces\2-se\spring-freerider)
2025-06-18T18:13:02.517+02:00  INFO 18736 --- [application] [           main] freerider.application.Application        : No active profile set, falling back to1 default profile: "default"
2025-06-18T18:13:03.103+02:00  INFO 18736 --- [application] [           main] freerider.application.Application        : Started Application in 1.173 seconds (process running for 1.607)
```

Package the application and run the packaged *.jar* file in the *target* folder:

```sh
mvn package                                 # package the application to '.jar'

ls -la target                               # show packaged '.jar' in 'target'

java -jar target/application-0.0.1-SNAPSHOT.jar     # run packaged '.jar'
```

Set-up *CLASSPATH* and run program with *java* :

```sh
source .env/env.sh                          # source project environment
```

Output shows a long *CLASSPATH* resulting from dependencies in *pom.xml* :

```
project environment has been set with:
 - created file: .classpath
 - CLASSPATH:
   - target/classes                         <-- compiled classes
   - accessors-smart-2.5.2.jar
   - android-json-0.0.20131108.vaadin1.jar
   - apiguardian-api-1.1.2.jar
   - asm-9.7.1.jar
   - assertj-core-3.27.3.jar
   - awaitility-4.3.0.jar
   - byte-buddy-1.17.5.jar
   - byte-buddy-agent-1.17.5.jar
   - h2-2.3.232.jar                         <-- h2 database dependency
   - hamcrest-3.0.jar
   - jakarta.activation-api-2.1.3.jar
   - jakarta.annotation-api-2.1.1.jar
   - jakarta.xml.bind-api-4.0.2.jar
   - jsonassert-1.5.3.jar
   - json-path-2.9.0.jar
   - json-smart-2.5.2.jar
   - jul-to-slf4j-2.0.17.jar
   - junit-jupiter-5.12.2.jar               <-- junit dependencies
   - junit-jupiter-api-5.12.2.jar
   - junit-jupiter-engine-5.12.2.jar
   - junit-jupiter-params-5.12.2.jar
   - junit-platform-commons-1.12.2.jar
   - junit-platform-engine-1.12.2.jar
   - log4j-api-2.24.3.jar                   <-- logging dependencies
   - log4j-to-slf4j-2.24.3.jar
   - logback-classic-1.5.18.jar
   - logback-core-1.5.18.jar
   - micrometer-commons-1.15.0.jar
   - micrometer-observation-1.15.0.jar
   - mockito-core-5.17.0.jar
   - mockito-junit-jupiter-5.17.0.jar
   - mysql-connector-j-9.2.0.jar            <-- mysql database dependency
   - objenesis-3.3.jar
   - opentest4j-1.3.0.jar
   - slf4j-api-2.0.17.jar
   - snakeyaml-2.4.jar
   - spring-aop-6.2.7.jar                   <-- Spring Boot dependencies
   - spring-beans-6.2.7.jar
   - spring-boot-3.5.0.jar
   - spring-boot-autoconfigure-3.5.0.jar
   - spring-boot-starter-3.5.0.jar
   - spring-boot-starter-logging-3.5.0.jar
   - spring-boot-starter-test-3.5.0.jar
   - spring-boot-test-3.5.0.jar
   - spring-boot-test-autoconfigure-3.5.0.jar
   - spring-context-6.2.7.jar
   - spring-core-6.2.7.jar
   - spring-expression-6.2.7.jar
   - spring-jcl-6.2.7.jar
   - spring-test-6.2.7.jar
   - xmlunit-core-2.10.1.jar
```

List *.jar* files on *CLASSPATH*:

```sh
# list '.jar' files on 'CLASSPATH' (function '' was created with environment)
show_classpath
```

Output shows 52 *.jar* files, which also have been packaged to the final
*application-0.0.1-SNAPSHOT.jar* (16 MB).

With CLASSPATH set, the application can run with java:

```sh
# run application with implicit CLASSPATH
java freerider.application.Application
```
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
...
```

Run tests:

```sh
mvn test                                    # run JUnit tests
```

Output:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running freerider.application.ApplicationTests
20:48:24.337 [main] INFO org.springframework.test.context.support.AnnotationConfigContextLoaderUtils -- Could not detect default configuration classes for test class [freerider.application.ApplicationTests]: ApplicationTests does not declare any static, non-private, non-final, nested classes annotated with @Configuration. ...
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
[INFO]
[INFO] Results:
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  6.597 s
[INFO] Finished at: 2025-06-18T20:48:27+02:00
[INFO] ------------------------------------------------------------------------
```

If everything is working, commit the *initializr* status of the project.

```sh
git add pom.xml src                         # stage 'pom.xml' and 'src' from initializr
git commit -m "add from initializr: pom.xml, src"
git tag initializr                          # tag commit as the one from initializr

git log --oneline                           # show commit history on 'main' branch
```

```
bf216aa (HEAD -> main, tag: initializr) add from initializr: pom.xml, src
8c8de5f (origin/main) add .gitignore, .gitmodules
c9f861e (tag: root) root commit (empty)
```

Push commits to your remote repository.
