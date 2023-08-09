package xrzqx.contactmanagementresfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xrzqx.contactmanagementresfulapi.entity.User;
import xrzqx.contactmanagementresfulapi.model.AddressResponse;
import xrzqx.contactmanagementresfulapi.model.CreateAddressRequest;
import xrzqx.contactmanagementresfulapi.model.WebResponse;
import xrzqx.contactmanagementresfulapi.service.AddressService;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping(
            path = "/api/contacts/{idContact}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable("idContact") String idContact) {
        request.setIdContact(idContact);
        AddressResponse addressResponse = addressService.create(user, request);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }
}
