package com.abs.controller;

import com.abs.model.Employee;
import com.abs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;



@CrossOrigin

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceimpl;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Employee employee){
        employeeServiceimpl.save(employee);
        return ResponseEntity.ok("save Data Successfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empEmailId , @PathVariable String empPassword){
        employeeServiceimpl.signIn(empEmailId, empPassword);
        return  ResponseEntity.ok("signUp Scessfully");
    }

    @GetMapping("/findbyid/{empId}")
    public  ResponseEntity<Employee> findById(@PathVariable int empId){
        return  ResponseEntity.ok(employeeServiceimpl.findByID(empId));
    }


    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll(){
        return ResponseEntity.ok(employeeServiceimpl.findAll());
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId)
    {
        employeeServiceimpl.deleteById(empId);
        return ResponseEntity.ok("deleted data by id");

    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll(){
        employeeServiceimpl.deleteAll();
        return  ResponseEntity.ok(" All deta deleted ");
    }

    @GetMapping("/findbyname/{empName}")
    public  ResponseEntity<List<Employee>> findByName(@PathVariable String empName)
    {
        return ResponseEntity.ok(employeeServiceimpl.findAll().stream().
                filter(emp -> emp.getEmpName().equals(empName)).toList());
    }

    @GetMapping("/findbycontact/{empContact}")
    public  ResponseEntity<Employee> findByContact(@PathVariable long empContact)
    {
        return ResponseEntity.ok(employeeServiceimpl.findAll().stream().filter(emp -> emp.getEmpContact()== empContact).toList().get(0));
    }


    @GetMapping("/findbyemailid/{empEmailId}")
    public  ResponseEntity<Employee> findbyEmailId(@PathVariable String empEmailId)
    {
        return  ResponseEntity.ok(employeeServiceimpl.findAll().stream().filter(emp -> emp.getEmpEmailId().equals(empEmailId)).toList().get(0));
    }

    @GetMapping("/findbydob/{empDOB}")
    public  ResponseEntity<List<Employee>> findByDob(@PathVariable String empDOB){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return  ResponseEntity.ok(employeeServiceimpl.findAll().stream().filter(emp ->simpleDateFormat.format(emp.getEmpDOB()).equals(empDOB)).toList());
    }

    @GetMapping("/sortbyid")
    public  ResponseEntity<List<Employee>> sortById(){
        return ResponseEntity.ok(employeeServiceimpl.findAll().stream().sorted
                (Comparator.comparingInt(Employee::getEmpId).reversed()).toList());
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeServiceimpl.findAll().stream().sorted(Comparator.comparing(Employee::getEmpName)).toList());
    }

    @GetMapping("/sortbysalary")
    public  ResponseEntity<List<Employee>> sortBySalary(){
        return  ResponseEntity.ok(employeeServiceimpl.findAll().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).toList());
    }


    @GetMapping("filterdatabysalary")
    public ResponseEntity<List<Employee>> filterBySalary(){
        return ResponseEntity.ok(employeeServiceimpl.findAll().stream().filter(emp -> emp.getEmpSalary() >2000).toList());
    }


    @GetMapping("/eligiblefor loan/{empId}")
    public ResponseEntity<String> loanEligibility(@PathVariable int empId){

        String msg="";
        boolean flag = false;
        for(Employee emp : employeeServiceimpl.findAll()){
            if(emp.getEmpId() == empId && emp.getEmpSalary() >50000){
                msg=" eligibel for loan";
                flag = true;
                break;
            }
            if (!flag){
                msg = "not Eligible";
            }
        }
           return  ResponseEntity.ok(msg);
          }


          @PutMapping("/updatedata/{empId}")
       public  ResponseEntity<String> updateData(@PathVariable int empId , @RequestBody Employee employee){
              employeeServiceimpl.upDateData(empId ,employee);
              return ResponseEntity.ok("Data Updated Successfully");
          }


          @GetMapping("/findbyanyinput/{input}")
       public ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String input){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

          return  ResponseEntity.ok(employeeServiceimpl.findAll().stream().filter(emp -> simpleDateFormat.format(emp.getEmpDOB()).equals(input)
          || String.valueOf(emp.getEmpContact()).equals(input)
           || String.valueOf(emp.getEmpId()).equals(input)
           ||emp.getEmpName().equals(input)
          || emp.getEmpEmailId().equals(input)).toList());
          }



}

