### Parakeet Backend

Parakeet is a basic Twitter clone. Development environment can be started with
```docker compose up```, which will start a Postgres DB and the Spring Boot app.
Dockerfile itself is for building the image to be used in Kubernetes which will
be pushed to Amazon ECR and used by Kubernetes in an EKS instance. Tests also
use a Test Container as a Singleton, so they need Docker too. Debugging can be done
using Remote JVM Debug. Building the project will trigger a live reload.