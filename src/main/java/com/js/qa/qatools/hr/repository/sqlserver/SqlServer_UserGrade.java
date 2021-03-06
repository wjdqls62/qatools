package com.js.qa.qatools.hr.repository.sqlserver;

import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_user_grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SqlServer_UserGrade extends JpaRepository<sqlserver_user_grade, Integer>, QuerydslPredicateExecutor<sqlserver_user_grade> {
}
