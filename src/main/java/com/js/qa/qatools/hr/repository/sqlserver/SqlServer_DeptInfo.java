package com.js.qa.qatools.hr.repository.sqlserver;

import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_dept_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SqlServer_DeptInfo extends JpaRepository<sqlserver_dept_info, Integer>, QuerydslPredicateExecutor<sqlserver_dept_info> {
}
