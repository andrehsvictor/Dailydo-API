package andrehsvictor.api.dailydo.exception;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DefaultExceptionResponse {
    private Date timeStamp;
    private String message;
    private String path;
}
