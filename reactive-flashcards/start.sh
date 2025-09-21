mvn clean package -DskipTests
java -jar target/reactive-flashcards-0.0.1-SNAPSHOT.jar
#java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 -jar target/reactive-flashcards-0.0.1-SNAPSHOT.jar
