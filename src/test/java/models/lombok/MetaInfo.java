package models.lombok;

import lombok.Data;

import java.util.List;

@Data
public class MetaInfo {
    private String powered_by;
    private String upgrade_url;
    private String docs_url;
    private String template_gallery;
    private String message;
    private List<String> features;
    private String upgrade_cta;
}
