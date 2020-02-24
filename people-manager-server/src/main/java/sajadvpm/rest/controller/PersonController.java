package sajadvpm.rest.controller;

import com.sun.net.httpserver.Authenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sajadvpm.exception.NotFoundException;
import sajadvpm.feature.file.File;
import sajadvpm.feature.file.IFileService;
import sajadvpm.feature.person.IPersonService;
import sajadvpm.feature.person.Person;
import sajadvpm.feature.person.command.PersonCommandRegister;
import sajadvpm.rest.model.PersonModelView;
import sajadvpm.util.MapperUtils;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private IPersonService personService;
    private IFileService fileService;

    public PersonController(IPersonService personService, IFileService fileService) {
        this.personService = personService;
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody PersonCommandRegister command) {

        //TODO: Validar os dados de entrada.

        Integer personId = Integer.MIN_VALUE;

        try {
            Person entity = MapperUtils.map(command, Person.class);

            File avatar = fileService.get(command.getAvatarId());

            entity.setAvatar(avatar);

            personId = personService.add(entity);

        } catch (NotFoundException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(personId, new HttpHeaders(), HttpStatus.OK);
    }

//    @GetMapping("/getall")
//    public  ResponseEntity<List<SolicitationModelView>> getAll() {
//        try {
//            List<Solicitation> entities = Lists.newArrayList(service.get());
//            HttpHeaders responseHeaders = new HttpHeaders();
//            return new ResponseEntity<>(convertListToView(entities), responseHeaders, HttpStatus.OK);
//        }catch (Exception ex){
//            ex.printStackTrace();
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonModelView> get(@PathVariable(required = true) Integer id) {

        try {

            Person entity = personService.get(id);

            return new ResponseEntity(MapperUtils.map(entity, PersonModelView.class), new HttpHeaders(), HttpStatus.OK);
        } catch (NotFoundException ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
