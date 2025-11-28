package models.lombok;

import lombok.Data;

@Data
public class RegisterResponseModel {

    String token, error;
    int id;

}
