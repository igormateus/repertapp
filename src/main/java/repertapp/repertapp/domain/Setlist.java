package repertapp.repertapp.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table
public class Setlist {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @Column(name = "event", columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
    private boolean isDone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "setlist_version",
        joinColumns = @JoinColumn(name = "setlist_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "version_id", referencedColumnName = "id")
    )
    private List<Version> versions = new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;
}