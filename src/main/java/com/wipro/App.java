package com.wipro;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.wipro.entity.Employee;

public class App {
	public static void main(String[] args) {

		// Configure Hibernate
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml");
		config.addAnnotatedClass(Employee.class);

		// Create SessionFactory and Session
		SessionFactory sessionFactory = config.buildSessionFactory();
		Session session = sessionFactory.openSession();

		// Transaction for Insert
		Transaction transaction = session.beginTransaction();

		System.out.println("===================this is insert===================");
		// Insert operation starts from here
		Employee employee1 = new Employee(2, "Mubashir", "Kashmir"); // No ID, MySQL auto-increment will handle it

		session.persist(employee1); // Persist the new employee
		transaction.commit(); // Commit transaction
		System.out.println("Employee Inserted: " + employee1);

		System.out.println("===================this is update===================");
		// updating operation is here
		transaction = session.beginTransaction();
		Employee employeeToUpdate = session.get(Employee.class, 2); // Fetch employee with stid = 1

		if (employeeToUpdate != null) {
			
			employeeToUpdate.setStname("Aqib"); // Update the name field
			employeeToUpdate.setAddress("Kashmir2");
			
			session.merge(employeeToUpdate); // Update in database
			transaction.commit(); // Commit transaction
			System.out.println("Employee Updated is: " + employeeToUpdate);
		} else {
			System.out.println("Employee not found for update.");
		}

		System.out.println("===================this is delete===================");
		// Delete operation starts here.
		transaction = session.beginTransaction();
		Employee employeeToDelete = session.get(Employee.class, 2); // Fetch employee with stid = 1

		if (employeeToDelete != null) {
			session.remove(employeeToDelete); // Remove (delete) the employee
			transaction.commit(); // Commit transaction
			System.out.println("Deleted Employee is: " + employeeToDelete);
		} else {
			System.out.println("Employee not found for delete.");
		}

		System.out.println("================fetch data using find()======================");

		// find method used for Fetching employee by ID
		Employee employee = session.find(Employee.class, 1); // Fetch employee with stid = 1

		if (employee != null) {
			System.out.println("Employee Found: " + employee);
		} else {
			System.out.println("Employee not found.");
		}

		System.out.println("================fetch data using get()======================");
		// get method is same as fetch but throws exception if the entity isnt found
		Employee emplyee = session.get(Employee.class, 2); // Fetch employee with stid = 2

		if (employee != null) {
			System.out.println("Employee Found: " + emplyee);
		} else {
			System.out.println("Employee not found.");
		}

		System.out.println("==================createQuery====================");
		// create query starts here

		String hql = "FROM Employee WHERE stname = :name"; // HQL query to find employee by name
		List<Employee> employees = session.createQuery(hql, Employee.class).setParameter("name", "Aqib")
				.getResultList(); // Fetch all employees with the name "Aqib"

		if (!employees.isEmpty()) {
			for (Employee employe : employees) {
				System.out.println("Employee Found: " + employe);
			}
		} else {
			System.out.println("No employees found with the given name.");
		}

		System.out.println("===================createNativequery===================");

		String sql = "SELECT * FROM Employee WHERE stname = :name"; // Native SQL query
		List<Employee> employees2 = session.createNativeQuery(sql, Employee.class).setParameter("name", "Aqib")
				.getResultList(); // Fetch employees with the name "Mubashir"

		if (!employees.isEmpty()) {
			for (Employee emply : employees2) {
				System.out.println("Employee Found: " + emply);
			}
		} else {
			System.out.println("No employees found with the given name.");
		}

		// Close session and sessionFactory
		session.close();
		sessionFactory.close();

	}
}
