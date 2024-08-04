package KWU_LIKELION.MeetTime.DTO.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUserResponse {
    private Long meeting_id;
    private Long user_id;
    private String nickname;
    private String password;
}
