import java.sql.*;
import java.util.Scanner;

public class Demo {


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306";

    static final String USER = "root";
    static final String PASSWORD = "root";


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scan = new Scanner(System.in);
        Class.forName(JDBC_DRIVER);

        System.out.println("Соединение с таблией...\n");
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
        while (true) {
            System.out.println("\n\tCписок команд:\n1) Создать запись\n2) Удалить запись\n" +
                    "3) Изменить запись\n4) Просмотреть все записи\n5) Выход");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    MysqlParser.create();
                    break;
                case 2:
                    MysqlParser.delete();
                    break;
                case 3:
                    MysqlParser.update();
                    break;
                case 4:
                    MysqlParser.show();
                    break;
                default:
                    break;

            }
            if (choice == 5){ break;}
        }


        statement.close();
        connection.close();
    }

}
