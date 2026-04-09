package org.example;

import java.sql.*;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public Connection getConnection(String flag) {
        Connection con = null;
        try {
            Thread.sleep(3000);

            if(flag.equals("yes")){
                con = DriverManager.getConnection(
                        "jdbc:mysql://db:3306/test_db?useSSL=false&allowPublicKeyRetrieval=true",
                        "ethan","1234"
                );
            }else{
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:33060/test_db?useSSL=false&allowPublicKeyRetrieval=true",
                        "ethan","1234"
                );
            }

            IO.println("Connected Successful.");

        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public void Office_Data(Connection con) {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT * FROM offices");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                System.out.println(res.getString(1) + ", " + res.getString(2) + ", " + res.getString(3));
            }
            res.close();
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Paris_Employee(Connection con) {
        try {
            PreparedStatement stat = con.prepareStatement("SELECT e.* FROM offices o, employees e WHERE o.officeCode = e.officeCode AND city = ?");
            stat.setString(1, "Paris");
            ResultSet res = stat.executeQuery();
            while (res.next()) {
                System.out.println(res.getString(1) + ", " + res.getString(2) + ", " + res.getString(3));
            }
            res.close();
            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Insert(Connection con, String officecode, String city, String phone, String address, String country, String postalcode, String territory) {
        try {
            PreparedStatement stat = con.prepareStatement("INSERT INTO offices (officeCode, city, phone, addressLine1, country, postalCode, territory) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, officecode);
            stat.setString(2, city);
            stat.setString(3, phone);
            stat.setString(4, address);
            stat.setString(5, country);
            stat.setString(6, postalcode);
            stat.setString(7, territory);
            stat.executeUpdate();

            stat.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Connection con) {
        String sql = "UPDATE offices SET city = ?, phone = ? WHERE officeCode=?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "Mandalay");
            stat.setString(2, "09976745625");
            stat.setString(3, "8");

            int rows = stat.executeUpdate();
            System.out.println(rows + " row(s) updated.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Connection con) {
        String sql = "DELETE FROM offices WHERE officeCode = ?";
        try (PreparedStatement stat = con.prepareStatement(sql)) {
            stat.setString(1, "9");

            int rows = stat.executeUpdate();
            System.out.println(rows + " row(s) deleted.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static void main(String[] args) {
        Main m = new Main();
        Connection con = null;
        int count=1;
        while(con ==null && count<=20){
            System.out.println("Trying to connect....."+count);
            if(args.length>0){
                con=m.getConnection("yes");

            }else{
                con=m.getConnection("no");
            }
            count ++;
        }


        try{
            if(con !=null){
                System.out.println("-------------------------");
                m.Office_Data(con);
                System.out.println("-------------------------");
                con.close();
            }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
    }

}

    //connection class
    //statement (used without user input) & PreparedStatement (user input)
    //ResultSet (store output of database) (start with index 1)

