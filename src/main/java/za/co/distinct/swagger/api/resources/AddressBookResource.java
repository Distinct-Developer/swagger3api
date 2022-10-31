
package za.co.distinct.swagger.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
public class AddressBookResource {
    
    private ConcurrentMap<String, Contact> contactList = new ConcurrentHashMap<>();
    
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable("id") String id){
        return contactList.get(id);
    }
    
    @GetMapping
    public List<Contact> getAllContacts(){
        return new ArrayList<>(contactList.values());
    }
    
    @PostMapping
    public Contact addContact(@RequestBody Contact contact){
        contactList.put(contact.getId(), contact);
        return contact;
    }
}
