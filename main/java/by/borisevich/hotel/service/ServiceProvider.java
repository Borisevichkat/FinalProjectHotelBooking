package by.borisevich.hotel.service;

import by.borisevich.hotel.service.impl.UserServiceImpl;

public class ServiceProvider {

    private static final ServiceProvider instance = new ServiceProvider();

    private final UserService userService = new UserServiceImpl();
    private ServiceProvider() {
    }

    public static ServiceProvider getInstance() {
        return instance;
    }
    public UserService getUserService(){
        return userService;
    }
}
