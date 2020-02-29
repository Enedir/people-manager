package sajadvpm.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.person.IPersonService;
import sajadvpm.feature.person.Person;
import sajadvpm.feature.person.command.PersonCommandRegister;
import sajadvpm.rest.dto.PersonDTO;
import sajadvpm.rest.model.PersonModelView;
import sajadvpm.util.MapperUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationController.class);

    private IPersonService personService;

    public IntegrationController(IPersonService personService) {
        this.personService = personService;
    }


    @PostMapping("/receive")
    public ResponseEntity<List<PersonDTO>> receive(@RequestBody List<PersonDTO> request) {

        logger.info("Iniciando o processo de internalização do registros na base via integração.");

        try {
            var dtos = MapperUtils.mapAll(request, Person.class);
            var persons = personService.upsert(dtos);
            var response = MapperUtils.mapAll(persons, PersonDTO.class);
            return new ResponseEntity(response, new HttpHeaders(), HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/getall")
    public ResponseEntity<List<PersonDTO>> getAll() {
        try {
            var entities = personService.get();
            var models = MapperUtils.mapAll(entities, PersonDTO.class);
            return new ResponseEntity(models, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
