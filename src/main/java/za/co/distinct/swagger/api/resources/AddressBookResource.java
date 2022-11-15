
package za.co.distinct.swagger.api.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.distinct.swagger.api.models.Contact;

/**
 *
 * @author FMphore
 */
@RestController
@RequestMapping("contacts")
@Log4j2
public class AddressBookResource {
    
    private final ConcurrentMap<String, Contact> contactList = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(AddressBookResource.class);
    
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable("id") String id) throws JsonProcessingException {
        String contactJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactList.get(id));
        logger.info("GET Contact By ID: "+ contactJson);
        return contactList.get(id);
    }
    
    @GetMapping
    public Collection<Contact> getAllContacts() throws JsonProcessingException {
        String contactListJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactList.values());
        logger.info("GET All Contacts: "+ contactListJson);
        return contactList.values();
    }
    
    @PostMapping
    public Contact addContact(@RequestBody Contact contact) throws JsonProcessingException {
        contactList.put(contact.getId(), contact);
        String contactJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contact);
        logger.info("POST Contact: "+ contactJson);
        return contact;
    }
    
    @PostMapping("/all")
    public List<Contact> saveAllContacts(@RequestBody List<Contact> newContactList) throws JsonProcessingException {        
        ConcurrentHashMap<String, Contact> newContactsMap = newContactList.stream().collect(Collectors.toMap(Contact::getId, Function.identity(), (contact, nextContact) -> contact, ConcurrentHashMap::new));
        String contactJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newContactsMap);
        logger.info("POST All Contacts: "+ contactJson);
        this.contactList.putAll(newContactsMap);
        return newContactList;
    }
}
