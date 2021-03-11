import java.sql.*;
import java.util.Scanner;

public class MysqlParser {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306";

    static final String USER = "root";
    static final String PASSWORD = "root";


    protected static void create() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        System.out.print("Введите имя: ");
        String name = scan.nextLine();
        System.out.print("Введите фамилию: ");
        String surname = scan.nextLine();

        boolean resultSet = statement.execute("INSERT INTO `schema`.users VALUES (0,'"
                + name + "','" + surname + "');");
        statement.close();
        connection.close();
        return;
    }


    protected static void delete() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        System.out.print("Введите id записи: ");
        int id = scan.nextInt();

        boolean resultSet = statement.execute("DELETE FROM `schema`.users WHERE id = " + id + "; ");

        statement.close();
        connection.close();
        return;
    }


    protected static void show() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM `schema`.users;");

        System.out.println("id:\t\t\t\t\tname:\t\t\t\t\tsurname:");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            System.out.println(id + "\t\t\t\t" + name + "\t\t\t\t\t\t" + surname);
        }

        statement.close();
        connection.close();
    }


    protected static void update() throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите id записи: ");
        int id = scan.nextInt();
        System.out.println("1) Изменить имя\n2) Изменить фамилию");
        int choice = scan.nextInt();

        if (choice == 1){
            System.out.print("Введите новое мия: ");
            String name = scan.next();
            boolean resultSet = statement.execute("UPDATE `schema`.users SET name = '" + name
                    +"' WHERE id = '" + id + "';");
        } else {
            System.out.print("Введите новое фамилию: ");
            String surname = scan.next();
            boolean resultSet = statement.execute("UPDATE `schema`.users SET surname = '" + surname
                    +"' WHERE id = '" + id + "';");
        }

        statement.close();
        connection.close();


    }

}
