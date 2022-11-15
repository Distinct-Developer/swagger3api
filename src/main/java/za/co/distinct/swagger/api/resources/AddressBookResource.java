
package za.co.distinct.swagger.api.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.distinct.swagger.api.models.Contact;
import static za.co.distinct.swagger.api.utils.JsonUtil.setWriterJsonDataFormats;

/**
 *
 * @author Boiki Mphore
 * @since 2022-11-15
 */
@RestController
@RequestMapping("contacts")
@Log4j2
public class AddressBookResource {
    
    private final ConcurrentMap<String, Contact> contactList = new ConcurrentHashMap<>();
    
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable("id") String id) throws JsonProcessingException {
        String contactJson = setWriterJsonDataFormats().writeValueAsString(contactList.get(id));
        log.info("GET Contact By ID: "+ contactJson);
        return ResponseEntity.ok(contactList.get(id));
    }
    
    @GetMapping
    public ResponseEntity<Collection<Contact>> getAllContacts() throws JsonProcessingException {
        String contactListJson = setWriterJsonDataFormats().writeValueAsString(contactList.values());
        log.info("GET All Contacts: "+ contactListJson);
        ResponseEntity.internalServerError().body(log);
        return ResponseEntity.ok(contactList.values());
    }
    
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) throws JsonProcessingException {
        contactList.put(contact.getId(), contact);
        String contactJson = setWriterJsonDataFormats().writeValueAsString(contact);
        log.info("POST Contact: "+ contactJson);
        return ResponseEntity.ok(contact);
    }
    
    @PostMapping("/all")
    public ResponseEntity<List<Contact>> addAllContacts(@RequestBody List<Contact> newContactList) throws JsonProcessingException {        
        ConcurrentHashMap<String, Contact> newContactsMap = newContactList.stream().collect(Collectors.toMap(Contact::getId, Function.identity(), (contact, nextContact) -> contact, ConcurrentHashMap::new));
        String contactJson = setWriterJsonDataFormats().writeValueAsString(newContactsMap);
        log.info("POST All Contacts: "+ contactJson);
        this.contactList.putAll(newContactsMap);
        return ResponseEntity.ok(newContactList);
    }
}
