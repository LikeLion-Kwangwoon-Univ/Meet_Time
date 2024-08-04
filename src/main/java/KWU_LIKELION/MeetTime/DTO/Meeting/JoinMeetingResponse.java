package KWU_LIKELION.MeetTime.DTO.Meeting;

import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class JoinMeetingResponse {
    private Long meeting_id;
    private String meeting_title;
    private mType meeting_type;
    private List<LocalDate> meeting_day_list;
    private List<Integer> meeting_week_list;
    private List<Long> meeting_day_id;
    private Integer meeting_start_time;
    private Integer meeting_end_time;
    private LocalDateTime meeting_create_time;
}
