package sajadvpm.feature.person;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "TBPerson")
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson", updatable = false, nullable = false)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    private String path;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Boolean isActive;

    private LocalDate insertDate;

    private LocalDate updateDate;

    public Person() {
    }

    public Person(String name, String cpf, String email, String path, LocalDate birthDate, Boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.path = path;
        this.birthDate = birthDate;
        this.isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }
}