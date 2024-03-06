package org.example.springrestipaserver.controllers.api;

import org.example.springrestipaserver.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apu/clientbook")
public class ClientBookController {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientBookController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    

}
