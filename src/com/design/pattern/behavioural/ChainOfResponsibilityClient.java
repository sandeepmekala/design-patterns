package com.design.pattern.behavioural;

// Handler interface
interface Handler {
    void setNext(Handler nextHandler);
    void handleRequest(String request);
}

// Abstract Handler
abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Concrete Handler 1
class AuthenticationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if ("AUTH".equals(request)) {
            System.out.println("Authentication Handler: Request processed.");
        } else {
            System.out.println("Authentication Handler: Passing request.");
            super.handleRequest(request);
        }
    }
}

// Concrete Handler 2
class AuthorizationHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if ("AUTHZ".equals(request)) {
            System.out.println("Authorization Handler: Request processed.");
        } else {
            System.out.println("Authorization Handler: Passing request.");
            super.handleRequest(request);
        }
    }
}

// Concrete Handler 3
class LoggingHandler extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("Logging Handler: Logging request - " + request);
        super.handleRequest(request);
    }
}

// Client
public class ChainOfResponsibilityClient {
    public static void main(String[] args) {
        // Creating handlers
        Handler authHandler = new AuthenticationHandler();
        Handler authzHandler = new AuthorizationHandler();
        Handler logHandler = new LoggingHandler();

        // Setting up the chain
        authHandler.setNext(authzHandler);
        authzHandler.setNext(logHandler);

        // Sending requests
        System.out.println("Processing 'AUTH' request:");
        authHandler.handleRequest("AUTH");
        
        System.out.println("\nProcessing 'AUTHZ' request:");
        authHandler.handleRequest("AUTHZ");
        
        System.out.println("\nProcessing 'LOG' request:");
        authHandler.handleRequest("LOG");
    }
}
