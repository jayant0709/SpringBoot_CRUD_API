package com.mongotest.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employee")
public class EmployeeDTO {
    @Id
    private String id;
    private String employeeName;
    private String employeeGender;
    private String designation;
    private Date createdAt;
    private Date updatedAt;
}
