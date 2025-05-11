//package br.com.estampamente.security;
//
//import br.com.estampamente.entities.Client;
//import br.com.estampamente.repositories.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Client client = this.clientRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado para o e-mail " + username));
//        return new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(), new ArrayList<>());
//    }
//}
