package KWU_LIKELION.MeetTime.Domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter @Setter
public class PossibleTime {
    @Id @GeneratedValue
    @Column(name = "possible_time_id")
    private Long id;

    private LocalTime possible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_day_id")
    private MeetingDay meetingDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public static PossibleTime newPossibleTime(LocalTime time){
        PossibleTime possibleTime = new PossibleTime();
        possibleTime.setPossible(time);

        return possibleTime;
    }
}
