package KWU_LIKELION.MeetTime.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.StyleContext;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "possibleTime")
public class PossibleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="possibleTime_id")
    private Long id;

    //??ManyToOne??
    @ManyToOne(fetch=FetchType.LAZY)
    @Column(nullable = false)
    private Long user_id;

    @ManyToOne
    @JoinColumn(name="meetingDay_id")
    private MeetingDay meetingDay;


    @Column(nullable = false)
    private LocalTime possibleTime;
}
