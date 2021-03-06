package qaApiServices.facility.builder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Facilities {
    private String street_address;
    private String country;
    private String facility_group_id;
    private String latitude;
    private String created_at;
    private String protocol_id;
    private String deleted_at;
    private String pin;
    private String updated_at;
    private String district;
    private String name;
    private String id;
    private String state;
    private String facility_type;
    private String village_or_colony;
    private String slug;
    private String zone;
    private String longitude;
    private String organization_name;
    private String facility_group_name;
    @JsonProperty("import")
    private String imports;
    private String facility_size;
    private Config config;

}
