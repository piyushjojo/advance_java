package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Employee;

@Repository
@Transactional
public class EmployeeDaoImpl implements IEmployeeDao {
	//dep : javax.persistence.EntityManager
	@Autowired
	private EntityManager mgr;

	@Override
	public List<Employee> getEmpsByDeptId(long deptId) {
		String jpql="select e from Employee e join e.department d where d.id=:id";
		return mgr.createQuery(jpql, Employee.class).setParameter("id", deptId).getResultList();
	}

}
