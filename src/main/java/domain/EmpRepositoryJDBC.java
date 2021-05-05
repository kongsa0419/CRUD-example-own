/*domain : DB에 접근해서 정보 가져오는애*/

package domain;

import dto.Emp;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class EmpRepositoryJDBC implements EmpRepository{


    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/temp?serverTimezone=UTC";
    private static String dbUser = "root";
    private static String DbPassword = "Saengdo1108#";



    @Override
    public Emp getById(int id) throws SQLException, ClassNotFoundException {
        Emp emp = new Emp();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            pstmt = conn.prepareStatement("SELECT * FROM emp99 WHERE id = ?");
            pstmt.setString(1, Integer.toString(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getFloat("salary"));
                emp.setDesignation(rs.getString("designation"));
            }

            rs.close();
            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return emp;
    }

    @Override
    public List<Emp> getEmployees() {
        List<Emp> empList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            pstmt = conn.prepareStatement("SELECT * FROM emp99");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Emp emp = new Emp();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setSalary(rs.getFloat("salary"));
                emp.setDesignation(rs.getString("designation"));
                empList.add(emp);
            }

            rs.close();
            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return empList;
    }

    //POST
    @Override
    public void insertEmployee(Emp emp) {
        String id = Integer.toString(emp.getId());
        String name = emp.getName();
        String salary  = Float.toString(emp.getSalary());
        String designation = emp.getDesignation();

        //JDBC
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl,dbUser,DbPassword);
            pstmt = conn.prepareStatement("INSERT INTO emp99(name, salary, designation) VALUES(?,?,?)"); // id: AUTO_INCREMENT 뻈음
            pstmt.setString(1, name);
            pstmt.setString(2,salary);
            pstmt.setString(3,designation);
            int count = pstmt.executeUpdate();
            System.out.println(count + "개의 row가 영향을 받았음.(inserted into db)");

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void deleteEmployee(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl,dbUser,DbPassword);
            pstmt = conn.prepareStatement("delete from emp99 where id = ?"); // id: AUTO_INCREMENT 뻈음
            pstmt.setString(1, Integer.toString(id));
            int count = pstmt.executeUpdate();
            System.out.println(count + "개의 row가 영향을 받았음.(inserted into db)");

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void putEmployee(Emp emp) {
        String id = Integer.toString(emp.getId());
        String name = emp.getName();
        String salary  = String.valueOf(emp.getSalary());
        String designation = emp.getDesignation();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl,dbUser,DbPassword);
            pstmt = conn.prepareStatement("update emp99 set name = ?, salary = ?, designation = ? where id = ?"); // id: AUTO_INCREMENT 뻈음
            pstmt.setString(1, name);
            pstmt.setString(2,salary);
            pstmt.setString(3,designation);
            pstmt.setString(4, id);
            int count = pstmt.executeUpdate();
            System.out.println("affected Row : "+count);

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

    }


}
