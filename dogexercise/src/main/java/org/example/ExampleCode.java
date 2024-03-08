package org.example;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;


public class ExampleCode {

    //Manage access:

        app.beforeMatched(ctx ->
    {
        var userRole = getUserRole(ctx); // some user defined function that returns a user role
        if (!ctx.routeRoles().contains(userRole)) { // routeRoles are provided through the Context interface
            throw new UnauthorizedResponse(); // request will have to be explicitly stopped by throwing an exception
        }
    });
}


// Check for role
app.get("/public", ctx -> ctx.result("Hello public"), Role.OPEN);
app.get("/private", ctx -> ctx.result("Hello private"), Role.LOGGED_IN);