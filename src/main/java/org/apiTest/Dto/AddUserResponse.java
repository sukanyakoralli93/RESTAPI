package org.apiTest.Dto;

import lombok.Data;
import java.util.Date;

@Data
public class AddUserResponse {
    private String id;
    private Date createdAt;
}
