package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Arthur", "Morgan", (byte) 36);
        userService.saveUser("Geralt", "of Rivia", (byte) 57);
        userService.saveUser("Carl", "Johnson", (byte) 24);
        userService.saveUser("Vladimir", "Putin", (byte) 71);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
//        userService.removeUserById(1);
    }
}
