# Hibernate course project
> Console application

![workflow](https://img.shields.io/badge/MySQL-8.0.26-yellow?logo=mysql)
![workflow](https://img.shields.io/badge/MySQL-8.0.26-yellow?logo=mysql)
![workflow](https://img.shields.io/badge/Java-17-yellow?logo=java)
![workflow](https://img.shields.io/badge/Log4J-1.2.17-yellow)

#### Requirements
1. Design a database according to the schema and implement relationships between entities (annotated classes) using hibernate
   ![schema](db_schema.jpg)
2. Self-define data types in tables
3. Table "user" - the user of the service. The user can have roles "USER", "ADMIN", "SUPER_ADMIN". Roles should be stored in the "user_role" table. "profile" - user profile, "service" - services used by users, "user_services" - a related table for "user" and "service", "incident" - an incident that occurred, a service breakdown (a ticket that a technical specialist must answer)
4. Relationships between hibernate entities and DB tables:
   - one "user" to one "profile";
   - one "user" to many "incident";
   - one "user" to one "user_role";
   - many "user" to many "service" (each user can use many services and at the same time each service can have many subscribed users)
5. When starting the application, carry out authorization with the input of username and password, the application searches the database, if there is a match, then we carry out user authorization