package com.js.qa.qatools.hr.repository.mysql;

import com.js.qa.qatools.hr.entity.mysql.user_grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MySQL_UserGrade extends JpaRepository<user_grade, Integer>, QuerydslPredicateExecutor<user_grade> {
}
