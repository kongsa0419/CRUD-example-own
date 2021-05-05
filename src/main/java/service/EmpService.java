package service;

import domain.EmpRepository;
import dto.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class EmpService {
    private final EmpRepository empRepository;

    @Autowired
    public EmpService(EmpRepository empRepository) {this.empRepository = empRepository;}


    public Emp getById(int id) throws SQLException, ClassNotFoundException {
        Emp emp = empRepository.getById(id);
        return emp;
    }

    public List<Emp> getEmployees() {
        List<Emp> empList = empRepository.getEmployees();
        return empList;
    }

    public void insertEmp(Emp emp) {
        empRepository.insertEmployee(emp);
    }


    public void updateEmp(Emp emp) {
        empRepository.putEmployee(emp);
    }

    public void deleteEmp(int id) {
        empRepository.deleteEmployee(id);
    }
}
