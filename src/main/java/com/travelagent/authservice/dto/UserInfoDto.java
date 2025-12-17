package com.travelagent.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoDto {
    private String userName;
        private String email;
        private String password;
        private String[] roles;

        public UserInfoDto() {
         roles = new String[50];
        }

        public Boolean isValid()
        {
            return email!=null&& !email.isBlank() && 
                userName!=null&& !userName.isBlank() && 
                password!=null&& !password.isBlank();
        }
}
