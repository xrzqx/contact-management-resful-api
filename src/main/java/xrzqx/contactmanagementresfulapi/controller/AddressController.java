package xrzqx.contactmanagementresfulapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(
            path = "/api/contacts/{idContact}/addresses/{idAddress}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable("idContact") String idContact,
                                            @PathVariable("idAddress") String idAddress
                                            ){
        AddressResponse addressResponse = addressService.get(user,idContact,idAddress);
        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
    }

    @DeleteMapping(
            path = "/api/contacts/{idContact}/addresses/{idAddress}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user,
                                            @PathVariable("idContact") String idContact,
                                            @PathVariable("idAddress") String idAddress
    ){
        addressService.delete(user,idContact,idAddress);
        return WebResponse.<String>builder().data("OK").build();
    }
}
