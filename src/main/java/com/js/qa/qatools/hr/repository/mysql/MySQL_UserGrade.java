package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.entity.mysql.mysql_user_grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MySQL_UserGrade extends JpaRepository<mysql_user_grade, Integer>, QuerydslPredicateExecutor<mysql_user_grade> {
}
