package sel.prac.springboot.BlogApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)    //This will cause spring boot to responsed with specific status code
public class ResourceNotFoundException extends RuntimeException{

    private String resourcename;
    private String fieldname;
    private long fieldvalue;

    public ResourceNotFoundException(String resourcename, String fieldname, long fieldvalue) {
        super(String.format("%s not found with %s : '%s'",resourcename, fieldname, fieldvalue)); //Post not Foud with id :1
        this.resourcename = resourcename;
        this.fieldname = fieldname;
        this.fieldvalue = fieldvalue;
    }

    public String getResourcename() {
        return resourcename;
    }

    public String getFieldname() {
        return fieldname;
    }

    public long getFieldvalue() {
        return fieldvalue;
    }
}
