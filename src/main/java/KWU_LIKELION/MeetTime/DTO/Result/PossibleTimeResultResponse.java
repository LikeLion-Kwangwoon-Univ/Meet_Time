package KWU_LIKELION.MeetTime.DTO.Result;

import KWU_LIKELION.MeetTime.Domain.Enum.mType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class PossibleTimeResultResponse {
    private Long meeting_id;
    private String meeting_title;
    private Long meeting_day_id;
    private Integer possible_id;
    private List<String> possible_people;
}
