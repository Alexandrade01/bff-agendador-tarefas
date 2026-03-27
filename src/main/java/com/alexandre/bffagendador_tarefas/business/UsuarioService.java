package com.alexandre.bffagendador_tarefas.business;

import com.alexandre.bffagendador_tarefas.business.dto.EnderecoDTO;
import com.alexandre.bffagendador_tarefas.business.dto.TelefoneDTO;
import com.alexandre.bffagendador_tarefas.business.dto.UsuarioDTO;
import com.alexandre.bffagendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTO salvaUsuarioDTO(UsuarioDTO usuarioDTO){

       return client.postUsuario(usuarioDTO);
    }

    public String loginUsuario(UsuarioDTO usuarioDTO){
        return client.login(usuarioDTO);
    }


    public UsuarioDTO buscaUsuarioPorEmail(String email, String token) {

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
    public UsuarioDTO atualizaDadosUsuario(UsuarioDTO usuarioDTO,String token){

        return client.atualizaDadosUsuario(usuarioDTO,token);
    }

    public List<UsuarioDTO> findAllUsuarios(String token){

        return client.findAllUsuario(token);

    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO,String token){

       return client.atualizaEndereco(enderecoDTO,idEndereco,token);


    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO telefoneDTO,String token){

        return client.atualizaTelefone(telefoneDTO,idTelefone,token);
    }

    public EnderecoDTO cadastroDeEndereco(EnderecoDTO dto,String token){

        return client.cadastraEndereco(dto,token);

    }

    public TelefoneDTO cadastroDeTelefone(String token, TelefoneDTO dto){

       return client.cadastraTelefone(dto,token);
    }

    public void deleteByEndereco(Long enderecoId,String token) {

        client.exclusaoDeEndereco(enderecoId,token);

    }

    public void deleteByTelefone(Long telefoneId,String token) {

       client.exclusaoDeTelefone(telefoneId,token);
    }
}
