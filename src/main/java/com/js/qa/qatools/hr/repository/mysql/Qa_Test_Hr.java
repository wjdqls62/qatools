package com.js.qa.qatools.hr.repository.mysql;
import com.js.qa.qatools.hr.entity.mysql.qa_test_hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface Qa_Test_Hr extends JpaRepository<qa_test_hr, Integer>, QuerydslPredicateExecutor<qa_test_hr> {

}
