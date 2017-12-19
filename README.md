# Springular-One
Spring 4.x + Angular JS 1.6.X seed starter maven project. One in Springular-One denotes the use of Angular 1.x as my preferred UI framework(as I am still coming to terms with Angular IO :D)

A pretty straight-forward but extremely handy seed starter project for Spring MVC + Angular based web applications using Java 8 and developed on Eclipse. The project is a result of the requirements coming out of a recent project I was authoring. You'll get the following features out of the box,
- Spring 4 MVC
- Angular 1.6.X
- Spring Security enabled with In-Memory, LDAP, Custom authentication supported.
- Spring Data JPA enabled with both Spring repository and Native queries support out of the box.
- Spring JUnit Test cases.
- Load of dymanic properites file based on server environment.
- Email Integration with Freemarker templates.
- Logging using Log4j.
- Responsive UI using Bootstrap 4.

What I have tried to do here is to collate a lot of best practises and some sensible defaults for any typical Java based web application. As with any other project and developer, I have done extensive Googling and taken multiple references from various forumns to put it all together. Happy developing!!

A note when you hit the login screen upon deploying,
Use any username and password as password when you hit the login screen. The same can be customized in /src/main/java/com/mycomp/dashboard/security/CustomUserDetailsService.java

