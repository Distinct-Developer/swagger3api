package za.co.distinct.swagger.api.resources;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.distinct.swagger.api.models.Employee;

/**
 *
 * @author Boiki Mphore
 * @since 2022-11-15
 */
@RestController
@RequestMapping("employees")
public class EmployeeResource {

    private ConcurrentMap<Integer, Employee> employeeList = new ConcurrentHashMap<>();

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeList.get(id));
    }

    @GetMapping
    public ResponseEntity<Collection<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeList.values());
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeList.put(employee.getId(), employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        for (var employeeEntry : employeeList.entrySet()){
            if (employeeEntry.getKey().equals(employee.getId())){
                employeeList.put(employeeEntry.getKey(), (Employee)employeeEntry);
            }
        }
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(@PathVariable("id") Integer id) {
        for (var employee : employeeList.entrySet()){
            if (employee.getKey().equals(id)){
                employeeList.remove(id);
            }
        }
        return (ResponseEntity)ResponseEntity.ok();
    }
}
