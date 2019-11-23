package sample.myClass;

import sample.Controller.Controller;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DataBaseHandler extends Configs{
    //ПОДКЛЮЧЕНИЕ К БД

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        String connectionString = "jdbc:mysql://" + dbHost + ":" +
                dbPort + "/" + dbName + "?useSSL=false";
        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString,
                dbUser,dbPass);
        return dbConnection;
    }


    //ДОБАВЛЕНИЕ КЛИЕНТА
    public void addClient (Client client){
        String insert = "INSERT INTO "+ ConstClient.CLIENT_TABLE + "(" +
                ConstClient.CLIENTS_FIO +","+ ConstClient.CLIENTS_adress +","+
                ConstClient.CLIENTS_TELEPHONE +"," + ConstClient.CLIENTS_KOLVO + ")"+
                "VALUES(?,?,?,?)";
        //был запрос тупо в строке а теперь через метод мы его адресуем в БД
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,client.getFIO());
            prSt.setString(2,client.getTelephone());
            prSt.setString(3,client.getAdress());
            prSt.setInt(4,0);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //ДОБАВЛЕНИЕ КНИГИ
    public void addBook (Book book){
        String insert = "INSERT INTO "+ ConstBook.BOOK_TABLE + "(" +
                ConstBook.BOOK_AUTHOR +","+ ConstBook.BOOK_BOOKNAME +","+ ConstBook.BOOK_GODIZD +","+ ConstBook.BOOK_KOLVO +")"+
                "VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,book.getAuthor());
            prSt.setString(2,book.getNameBook());
            prSt.setString(3,book.getGodIzd());
            prSt.setInt(4,book.getKolvo());
            prSt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //ВХОД В СИСТЕМУ
    public ResultSet getWorker (Worker worker){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + ConstWorker.WORKER_TABLE + " WHERE " +
                ConstWorker.WORKER_FIO + "=? AND " + ConstWorker.WORKER_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,worker.getFio());
            prSt.setString(2,worker.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return resSet;
    }
//========================================================================================
    //ПРОСМОТР ОСТАТКОВ (по книгам)
    public ResultSet ostatkiView (Book book) {
        ResultSet resSet = null;
        String select = "SELECT " + ConstBook.BOOK_KOLVO + " FROM " + ConstBook.BOOK_TABLE +
                " WHERE " + ConstBook.BOOK_BOOKNAME + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,book.getNameBook());
             resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    public ResultSet ostatkiView2 (Book book) {
        ResultSet resSet = null;
        String select = "SELECT " + ConstBook.BOOK_KOLVO + " FROM " + ConstBook.BOOK_TABLE +
                " WHERE " + ConstBook.BOOK_BOOKNAME + " =? AND " + ConstBook.BOOK_GODIZD + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,book.getNameBook());
            prSt.setString(2,book.getGodIzd());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }

    //ПРОВЕРКА НА СУЩЕСТВОВАНИЕ КЛИЕНТА
    public ResultSet clientView (Client client) {
        ResultSet resSet = null;
        String select = "SELECT " + ConstClient.CLIENTS_FIO + " FROM " + ConstClient.CLIENT_TABLE +
                " WHERE " + ConstClient.CLIENTS_FIO + " =? AND " + ConstClient.CLIENTS_TELEPHONE + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,client.getFIO());
            prSt.setString(2,client.getTelephone());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
//===================================================================================================
    //ВЫДАЧА КНИГИ  минус по количество книг
    public void giveBook (Book book){
        String update = "UPDATE " + ConstBook.BOOK_TABLE + " SET " + ConstBook.BOOK_KOLVO +
                " = (" + ConstBook.BOOK_KOLVO + "-1) " +
                "WHERE " + ConstBook.BOOK_BOOKNAME + " =? AND " + ConstBook.BOOK_GODIZD + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,book.getNameBook());
            prSt.setString(2,book.getGodIzd());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //ВЫДАЧА КЛИЕНТУ плюс по клиенту
    public void takeClient (Client client){
        String update = "UPDATE " + ConstClient.CLIENT_TABLE + " SET " + ConstClient.CLIENTS_KOLVO + " = (" +
                ConstClient.CLIENTS_KOLVO + "+1)" + " WHERE " + ConstClient.CLIENTS_FIO +
                "=? AND " + ConstClient.CLIENTS_TELEPHONE + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,client.getFIO());
            prSt.setString(2,client.getTelephone());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
//=============================================================================================
    //ВЫДАЧА КНИГИ  плюс по количество книг
    public void returnBook (Book book){
        String update = "UPDATE " + ConstBook.BOOK_TABLE + " SET " + ConstBook.BOOK_KOLVO +
                " = (" + ConstBook.BOOK_KOLVO + "+1) " +
                "WHERE " + ConstBook.BOOK_BOOKNAME + " =? AND " + ConstBook.BOOK_GODIZD + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,book.getNameBook());
            prSt.setString(2,book.getGodIzd());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //ВЫДАЧА КЛИЕНТУ минус по клиенту
    public void returnClient (Client client){
        String update = "UPDATE " + ConstClient.CLIENT_TABLE + " SET " + ConstClient.CLIENTS_KOLVO + " = (" +
                ConstClient.CLIENTS_KOLVO + "-1)" + " WHERE " + ConstClient.CLIENTS_FIO +
                "=? AND " + ConstClient.CLIENTS_TELEPHONE + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(update);
            prSt.setString(1,client.getFIO());
            prSt.setString(2,client.getTelephone());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

//===================================================================
    //Добавление в таблицу RESULT (выдача)
    public void addResult(Book book, Client client, Operation operation){
        String insert = "INSERT INTO " + ConstResult.RESULT_TABLE + "(" + ConstResult.RESULT_OPERATION + "," +
                ConstResult.RESULT_FIO + "," + ConstResult.RESULT_TELEPHONE + "," +
                ConstResult.RESULT_BOOKNAME + "," + ConstResult.RESULT_GODIZD + "," + ConstResult.RESULT_DATEGIVE +
                "," + ConstResult.RESULT_DATERETURN + ")"+ "VALUES(?,?,?,?,?,?,?)";
        try {
            DateFormat df = new SimpleDateFormat("dd MMMMMMMM yyyy, HH:mm");
            GregorianCalendar calendar1 = new GregorianCalendar();
            GregorianCalendar calendar2 = new GregorianCalendar();
            calendar2.add(Calendar.MONTH,3);
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1,String.valueOf(operation));
            prSt.setString(2,client.getFIO());
            prSt.setString(3,client.getTelephone());
            prSt.setString(4,book.getNameBook());
            prSt.setString(5,book.getGodIzd());
            prSt.setString(6,df.format(calendar1.getTime()));
            if (operation.equals(Operation.Выдача)) {
                prSt.setString(7, df.format(calendar2.getTime()));
            } else {
                prSt.setString(7,"-");
            }
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

//================================================================================================

    //Вывод в txt - file

    public ResultSet addTxt (Client client){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + ConstResult.RESULT_TABLE + " WHERE " +
                ConstResult.RESULT_FIO + " =? AND " + ConstResult.RESULT_TELEPHONE + " =?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1,client.getFIO());
            prSt.setString(2,client.getTelephone());
            resSet = prSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                getDbConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  resSet;
    }

}
