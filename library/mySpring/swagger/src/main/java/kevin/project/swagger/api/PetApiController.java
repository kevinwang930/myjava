package kevin.project.swagger.api;

import kevin.project.swagger.model.ModelApiResponse;
import kevin.project.swagger.model.Pet;
import org.springframework.core.io.Resource;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2024-03-14T21:54:51.375+08:00")

@Controller
public class PetApiController implements PetApi {

    private final PetApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public PetApiController(PetApiDelegate delegate) {
        this.delegate = delegate;
    }
    public ResponseEntity<Void> addPet(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Pet body) {
        return delegate.addPet(body);
    }

    public ResponseEntity<Void> deletePet(@ApiParam(value = "Pet id to delete",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "" ) @RequestHeader(value="api_key", required=false) String apiKey) {
        return delegate.deletePet(petId, apiKey);
    }

    public ResponseEntity<List<Pet>> findPetsByStatus(@NotNull @ApiParam(value = "Status values that need to be considered for filter", required = true, allowableValues = "available, pending, sold") @Valid @RequestParam(value = "status", required = true) List<String> status) {
        return delegate.findPetsByStatus(status);
    }

    public ResponseEntity<List<Pet>> findPetsByTags(@NotNull @ApiParam(value = "Tags to filter by", required = true) @Valid @RequestParam(value = "tags", required = true) List<String> tags) {
        return delegate.findPetsByTags(tags);
    }

    public ResponseEntity<Pet> getPetById(@ApiParam(value = "ID of pet to return",required=true) @PathVariable("petId") Long petId) {
        return delegate.getPetById(petId);
    }

    public ResponseEntity<Void> updatePet(@ApiParam(value = "Pet object that needs to be added to the store" ,required=true )  @Valid @RequestBody Pet body) {
        return delegate.updatePet(body);
    }

    public ResponseEntity<Void> updatePetWithForm(@ApiParam(value = "ID of pet that needs to be updated",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "Updated name of the pet") @RequestParam(value="name", required=false)  String name,@ApiParam(value = "Updated status of the pet") @RequestParam(value="status", required=false)  String status) {
        return delegate.updatePetWithForm(petId, name, status);
    }

    public ResponseEntity<ModelApiResponse> uploadFile(@ApiParam(value = "ID of pet to update",required=true) @PathVariable("petId") Long petId,@ApiParam(value = "Additional data to pass to server") @RequestParam(value="additionalMetadata", required=false)  String additionalMetadata,@ApiParam(value = "file to upload") @Valid @RequestPart(value="file", required=false) MultipartFile file) {
        return delegate.uploadFile(petId, additionalMetadata, file);
    }

}
