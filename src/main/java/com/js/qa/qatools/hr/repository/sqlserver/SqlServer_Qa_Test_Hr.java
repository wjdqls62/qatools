package com.js.qa.qatools.hr.repository.sqlserver;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_qa_test_hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;

public interface SqlServer_Qa_Test_Hr extends JpaRepository<sqlserver_qa_test_hr, Integer>, QuerydslPredicateExecutor<sqlserver_qa_test_hr> {

    /**
     * AutoIncreament 초기화
     */
    @Transactional
    @Modifying
    @Query(value = "DBCC CHECKIDENT([insa.qa_test_hr], reseed, 0);", nativeQuery = true)
    void resetAutoIncreament();
}
