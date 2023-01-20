# Blog-App
Blog App using; Spring Boot &amp; React &amp; JWT &amp; PostgreSQL 

## Backend side for blog project. You can reach out below frontend side of this project
https://github.com/feyzabecerr/Blog-AppFrontend

### Endpoints 
* Register
`POST /auth/register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{
    "username": "user",
    "email": "user@gmail.com",
    "password": "password"
}`

* Log-in
 `POST /auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json

{
    "username": "user",
    "password": "password"
}`
