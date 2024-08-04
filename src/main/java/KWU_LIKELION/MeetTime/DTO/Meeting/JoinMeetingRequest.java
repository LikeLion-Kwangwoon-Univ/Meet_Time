package KWU_LIKELION.MeetTime.DTO.Meeting;

import lombok.Data;

@Data
public class JoinMeetingRequest {
    private String meeting_title;
    private Long meeting_id;
}
