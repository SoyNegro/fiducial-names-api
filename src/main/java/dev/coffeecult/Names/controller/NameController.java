package dev.coffeecult.Names.controller;

import dev.coffeecult.Names.model.NamesInsertedResponse;
import dev.coffeecult.Names.service.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class NameController {

    private final NameService nameService;

    /*
     * /api/names
     * Endpoint to get the list of names from the database.
     * The list if obtained through pagination so list size can remain sensible.
     * Since even if there aren't elements in the database a Page will always be returned
     * the <code>HttpStatus.NOT_FOUND</code< was omitted
     * @param page Index of the Page to be obtained
     * @param size Amount of elements in the Page to be obtained
     * @return A Page of Strings and <code>HttpStatus.OK</code>
     * */
    @GetMapping("names")
    public ResponseEntity<Page<String>> getNames(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "20") int size
    ) {
        return nameService.getNames(PageRequest.of(page, size));
    }

    /*
     * /api/exist/{name}
     * Endpoint to know if a given name already exist on the database.
     * Given the fact that the return type is boolean we avoid using
     * <code>HttpStatus.NOT_FOUND</code>
     * @param firstName Name to be searched
     * @return <code>true</code> or <code>false</code> and <code>HttpStatus.OK</code>
     * */
    @GetMapping("exist/{name}")
    public ResponseEntity<Boolean> existByName(@PathVariable("name") final String firstName) {
        return nameService.existName(firstName);
    }

    /*
     * /api/names
     * Endpoint to insert a Set of names in the database
     * @param names Set of names to be persisted
     * @return A set of names in the form of <code>NameInsertedResponse</code> and <code>HttpStatus.OK</code>
     * */
    @PostMapping("names")
    public ResponseEntity<NamesInsertedResponse> insertNames(@RequestBody final Set<String> names) {
        return nameService.insertNames(names);
    }

    /*
     * /api/name
     * Endpoint to delete a given name from the database
     * @param firstName Name to be deleted
     * @return A String and <code>HttpStatus.OK</code> if name was deleted <code>HttpStatus.NOT_FOUND</code> otherwise
     * */
    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteName(@PathVariable("name") final String firstName) {
        return nameService.deleteName(firstName);
    }
}
