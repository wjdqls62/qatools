package com.js.qa.qatools.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int pageSize;
    private String type;
    private String dbType;
    private String keyword;
    private String sortType;
    private String sortName;

    public PageRequestDTO(){
        this.page = 1;
        this.pageSize = 10;
    }

    public Pageable getPageable(){
        Sort sort;

        switch (sortType){
            case "asc":
                sort = Sort.by(sortName).ascending();
                break;
            case "desc":
                sort = Sort.by(sortName).descending();
                break;
            default:
                sort = Sort.by("No").descending();
                break;
        }

        return PageRequest.of(page - 1, pageSize, sort);
    }
}