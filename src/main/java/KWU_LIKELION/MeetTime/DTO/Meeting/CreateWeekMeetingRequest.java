package KWU_LIKELION.MeetTime.DTO.Meeting;

import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import lombok.Data;

import java.util.List;

@Data
public class CreateWeekMeetingRequest {
    private String meeting_title;
    private mType meeting_type;
    private List<Integer> meeting_list;
    private Integer meeting_start_time;
    private Integer meeting_end_time;
}
