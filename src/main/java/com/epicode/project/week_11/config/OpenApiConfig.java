package com.epicode.project.week_11.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Book's Catalogue",
                description = "" +
                        "Spring-Boot application to manage relationships between Books, Authors and Categories with Logins and Authorizations)",
                contact = @Contact(
                        name = "JCTS",
                        url = "https://github.com/JacopoColleluori?tab=repositories",
                        email = "jacolle99@gmail.com"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/LupoX1/tournament/blob/daniele/LICENSE%22")),
                        servers = @Server(url = "http://localhost:8080")
                )
                        public class OpenApiConfig{
                }
