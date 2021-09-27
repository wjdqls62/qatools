package com.js.qa.qatools.hr.service.mysql;

import com.js.qa.qatools.hr.dto.*;
import com.js.qa.qatools.hr.entity.mysql.Qqa_test_hr;
import com.js.qa.qatools.hr.entity.mysql.dept_info;
import com.js.qa.qatools.hr.entity.mysql.qa_test_hr;
import com.js.qa.qatools.hr.entity.mysql.user_grade;
import com.js.qa.qatools.hr.repository.mysql.DeptInfo;
import com.js.qa.qatools.hr.repository.mysql.Qa_Test_Hr;
import com.js.qa.qatools.hr.repository.mysql.UserGrade;
import com.js.qa.qatools.hr.user.GenerateUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MySQL_HR_ServiceImpl implements MySQL_HR_Service{
    private final Qa_Test_Hr hrRepository;
    private final DeptInfo deptRepository;
    private final UserGrade gradeRepository;
    private final GenerateUser user;

    @Override
    public Integer hr_register(hrDTO dto) {
        qa_test_hr entity = dtoToEntity(dto);

        hrRepository.save(entity);
        return entity.getNO();
    }

    @Override
    public void hr_auto_register(int count, String isClear, String filterMode, String lang, Integer grade, Integer dept, String mailserver_encrypt, String mailserver_host, String mailserver_port, String grp, Character grp_admin, Character apt, Character detox, String domainList) {
        // 기존데이터 삭제옵션 활성화시 데이터 제거
        if(isClear != null){
            hrRepository.deleteAll();
        }

        // 직급, 부서정보 로드
        List<user_grade> userGrade = gradeRepository.findAll();
        List<dept_info> deptList = deptRepository.findAll();

        List<qa_test_hr> entityList = new ArrayList<>();
        String[] domain = domainList.split(",");

        for(int i = 0; i < count; i++){
            StringBuffer buffer = new StringBuffer(user.generateEmail(domain));

            hrDTO tempHrDTO = hrDTO.builder()
                    .EMAIL(String.valueOf(buffer))
                    .PASSWORD(String.valueOf(user.getneratePassword()))
                    .NAME(user.generateName())
                    .GRP(grp)
                    .GRP_ADMIN(grp_admin)
                    .DOMAIN((buffer.substring(buffer.indexOf("@")+1, buffer.length())))
                    .EARS_USE(null)
                    .EARS_EXPIRE_ACTION(null)
                    .ROUTE_SERVER(null)
                    .FORWARD_SERVER(mailserver_encrypt + "|" + mailserver_host + "|" + mailserver_port)
                    .ROUTE_EMAIL(String.valueOf(buffer))
                    .RUN_MODE(null)
                    .FILTER_ORDER(filterMode)
                    .APT_USE(apt)
                    .DETOX_USE(detox)
                    .LANG(lang)
                    .USR_MANAGER(null)
                    .IS_VALID(null)
                    .build();

            // select의 option 중 value가 0이면 랜덤생성한다.
            if(grade == 0){
                long grade_count = gradeRepository.count();
                tempHrDTO.setUSR_GRADE(userGrade.get((int) (Math.random() * grade_count)));
            }else{
                tempHrDTO.setUSR_GRADE(userGrade.get(grade-1));
            }
            if(dept == 0){
                long dept_count = deptRepository.count();
                tempHrDTO.setDEPT_INFO(deptList.get((int) (Math.random() * dept_count)));
            }else{
                tempHrDTO.setDEPT_INFO(deptList.get(dept-1));
            }
            entityList.add(dtoToEntity(tempHrDTO));
        }
        hrRepository.saveAll(entityList);
    }

    @Override
    public PageResultDTO<hrDTO, qa_test_hr> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("NO").descending());

        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        Page<qa_test_hr> result = hrRepository.findAll(booleanBuilder, pageable);

        Function<qa_test_hr, hrDTO> fn = (entity -> entityToDTO(entity));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public hrDTO hr_read(Integer emp_no) {
        return entityToDTO(hrRepository.getById(emp_no));
    }

    @Override
    public void hr_remove(Integer emp_no) {
        hrRepository.deleteById(emp_no);
    }

    @Override
    public void hr_removeAll() {
        hrRepository.deleteAll();
    }

    @Override
    public void hr_modify(hrDTO dto) {

        qa_test_hr entity = hrRepository.getById(dto.getNO());
        entity.setEMAIL(dto.getEMAIL());
        entity.setPASSWORD(dto.getPASSWORD());
        entity.setNAME(dto.getNAME());
        entity.setGRP(dto.getGRP());
        entity.setGRP_ADMIN(dto.getGRP_ADMIN());
        entity.setDOMAIN(dto.getDOMAIN());
        entity.setEARS_USE(dto.getEARS_USE());
        entity.setEARS_EXPIRE_ACTION(dto.getEARS_EXPIRE_ACTION());
        entity.setROUTE_SERVER(dto.getROUTE_SERVER());
        entity.setFORWARD_SERVER(dto.getFORWARD_SERVER());
        entity.setROUTE_EMAIL(dto.getROUTE_EMAIL());
        entity.setRUN_MODE(dto.getRUN_MODE());
        entity.setFILTER_ORDER(dto.getFILTER_ORDER());
        entity.setAPT_USE(dto.getAPT_USE());
        entity.setDETOX_USE(dto.getDETOX_USE());
        entity.setLANG(dto.getLANG());
        entity.setUSR_GRADE(dto.getUSR_GRADE());
        entity.setDEPT_INFO(dto.getDEPT_INFO());
        entity.setUSR_MANAGER(dto.getUSR_MANAGER());
        entity.setIS_VALID(dto.getIS_VALID());

        hrRepository.save(entity);
    }

    @Override
    public long hr_getCnt() {
        return hrRepository.count();
    }

    @Override
    public List<dept_info> dept_getList() {
        return deptRepository.findAll();
    }

    @Override
    public Integer dept_register(hrDTO dto) {
        return null;
    }

    @Override
    public deptDTO dept_read(Integer dept_num) {
        Optional<dept_info> result = deptRepository.findById(dept_num);
        return result.isPresent() ? entityToDTO(result.get()) : null;

    }

    @Override
    public void dept_remove(Integer dept_num) {
        deptRepository.delete(deptRepository.getById(dept_num));
    }

    @Override
    public void dept_modify(deptDTO dto) {
        dept_info entity = deptRepository.getById(dto.getDept_num());

        entity.changeDeptName(dto.getDept_name());
        entity.changeParent(dto.getDept_parent());

        deptRepository.save(entity);
    }

    @Override
    public void dept_register(deptDTO dto) {
        deptRepository.save(dtoToEntity(dto));
    }

    @Override
    public List<user_grade> grade_getList() {
        return gradeRepository.findAll();
    }

    @Override
    public Integer grade_register(hrDTO dto) {
        return null;
    }

    @Override
    public gradeDTO grade_read(Integer grade_num) {
        Optional<user_grade> result = gradeRepository.findById(grade_num);
        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void grade_remove(Integer grade_num) {
        gradeRepository.delete(gradeRepository.getById(grade_num));
    }

    @Override
    public void grade_modify(gradeDTO dto) {
        user_grade entity = gradeRepository.getById(dto.getGrade_num());

        entity.changeGradeName(dto.getGrade_name());

        gradeRepository.save(entity);
    }

    @Override
    public void grade_register(gradeDTO dto) {
        gradeRepository.save(dtoToEntity(dto));
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        String type = requestDTO.getType();

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Qqa_test_hr qa_test_hr = Qqa_test_hr.qa_test_hr;

        String keyword = requestDTO.getKeyword();

        BooleanExpression expression = qa_test_hr.NO.gt(0L);

        booleanBuilder.and(expression);

        return booleanBuilder;
    }

}
