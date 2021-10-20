package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.entity.mysql.dept_info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MySQL_DeptInfo extends JpaRepository<dept_info, Integer>, QuerydslPredicateExecutor<dept_info> {
}
