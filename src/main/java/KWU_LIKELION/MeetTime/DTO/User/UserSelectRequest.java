package KWU_LIKELION.MeetTime.DTO.User;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserSelectRequest {
    private Map<Long, List<Integer>> possible_list;
}
