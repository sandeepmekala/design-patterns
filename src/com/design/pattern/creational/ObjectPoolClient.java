package com.design.pattern.creational;

import java.util.ArrayList;
import java.util.List;

class DBConnection {
    public void connect() {
        System.out.println("Connected to DB.");
    }
}

class DBConnectionPoolManager {
    private static DBConnectionPoolManager instance;
    private List<DBConnection> freeConnections;
    private List<DBConnection> inUseConnections;
    private int initialPoolSize = 3;
    private int maxPoolSize = 6;

    private DBConnectionPoolManager() {
        freeConnections = new ArrayList<>();
        inUseConnections = new ArrayList<>();
        for (int i = 0; i < initialPoolSize; i++) {
            freeConnections.add(new DBConnection());
        }
    }

    public static synchronized DBConnectionPoolManager getInstance() {
        if (instance == null) {
            synchronized (DBConnectionPoolManager.class) {
                if (instance == null) {
                    instance = new DBConnectionPoolManager();
                }
            }
        }
        return instance;
    }

    public synchronized DBConnection getDBConnection() {
        if (!freeConnections.isEmpty()) {
            DBConnection connection = freeConnections.remove(freeConnections.size() - 1);
            inUseConnections.add(connection);
            return connection;
        } else if (inUseConnections.size() < maxPoolSize) {
            DBConnection connection = new DBConnection();
            inUseConnections.add(connection);
            return connection;
        }
        return null;
    }

    public synchronized void releaseDBConnection(DBConnection connection) {
        if (connection != null && inUseConnections.remove(connection)) {
            freeConnections.add(connection);
        }
    }
}

public class ObjectPoolClient {
    public static void main(String[] args) {
        // Get the singleton instance of DBConnectionPoolManager
        DBConnectionPoolManager poolManager = DBConnectionPoolManager.getInstance();

        // Request DB connections
        DBConnection connection1 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 1: " + connection1);

        DBConnection connection2 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 2: " + connection2);

        DBConnection connection3 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 3: " + connection3);

        // Attempt to acquire more connections than allowed
        DBConnection connection4 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 4: " + connection4);

        // Release a connection back to the pool
        poolManager.releaseDBConnection(connection1);
        System.out.println("Released Connection 1 back to the pool.");

        // Reacquire a connection after releasing
        DBConnection connection5 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 5: " + connection5);

        // Attempt to acquire beyond maximum pool size
        DBConnection connection6 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 6: " + connection6);

        DBConnection connection7 = poolManager.getDBConnection();
        System.out.println("Acquired Connection 7: " + connection7);
    }
}
