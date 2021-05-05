package domain;

import dto.Emp;

import java.sql.SQLException;
import java.util.List;

public interface EmpRepository {

    public Emp getById(int id) throws SQLException, ClassNotFoundException;

    List<Emp> getEmployees();

    void insertEmployee(Emp emp);

    void deleteEmployee(int id);

    void putEmployee(Emp emp);

//    void patchEmployee();
}
