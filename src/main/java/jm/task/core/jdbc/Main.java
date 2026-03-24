package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Ivan","Ivanov",(byte) 76);
        System.out.println("User с именем — Ivan добавлен в базу данных ");
        service.saveUser("Petr","Petrov",(byte) 24);
        System.out.println("User с именем — Petr добавлен в базу данных ");
        service.saveUser("Vova","Vovan",(byte) 45);
        System.out.println("User с именем — Vova добавлен в базу данных ");
        service.saveUser("Dima","Dimon",(byte) 34);
        System.out.println("User с именем — Dima добавлен в базу данных ");
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
