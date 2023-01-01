package com.peaksoft;

import com.peaksoft.service.UserServiceImpl;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dilnaz",
                "Tolonbaeva", (byte) 16);
        userService.saveUser("Aizada",
                "Sultanova", (byte) 11);
        userService.saveUser("Aida",
                "Kubatova", (byte) 8);
        userService.saveUser("Baikal",
                "Asanov", (byte) 14);
        userService.getAllUsers();

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
