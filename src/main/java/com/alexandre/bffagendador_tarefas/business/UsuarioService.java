package com.alexandre.bffagendador_tarefas.business;

import com.alexandre.bffagendador_tarefas.business.dto.in.EnderecoDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.LoginRequestDTO;
import com.alexandre.bffagendador_tarefas.business.dto.in.TelefoneDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.in.UsuarioDTORequest;
import com.alexandre.bffagendador_tarefas.business.dto.out.EnderecoDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.TelefoneDTOResponse;
import com.alexandre.bffagendador_tarefas.business.dto.out.UsuarioDTOResponse;
import com.alexandre.bffagendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuarioDTO(UsuarioDTORequest usuarioDTO){

       return client.salvaUsuario(usuarioDTO);
    }

    public String loginUsuario(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
    }


    public UsuarioDTOResponse buscaUsuarioPorEmail(String email, String token) {

        return client.getUsuarioPorEmail(email, token);
    }

    /**
     * @param email
     */
    public void deleteByEmail(String email, String token) {

        client.deleteusuarioPorEmail(email,token);
    }

    //Atualizacao dos dados da tabela usuario
    // A orientação é buscar o usuario via token
    public UsuarioDTOResponse atualizaDadosUsuario(UsuarioDTORequest usuarioDTO, String token){

        return client.atualizaDadosUsuario(usuarioDTO,token);
    }

    public List<UsuarioDTOResponse> findAllUsuarios(String token){

        return client.findAllUsuario(token);

    }

    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){

       return client.atualizaEndereco(enderecoDTO,idEndereco,token);


    }

    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){

        return client.atualizaTelefone(telefoneDTO,idTelefone,token);
    }

    public EnderecoDTOResponse cadastroDeEndereco(EnderecoDTORequest dto, String token){

        return client.cadastraEndereco(dto,token);

    }

    public TelefoneDTOResponse cadastroDeTelefone(String token, TelefoneDTORequest dto){

       return client.cadastraTelefone(dto,token);
    }

    public void deleteByEndereco(Long enderecoId,String token) {

        client.exclusaoDeEndereco(enderecoId,token);

    }

    public void deleteByTelefone(Long telefoneId,String token) {

       client.exclusaoDeTelefone(telefoneId,token);
    }
}
