# Liulang Animal Adoption System (IDEA Run Package)

## Recommended environment
- JDK 8
- Maven 3.6+
- MySQL 5.7/8.0
- IntelliJ IDEA

## Run in IntelliJ IDEA
1. Extract this package.
2. Open the folder `liulang-animal-adoption-system` in IDEA.
3. Wait for Maven import to finish.
4. Create database: `liulang_animal_adoption`.
5. Import the included SQL file if needed.
6. Check database config in `src/main/resources/application.yml`.
7. Run the Spring Boot main class:
   `com.AdoptionSystemApplication`
8. Visit:
   `http://localhost:8081/adoption`

## Notes
- This package removes build output and IDEA cache files.
- If port 8081 is occupied, change the port in `application.yml`.
- Static images are kept in the package.
