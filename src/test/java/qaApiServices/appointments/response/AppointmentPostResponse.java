package qaApiServices.appointments.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AppointmentPostResponse {
    private List<String> errors;
}
