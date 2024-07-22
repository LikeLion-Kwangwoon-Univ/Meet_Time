package KWU_LIKELION.MeetTime.domain;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.text.StyleContext;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "possibleTime")
@Builder
public class PossibleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="possibleTime_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name="meetingDay_id")
    private MeetingDay meetingDay;


    @Column(nullable = false)
    private LocalTime possibleTime;
}
