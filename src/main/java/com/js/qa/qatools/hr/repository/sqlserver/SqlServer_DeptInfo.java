package com.js.qa.qatools.hr.repository.sqlserver;

import com.js.qa.qatools.hr.entity.sqlserver.dept_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SqlServer_DeptInfo extends JpaRepository<dept_info, Integer>, QuerydslPredicateExecutor<dept_info> {
}
