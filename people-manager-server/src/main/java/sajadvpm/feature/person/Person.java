package sajadvpm.feature.person;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sajadvpm.feature.file.File;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "persons")
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "avatar_id")
    private File avatar;

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
        this.birthDate = birthDate;
        this.isActive = active;
    }

    public Person(Integer id, File avatar, LocalDate insertDate, LocalDate updateDate) {
        this.id = id;
        this.avatar = avatar;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public void deactivate()
    {
        isActive = false;
        updateDate = LocalDate.now();
    }

    public void newPerson()
    {
        isActive = true;
        insertDate = LocalDate.now();
    }

    public void updatePerson()
    {
        isActive = true;
        insertDate = LocalDate.now();
    }
}
