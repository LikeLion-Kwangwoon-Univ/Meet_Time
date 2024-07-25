package KWU_LIKELION.MeetTime.dto;

import KWU_LIKELION.MeetTime.domain.Users;
import KWU_LIKELION.MeetTime.repository.MeetingRepository;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UsersRequest {//로그인 request
    private final MeetingRepository meetingRepository;

    private String userName;

    private String password;

    private Long meetingId;
    public Users toEntity(){
        return Users.builder()
                .userName(this.userName)
                .password(this.password)
                .meeting(meetingRepository.findById(this.meetingId).get())
                .build();
    }

}
