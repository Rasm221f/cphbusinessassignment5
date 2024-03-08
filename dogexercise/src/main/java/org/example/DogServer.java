package org.example;

import io.javalin.Javalin;
import io.javalin.apibuilder.ApiBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.example.Dog;

public class DogServer {
    private static Map<Integer, Dog> dogs = new HashMap<>();
    private static AtomicInteger idCounter = new AtomicInteger();

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7007);

        // GET all dogs
        app.get("/api/dogs", ctx -> ctx.json(dogs.values()));

        // GET a specific dog by ID
        app.get("/api/dogs/:id", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Dog dog = dogs.get(id);
            if (dog != null) {
                ctx.json(dog);
            } else {
                ctx.status(404).result("Dog not found");
            }
        });
        // GET a dog based on breed
        // Query Parameters: Handle query parameters in a request to implement more flexible GET operations. For example, filtering dogs by breed.
        app.get("/api/dogs", ctx -> {
            // api/dogs?breed=Schaeffer
            String breedFilter = ctx.queryParam("breed");
            if (breedFilter != null) {
                ctx.json(dogs.values().stream().filter(dog -> dog.getBreed().equals(breedFilter)).collect(Collectors.toList()));
            } else {
                ctx.json(dogs.values());
            }
        });

        //Path Parameters for Additional Operations:
        // Use path parameters to implement additional operations
        app.patch("/api/dogs/:id/age", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Dog dog = dogs.get(id);
            if (dog != null) {
                dog.setAge(dog.getAge() + 1);
                ctx.json(dog);
            } else {
                ctx.status(404).result("Dog not found");
            }
        });

        // POST a new dog
        //Parsing Request Body: When creating or updating a Dog instance, we parse the incoming request's body to a Dog object using ctx.bodyAsClass(Dog.class);.
        // This is an example of request parsing.
        app.post("/api/dogs", ctx -> {
            Dog dog = ctx.bodyAsClass(Dog.class);
            int id = idCounter.incrementAndGet();
            dog.setId(id);
            dogs.put(id, dog);
            //Setting Response Status and Body: In several places, we manipulate the response by setting its status code and body.
            //For example, when a dog is successfully created, we set the response status to 201 and return the created Dog object as JSON.
            ctx.status(201).json(dog);
        });

        // PUT (update) a dog by ID
        app.put("/api/dogs/:id", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Dog dogToUpdate = ctx.bodyAsClass(Dog.class);
            if (dogs.containsKey(id)) {
                dogToUpdate.setId(id);
                dogs.put(id, dogToUpdate);
                ctx.status(200).json(dogToUpdate);
            } else {
                //Error Handling: When a requested Dog is not found in the GET or DELETE operation,
                //we set the response status to 404 and provide a message indicating that the dog was not found.
                ctx.status(404).result("Dog not found");
            }
        });

        // DELETE a dog by ID
        app.delete("/api/dogs/:id", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (dogs.remove(id) != null) {
                ctx.status(200).result("Dog deleted");
            } else {
                ctx.status(404).result("Dog not found");
            }
        });
    }
}
