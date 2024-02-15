package com.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Department;

@Repository
@Transactional
public class DepartmentDaoImpl implements IDepartmentDao {
	// dep : EntityMgr
	@Autowired
	private EntityManager mgr;

	@Override
	public List<Department> fetchAllDepartments() {
		String jpql = "select d from Department d";
		return mgr.createQuery(jpql, Department.class).getResultList();
	}

}
