# chwitter
A twitter-like application that demonstrates some web application development techniques. An anonymous user can register
to be a registered user. A confirmation email will be sent to the user (using Mailgun SMTP server). Once confirmed, user
can log in to the system and can create "chweet" which can be text message or image or both. One user can follow another
user also. The application is developed using Spring Boot as the backend with JSP and JQuery as the frontend.

The project utilizes Maven's multi-module feature to link the frontend build to the backend build. The frontend module
uses frontend-maven-plugin to execute NPM which kicks Webpack to bundle all the frontend resources. It also minimizes
all CSS and JavaScript resources in PROD environment. Next, Webpack will put those resources into the
directory compatible to Spring Boot convention. It then follows by the standard backend build.

For demonstration, I deployed the application to Heroku: https://chi-witter.herokuapp.com/