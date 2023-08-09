package xrzqx.contactmanagementresfulapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import xrzqx.contactmanagementresfulapi.entity.Address;
import xrzqx.contactmanagementresfulapi.entity.Contact;
import xrzqx.contactmanagementresfulapi.entity.User;
import xrzqx.contactmanagementresfulapi.model.AddressResponse;
import xrzqx.contactmanagementresfulapi.model.CreateAddressRequest;
import xrzqx.contactmanagementresfulapi.repository.AddressRepository;
import xrzqx.contactmanagementresfulapi.repository.ContactRepository;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public AddressResponse create(User user, CreateAddressRequest request){
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getIdContact())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is Not Found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvice(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        addressRepository.save(address);

        return toAddressResponse(address);
    }

    private AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .province(address.getProvice())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }
}
