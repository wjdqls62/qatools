package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.entity.mysql.mysql_dept_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MySQL_DeptInfo extends JpaRepository<mysql_dept_info, Integer>, QuerydslPredicateExecutor<mysql_dept_info> {
}
