package sajadvpm.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sajadvpm.feature.person.IPersonService;
import sajadvpm.rest.dto.PersonDTO;
import sajadvpm.rest.model.PersonModelView;
import sajadvpm.util.MapperUtils;

import java.util.List;

@RestController
@RequestMapping("/api/integrations")
public class IntegrationController {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationController.class);

    private IPersonService personService;

    public IntegrationController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<PersonModelView>> show() {
        try {
            var entities = personService.get();
            var models = MapperUtils.mapAll(entities, PersonDTO.class);
            return new ResponseEntity(models, new HttpHeaders(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
