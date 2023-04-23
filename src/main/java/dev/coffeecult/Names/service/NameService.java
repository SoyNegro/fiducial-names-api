package dev.coffeecult.Names.service;

import dev.coffeecult.Names.model.Name;
import dev.coffeecult.Names.model.NamesInsertedResponse;
import dev.coffeecult.Names.repository.NamesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class NameService {
    private final NamesRepository namesRepository;

    /*
    * Fetch the list of names persisted on the database. Use pagination
    * to allow returns of sensible sizes.
    * @param pageRequest PageRequest of given page and size
    * @return A Page containing the list of Names
    * */
    public ResponseEntity<Page<String>> getNames(PageRequest pageRequest) {
        var names = namesRepository.findAll(pageRequest).map(Name::getFirstName);
        return new ResponseEntity<>(names, HttpStatus.OK);
    }

    /*
    * Checks whether a name already exist on the database.
    * @param firstName Name to be persisted
    * @return True if the name exist False otherwise.
    * */
    public ResponseEntity<Boolean> existName(String firstName) {
        var exist = namesRepository.existsByFirstName(firstName);
        return new ResponseEntity<>(exist, HttpStatus.OK);
    }
/*
* Expects a Set, being compliant with constraints, of names to be persisted.
* Given a non-unique name an Exception is expected but tests show it can be delayed
* until queried. Therefore, we manually assure every name is unique.
* @param names Set of names to be persisted
* @return A Set of Names in the form of NameInsertedResponse
* */
    public ResponseEntity<NamesInsertedResponse> insertNames(Set<String> names) {
        var insertedNames = new HashSet<String>();
        names.forEach(
                name -> {
                    if (!namesRepository.existsByFirstName(name)) {
                        namesRepository.save(new Name(name));
                        insertedNames.add(name);
                    }
                }
        );
        return new ResponseEntity<>(new NamesInsertedResponse(insertedNames),HttpStatus.OK);
    }
}
