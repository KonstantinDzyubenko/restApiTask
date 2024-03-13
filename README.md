# Rest API Task

Практическое задание на стажировку по специальности Java Разработчик 

Приложение реализовано с использованием фреймворка Spring Boot и следующих технологий:
* Spring MVC
* Spring Security
* Spring Data
* Spring Validation
* Spring Cache + Caffeine
* Maven
* JUnit 5 + Mockito
* H2 Database

Данное приложение предоставляет Rest API для проксирования запросов на сайт https://jsonplaceholder.typicode.com/ 
по следующим эндпоинтам:
* `/api/posts/**` -> `https://jsonplaceholder.typicode.com/posts/**`
* `/api/users/**` -> `https://jsonplaceholder.typicode.com/users/**`
* `/api/albums/**` -> `https://jsonplaceholder.typicode.com/albums/**`

Также предусмотрен эндпоинт `/management/users` для добавления новых пользователей приложения. При добавлении новых
пользователей происходит их базовая валидация (логин должен быть уникальным, пароль и список ролей - непустыми) 

Приложение поддерживает ролевую модель доступа с базовой авторизацией со следующими ролями:
* `ROLE_APPLICATION_ADMIN` - имеет доступ к `/management/users`
* `ROLE_ADMIN` - имеет доступ к `/api/**`
* `ROLE_USERS_VIEWER` - имеет доступ к `GET /api/users/**`
* `ROLE_USERS_EDITOR` - имеет доступ к `POST/PUT/DELETE /api/users/**`
* `ROLE_POSTS_VIEWER` - имеет доступ к `GET /api/posts/**`
* `ROLE_POSTS_EDITOR` - имеет доступ к `POST/PUT/DELETE /api/posts/**`
* `ROLE_ALBUMS_VIEWER` - имеет доступ к `GET /api/albums/**`
* `ROLE_ALBUMS_EDITOR` - имеет доступ к `POST/PUT/DELETE /api/albums/**`

Приложение использует встроенные возможности фреймворка Spring Boot для ведения аудита действий 
и записи лога в файл spring.log

В приложении реализован inmemory cache для уменьшения числа запросов к https://jsonplaceholder.typicode.com/

Данные пользователей приложения (логины, зашифрованные пароли, список ролей) хранятся в базе данных. 
Текущая конфигурация базы данных предполагает ее создание и наполнение данными при запуске приложения для удобства 
проверки. Инициация базы данных происходит с добавлением 2-х пользователей: 
* user1/password1 - `ROLE_APPLICATION_ADMIN`
* user2/password2 - `ROLE_ADMIN`

Ключевой функционал покрыт юнит-тестами