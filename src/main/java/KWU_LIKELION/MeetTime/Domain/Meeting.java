package KWU_LIKELION.MeetTime.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter @Setter
public class Meeting {
    @Id @GeneratedValue
    @Column(name = "meeting_id")
    private Long id;

    private String meetingTitle;

    @Enumerated(EnumType.STRING)
    private mType meetingType;

    private LocalTime meetingStartTime;

    private LocalTime meetingEndTime;
}
