package sajadvpm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CpfValidationExeption extends  Exception {
    public CpfValidationExeption() {
        super("CPF invalido.");
    }
}
