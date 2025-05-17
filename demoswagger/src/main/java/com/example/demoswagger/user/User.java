package com.example.demoswagger.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "tbl_User")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @NotNull
    @Min(value = 1)
    @Column(name = "user_id")
    private @Id long userId;

    @NotNull
    @NotEmpty(message = "fullName must not be empty")
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    @NotEmpty(message = "passWord must not be empty")
    @Column(name = "pass_word")
    private String passWord;

    @NotNull
    @NotEmpty(message = "notificationToken must not be empty")
    @Column(name = "notification_token")
    private String notificationToken;

    public User(long userId, String fullName, String passWord, String notificationToken) {
        super();
        this.userId = userId;
        this.fullName = fullName;
        this.passWord = passWord;
        this.notificationToken = notificationToken;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (!(object instanceof User))
            return false;
        User user = (User) object;
        return Objects.equals(this.userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId);
    }
}
