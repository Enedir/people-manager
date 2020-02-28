package sajadvpmtest.feature.person;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sajadvpm.feature.file.File;
import sajadvpm.feature.person.Person;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    @Test
    void deveria_inativar_uma_pessoa() {
        // Arrange
        Person person = new Person();

        // Act
        person.deactivate();

        // Assert
        assertFalse(person.getActive());
        assertNotNull(person.getUpdateDate());
    }

    @Test
    void deveria_setar_dados_basico_em_uma_pessoa() {
        Person person = new Person();
        File avatar = Mockito.mock(File.class);
        // Act
        person.newPerson(avatar);

        // Assert
        assertNotNull(person.getAvatar());
        assertTrue(person.getActive());
        assertNotNull(person.getInsertDate());
    }

    @Test
    void deveria_atualizar_registro() {
        Person person = new Person();
        File avatar = Mockito.mock(File.class);
        Person existing = Mockito.mock(Person.class);

        // Act
        person.updatePerson(existing, avatar);

        // Assert
        assertNotNull(person.getAvatar());
        assertTrue(person.getActive());
        assertNotNull(person.getUpdateDate());
    }

    @Test
    void deverrria_checar_se_o_avatar_e_valido() {
        File avatar = Mockito.mock(File.class);
        Person person = new Person();
        person.setAvatar(avatar);

        // Act
        var result = person.hasAvatar();

        // Assert
        assertTrue(result);
    }

    @Test
    void deverrria_checar_se_o_avatar_e_invalido() {
        Person person = new Person();

        // Act
        var result = person.hasAvatar();

        // Assert
        assertFalse(result);
    }
}