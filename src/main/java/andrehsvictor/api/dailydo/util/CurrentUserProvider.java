package andrehsvictor.api.dailydo.util;

import org.springframework.security.core.context.SecurityContextHolder;

import andrehsvictor.api.dailydo.entity.User;

public class CurrentUserProvider {
    public static User currentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
