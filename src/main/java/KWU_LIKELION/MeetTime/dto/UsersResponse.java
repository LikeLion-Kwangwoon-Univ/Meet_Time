package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Users;
import lombok.Builder;

@Builder
public class UsersResponse {

    private Long meetingId;
    private Long userId;

    private String userName;

    private String password;

    public static UsersResponse fromEntity(Long meetingId, Users user){
        return UsersResponse.builder()
                .meetingId(meetingId)
                .userId(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }
}
