package com.practical.PROJECT.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class LoginDTO {
    private String customerId;
    private String pin;
}
