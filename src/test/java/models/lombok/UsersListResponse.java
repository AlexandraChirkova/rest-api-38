package models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class UsersListResponse {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<UserData> data;
    private Support support;
    private MetaInfo _meta;
}
