package com.csrr.consul.csrrconsul.dto;


import lombok.*;

import java.util.Date;

/**
 * 故意加前缀...
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDTO {

    private Integer companyId;
    private String companyName;
    private Integer companyAge;
    private String companyAddress;
    private Float salary;
    private Date joinDate;

}
