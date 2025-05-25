package br.com.estampamente.services;

import br.com.estampamente.entities.Client;
import br.com.estampamente.entities.DTOs.ClientDTO;
import br.com.estampamente.entities.DTOs.OrderDTO;
import br.com.estampamente.repositories.ClientRepository;
import br.com.estampamente.utils.DtoConverters;
import br.com.estampamente.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequestMapping("/perfil")
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;
    private final OrderService orderService;

    @Autowired
    public ClientService(ClientRepository clientRepository, OrderService orderService) {
        this.clientRepository = clientRepository;
        this.orderService=orderService;
    }

    public Client createClient(Client client) {
        client.setPassword(PasswordUtils.hashPassword(client.getPassword()));
        return clientRepository.save(client);
    }

    public List<Client> getClient() {
        return clientRepository.findAll();
    }

    public ClientDTO findClientProfile(Long id) {
            Optional<Client> client = clientRepository.findById(id);
            List<OrderDTO> orderDTOS  = orderService.getAllOrders(id);
            ClientDTO clientDTO = new ClientDTO();
            if (client.isPresent()){
                clientDTO.setId(client.get().getId());
                clientDTO.setNome(client.get().getName());
                clientDTO.setEmail(client.get().getEmail());
                clientDTO.setPhoneNumber(client.get().getPhoneNumber());
                clientDTO.setOrders(orderDTOS);
                clientDTO.setAddress(DtoConverters.toAddressDtoList(client.get().getAddresses()));
                clientDTO.setCards(DtoConverters.toCreditCardDtoList(client.get().getCreditCards()));
            }
            return clientDTO;
    }
}
