# Hibernate course project

> Console application

![workflow](https://img.shields.io/badge/MySQL-8.0.26-brightgreen?logo=mysql)
![workflow](https://img.shields.io/badge/MySQL-8.0.26-brightgreen?logo=mysql)
![workflow](https://img.shields.io/badge/Java-17-brightgreen?logo=java)
![workflow](https://img.shields.io/badge/Log4J-1.2.17-brightgreen)

#### Requirements

1. Design a database according to the schema and implement relationships between entities (annotated classes) using
   hibernate

![schema](db_schema.jpg)

3. Self-define data types in tables. Table "user" - the user of the service. The user can have roles "USER", "ADMIN", "
   SUPER_ADMIN". Roles should be stored in the "user_role" table. "profile" - user profile, "service" - services used by
   users, "user_services" - a related table for "user" and "service", "incident" - an incident that occurred, a service
   breakdown (a ticket that a technical specialist must answer)
4. Relationships between hibernate entities and DB tables:
    - one "user" to one "profile";
    - one "user" to many "incident";
    - one "user" to one "user_role";
    - many "user" to many "service" (each user can use many services and at the same time each service can have many
      subscribed users)
5. When starting the application, carry out authorization with the input of username and password, the application
   searches the database, if there is a match, then we carry out user authorization
6. Receiving requests can be organized through the console, requests for which the application must respond:

| Request                                        | Description                                                                                 | Access                 |
|------------------------------------------------|---------------------------------------------------------------------------------------------|------------------------|
| fetch_all_users                                | display in the console a list of all users with all dependencies                            | «ADMIN», «SUPER_ADMIN» |
| fetch_all_incidents fetch_all_active_incidents | display in the console a list of all incidents and corresponding users without dependencies | «ADMIN», «SUPER_ADMIN» |
| fetch_user_by_{id}                             | display in the user console by id with all dependencies, {id} — "id" parameter              | «ADMIN», «SUPER_ADMIN» |



