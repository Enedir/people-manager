package sajadvpm.feature.person.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PersonCommandUpdate {

    private Integer id;

    @NotNull(message = "O nome não pode ser nulo.")
    @Size(max = 150, message = "O nome deve ter no máximo de 150 caractes.")
    private String name;

    @NotNull(message = "O cpf não pode ser nulo.")
    @NotBlank(message = "O cpf não pode estar em vazio.")
    @Size(max = 11, message = "O cpf deve ter no máximo de 9 caractes.")
    private String cpf;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email não pode estar em vazio.")
    @Size(max = 400, message = "O email deve ter no máximo de 400 caractes.")
    private String email;

    private Integer avatarId;

    @NotNull(message = "O data de nascimento não pode ser nula.")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;

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

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
