package com.js.qa.qatools.hr.service.sqlserver;

import com.js.qa.qatools.hr.dto.*;
import com.js.qa.qatools.hr.entity.sqlserver.Qsqlserver_qa_test_hr;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_dept_info;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_qa_test_hr;
import com.js.qa.qatools.hr.entity.sqlserver.sqlserver_user_grade;
import com.js.qa.qatools.hr.repository.sqlserver.SqlServer_DeptInfo;
import com.js.qa.qatools.hr.repository.sqlserver.SqlServer_Qa_Test_Hr;
import com.js.qa.qatools.hr.repository.sqlserver.SqlServer_UserGrade;
import com.js.qa.qatools.hr.user.GenerateUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class SqlServer_HR_ServiceImpl implements SqlServer_HR_Service {
    private final SqlServer_Qa_Test_Hr hrRepository;
    private final SqlServer_DeptInfo deptRepository;
    private final SqlServer_UserGrade gradeRepository;
    private final GenerateUser user;

    @Override
    public Integer hr_register(hrDTO dto) {
        sqlserver_qa_test_hr entity = dtoToEntity(dto);

        hrRepository.save(entity);
        return entity.getNo();
    }

    @Override
    public void hr_auto_register(int count,
                                 String isClear,
                                 String filterMode,
                                 String lang,
                                 Integer grade,
                                 Integer dept,
                                 String mailserver_encrypt,
                                 String mailserver_host,
                                 String mailserver_port,
                                 String grp,
                                 Character grp_admin,
                                 Character apt,
                                 Character detox,
                                 String domainList,
                                 String passwordType,
                                 Character earsUse,
                                 Character earExpireAction,
                                 String routeServer,
                                 String routeEmail,
                                 Character runMode,
                                 String customPassword,
                                 String targetDatabase) {
        // 기존데이터 삭제옵션 활성화시 데이터 제거
        if(isClear != null){
            hrRepository.deleteAll();
            hrRepository.resetAutoIncreament();
        }

        // 직급, 부서정보 로드
        List<sqlserver_user_grade> userGrade = gradeRepository.findAll();
        List<sqlserver_dept_info> deptList = deptRepository.findAll();
        sqlserver_user_grade resultUserGrade;
        sqlserver_dept_info resultDeptInfo;
        // select의 option 중 value가 0이면 랜덤생성한다.
        if(grade == 0){
            long grade_count = gradeRepository.count();
            resultUserGrade = userGrade.get((int) (Math.random() * grade_count));
        }else{
            resultUserGrade = userGrade.get(grade-1);
        }
        if(dept == 0){
            long dept_count = deptRepository.count();
            resultDeptInfo = deptList.get((int) (Math.random() * dept_count));
        }else{
            resultDeptInfo = deptList.get(dept-1);
        }

        List<sqlserver_qa_test_hr> entityList = new ArrayList<>();
        String[] domain = domainList.split(",");

        for(int i = 0; i < count; i++){
            StringBuffer buffer = new StringBuffer(user.generateEmail(domain));

            hrDTO tempHrDTO = hrDTO.builder()
                    .EMAIL(String.valueOf(buffer))
                    .PASSWORD(String.valueOf(user.getneratePassword(passwordType, customPassword)))
                    .NAME(user.generateName())
                    .GRP(grp)
                    .GRP_ADMIN(grp_admin)
                    .DOMAIN((buffer.substring(buffer.indexOf("@")+1, buffer.length())))
                    .EARS_USE(earsUse)
                    .EARS_EXPIRE_ACTION(earExpireAction)
                    .ROUTE_SERVER(routeServer)
                    .FORWARD_SERVER(mailserver_encrypt + "|" + mailserver_host + "|" + mailserver_port)
                    .ROUTE_EMAIL(String.valueOf(buffer))
                    .RUN_MODE(runMode)
                    .FILTER_ORDER(filterMode)
                    .APT_USE(apt)
                    .DETOX_USE(detox)
                    .LANG(lang)
                    .USR_MANAGER(null)
                    .IS_VALID(null)
                    .SQLSERVER_USR_GRADE(resultUserGrade)
                    .SQLSERVER_DEPT_INFO(resultDeptInfo)
                    .build();

            entityList.add(dtoToEntity(tempHrDTO));
        }
        hrRepository.saveAll(entityList);
    }

    @Override
    public PageResultDTO<hrDTO, sqlserver_qa_test_hr> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable();

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<sqlserver_qa_test_hr> result = hrRepository.findAll(booleanBuilder, pageable);

        Function<sqlserver_qa_test_hr, hrDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public hrDTO hr_read(Integer emp_no) {
        Optional<sqlserver_qa_test_hr> entity = hrRepository.findById(emp_no);

        return entityToDTO(entity.get());
    }

    @Override
    public void hr_remove(Integer emp_no) {
        hrRepository.deleteById(emp_no);
    }

    @Override
    public void hr_removeAll() {
        hrRepository.deleteAll();
        hrRepository.resetAutoIncreament();
    }

    @Override
    public void hr_modify(hrDTO dto) {

        sqlserver_qa_test_hr entity = hrRepository.findById(dto.getNO()).get();
        entity.setEMAIL(dto.getEMAIL());
        entity.setPASSWORD(dto.getPASSWORD());
        entity.setName(dto.getNAME());
        entity.setGrp(dto.getGRP());
        entity.setGrpAdmin(dto.getGRP_ADMIN());
        entity.setDomain(dto.getDOMAIN());
        entity.setEarsUse(dto.getEARS_USE());
        entity.setEarsExpireAction(dto.getEARS_EXPIRE_ACTION());
        entity.setRouteServer(dto.getROUTE_SERVER());
        entity.setForwardServer(dto.getFORWARD_SERVER());
        entity.setRouteEmail(dto.getROUTE_EMAIL());
        entity.setRunMode(dto.getRUN_MODE());
        entity.setFilterOrder(dto.getFILTER_ORDER());
        entity.setAptUse(dto.getAPT_USE());
        entity.setDetoxUse(dto.getDETOX_USE());
        entity.setLang(dto.getLANG());
        entity.setUsrGrade(dto.getSQLSERVER_USR_GRADE());
        entity.setUsrDept(dto.getSQLSERVER_DEPT_INFO());
        entity.setUsrManager(dto.getUSR_MANAGER());
        entity.setIsValid(dto.getIS_VALID());
        hrRepository.save(entity);
        /**
        entity.setEMAIL(dto.getEMAIL());
        entity.setPASSWORD(dto.getPASSWORD());
        entity.setName(dto.getNAME());
        entity.setGrp(dto.getGRP());
        entity.setGrpAdmin(dto.getGRP_ADMIN());
        entity.setDomain(dto.getDOMAIN());
        entity.setEarsUse(dto.getEARS_USE());
        entity.setEarsExpireAction(dto.getEARS_EXPIRE_ACTION());
        entity.setRouteServer(dto.getROUTE_SERVER());
        entity.setForwardServer(dto.getFORWARD_SERVER());
        entity.setRouteEmail(dto.getROUTE_EMAIL());
        entity.setRunMode(dto.getRUN_MODE());
        entity.setFilterOrder(dto.getFILTER_ORDER());
        entity.setAptUse(dto.getAPT_USE());
        entity.setDetoxUse(dto.getDETOX_USE());
        entity.setLang(dto.getLANG());
        entity.setUsrGrade(dto.getSQLSERVER_USR_GRADE());
        entity.setUsrDept(dto.getSQLSERVER_DEPT_INFO());
        entity.setUsrManager(dto.getUSR_MANAGER());
        entity.setIsValid(dto.getIS_VALID());


        hrRepository.save(entity);
         **/
    }

    @Override
    public long hr_getCnt() {
        return hrRepository.count();
    }

    @Override
    public List<sqlserver_dept_info> dept_getList() {
        return deptRepository.findAll();
    }

    @Override
    public Integer dept_register(hrDTO dto) {
        return null;
    }

    @Override
    public deptDTO dept_read(Integer dept_num) {
        Optional<sqlserver_dept_info> result = deptRepository.findById(dept_num);
        return result.isPresent() ? entityToDTO(result.get()) : null;

    }

    @Override
    public void dept_remove(Integer dept_num) {
        deptRepository.delete(deptRepository.getById(dept_num));
    }

    @Override
    public void dept_modify(deptDTO dto) {
        Optional<sqlserver_dept_info> entity = deptRepository.findById(dto.getDept_num());

        entity.ifPresent(sqlserver_dept_info -> {
            sqlserver_dept_info.changeDeptName(dto.getDept_name());
            sqlserver_dept_info.changeParent(dto.getDept_parent());
            deptRepository.save(sqlserver_dept_info);
        });
    }

    @Override
    public void dept_register(deptDTO dto) {
        deptRepository.save(dtoToEntity(dto));
    }

    @Override
    public List<sqlserver_user_grade> grade_getList() {
        return gradeRepository.findAll();
    }

    @Override
    public Integer grade_register(hrDTO dto) {
        return null;
    }

    @Override
    public gradeDTO grade_read(Integer grade_num) {
        Optional<sqlserver_user_grade> result = gradeRepository.findById(grade_num);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void grade_remove(Integer grade_num) {
        gradeRepository.delete(gradeRepository.getById(grade_num));
    }

    @Override
    public void grade_modify(gradeDTO dto) {
        Optional<sqlserver_user_grade> entity = gradeRepository.findById(dto.getGrade_num());

        entity.ifPresent(sqlserver_user_grade -> {
            sqlserver_user_grade.changeGradeName(dto.getGrade_name());
            gradeRepository.save(sqlserver_user_grade);
        });
    }

    @Override
    public void grade_register(gradeDTO dto) {
        gradeRepository.save(dtoToEntity(dto));
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Qsqlserver_qa_test_hr qa_test_hr = Qsqlserver_qa_test_hr.sqlserver_qa_test_hr;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qa_test_hr.no.gt(0L);

        booleanBuilder.and(expression);

        return booleanBuilder;
    }
}
