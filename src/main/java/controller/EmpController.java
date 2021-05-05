package controller;


import dto.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.EmpService;

import java.sql.SQLException;
import java.util.List;


// @RequestParam(), @PathVariable() 둘을 혼용하여 쓰기도 함.
//기본 도메인? Uri
//@RequestMapping(value = "/bcsd/crud")
@Controller
public class EmpController {

    //여기다가 @Autowired 하는게 맞나 모르겠다
    private final EmpService empService;

    //right place to hold @Autowired
    @Autowired
    public EmpController(EmpService empService){this.empService = empService;}



    //RequestParam("id")라고 써서 오류가 날 수도 있을까?
    @ResponseBody
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Emp getEmpById(@PathVariable("id") int id) throws SQLException, ClassNotFoundException {
        Emp emp = empService.getById(id);
        return emp;
    }

    @ResponseBody
    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public List<Emp> getEmployees(){
        List<Emp> emplist = empService.getEmployees();
        return emplist;
    }

    //@RequestBody : 클라이언트로부터 Emp객체를 받아야 함
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String insertEmp(@RequestBody Emp emp){
        if(emp != null)
            empService.insertEmp(emp);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmp(@RequestBody Emp emp){
        if(emp != null)
            empService.updateEmp(emp);
        return "redirect:/employee";
    }

    //@RequestParam() 써보는 테스트
    // 기본적으로 /employee?id=4 식으로 쿼리를 짠다하니, /employee/4 로 호출해보자
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable int id) throws SQLException, ClassNotFoundException {
        if(empService.getById(id) != null)
            empService.deleteEmp(id);
        else
            System.out.println("! no row for that id !");

        return "redirect:/employee";
    }


}
