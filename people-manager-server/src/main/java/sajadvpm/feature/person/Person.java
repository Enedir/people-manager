package sajadvpm.feature.person;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import sajadvpm.feature.file.File;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;


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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id")
    private File avatar;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private Boolean isActive;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    public Person() {
    }

    public Person(String name, String cpf, String email, LocalDate birthDate, Boolean active) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.birthDate = birthDate;
        this.isActive = active;
    }

    public Person(Integer id, File avatar, LocalDateTime insertDate, LocalDateTime updateDate) {
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

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public void deactivate()
    {
        this.isActive = Boolean.FALSE;
        this.updateDate = LocalDateTime.now();
    }

    public void newPerson(File avatar)
    {
        this.avatar = avatar;
        this.isActive = Boolean.TRUE;
        this.insertDate = LocalDateTime.now();
    }

    public void updatePerson(Person existing, File avatar)
    {
        this.avatar = avatar;
        this.isActive = Boolean.TRUE;
        this.insertDate = existing.getInsertDate();
        this.updateDate = LocalDateTime.now();
    }

    public Boolean hasAvatar(){
        return  avatar.getId() != null;
    }
}
