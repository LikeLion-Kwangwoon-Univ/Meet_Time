package KWU_LIKELION.MeetTime.Domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Users {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String nickname;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
}
