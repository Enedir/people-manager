package sajadvpmtest.feature.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.Repository;
import sajadvpm.exception.CpfValidationExeption;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.FileRepository;
import sajadvpm.feature.person.Person;
import sajadvpm.feature.person.PersonRepository;
import sajadvpm.feature.person.PersonService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PersonServiceTest {

    private PersonService service;
    private PersonRepository mockPersonRepository;
    private FileRepository mockFileRepository;


    @BeforeEach
    void setUp() {
        mockPersonRepository = Mockito.mock(PersonRepository.class);
        mockFileRepository = Mockito.mock(FileRepository.class);

        service = new PersonService(mockPersonRepository, mockFileRepository);
    }

    @Test
    void deveria_salvar_uma_pessoa_com_cpf_valido() {
        // Arrange
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        var person = new Person("TESTE", "76060106099", "teste@gmail", LocalDate.now(), Boolean.FALSE);

        // Act
        Integer result = null;
        try {
            result = service.add(person, null);
        } catch (CpfValidationExeption e) {
            fail(e.getMessage());
        }

        // Assert
        assertEquals(1, result);
    }

    @Test
    void nao_deveria_salvar_uma_pessoa_com_cpf_invalido() {
        // Arrange
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        var person = new Person("TESTE", "111111", "teste@gmail", LocalDate.now(), Boolean.FALSE);

        // Act and Assert
        assertThatThrownBy(() -> service.add(person, null))
                .isInstanceOf(CpfValidationExeption.class);
    }


    @Test
    void deveria_atualizar_uma_pessoa_com_cpf_valido() {
        // Arrange
        when(mockPersonRepository.findById(any())).thenReturn(Optional.of(new Person(1)));
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        var person = new Person("TESTE", "76060106099", "teste@gmail", LocalDate.now(), Boolean.FALSE);

        // Act
        Boolean result = null;
        try {
            result = service.update(person, null);
        } catch (NotFoundException | CpfValidationExeption e) {
            fail(e.getMessage());
        }

        // Assert
        assertTrue(result);
    }

    @Test
    void nao_deveria_atualizar_uma_pessoa_com_cpf_invalido_com_o_seu_avatar() {
        // Arrange
        when(mockPersonRepository.findById(any())).thenReturn(Optional.of(new Person(1)));
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        var person = new Person("TESTE", "99999999", "teste@gmail", LocalDate.now(), Boolean.FALSE);

        // Act and Assert
        assertThatThrownBy(() -> service.update(person, null))
                .isInstanceOf(CpfValidationExeption.class);
    }

    @Test
    void deveria_deletar_uma_pessoa_valida() {
        // Arrange
        when(mockPersonRepository.findById(any())).thenReturn(Optional.of(new Person(1)));
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        // Act
        Boolean result = null;
        try {
            result = service.delete(1);
        } catch (NotFoundException e) {
            fail(e.getMessage());
        }

        // Assert
        assertTrue(result);
    }

    @Test
    void nao_deveria_deletar_uma_pessoa_invalida() {
        // Arrange
        when(mockPersonRepository.save(any())).thenReturn(new Person(1));

        // Act and Assert
        assertThatThrownBy(() -> service.delete(1))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void deveria_salvar_e_atualizar_uma_integracao() {
        // Arrange

        var mockList = new ArrayList<Person>();
        mockList.add(new Person());
        mockList.add(new Person());

        when(mockPersonRepository.findById(any())).thenReturn(Optional.of(new Person(1)));
        when(mockPersonRepository.saveAll(any())).thenReturn(mockList);

        var persons = new ArrayList<Person>();
        persons.add(new Person("TESTE1", "76060106099", "teste1@gmail", LocalDate.now(), Boolean.TRUE));
        persons.add(new Person(1, "TESTE2", "76060106045", "teste2@gmail", LocalDate.now(), Boolean.TRUE));

        List<Person> result = null;
        // Act
        result = service.upsert(persons);


        // Assert
        assertNotNull( result);
    }
}