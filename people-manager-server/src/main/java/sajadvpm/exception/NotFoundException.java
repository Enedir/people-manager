package sajadvpm.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(Integer id) {
        super("Não foi possível encontrar o registro:" + id);
    }
}