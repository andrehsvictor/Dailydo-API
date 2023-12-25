package andrehsvictor.api.dailydo.dto;

import java.util.Date;

import andrehsvictor.api.dailydo.util.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Date createdAt = new Date();
    private Date expiresAt;

    public TaskDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TaskDTO(String title, Date expiresAt) {
        this.title = title;
        this.expiresAt = expiresAt;
    }

    public TaskDTO(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public TaskDTO(String title) {
        this.title = title;
    }

    public TaskDTO(String title, Status status) {
        this.title = title;
        this.status = status;
    }

    public TaskDTO(String title, String description, Date expiresAt) {
        this.title = title;
        this.description = description;
        this.expiresAt = expiresAt;
    }

    public TaskDTO(String title, String description, Status status, Date expiresAt) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.expiresAt = expiresAt;
    }
}
