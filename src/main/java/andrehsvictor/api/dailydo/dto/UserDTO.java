package andrehsvictor.api.dailydo.dto;

import java.util.Set;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private Set<TaskDTO> tasks;
}
