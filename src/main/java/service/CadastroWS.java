/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.google.gson.Gson;
import dao.BordaDao;
import dao.CategoriaDao;
import dao.ProdutoDao;
import dao.SaboresDao;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import model.Borda;
import model.Categoria;
import model.Produto;
import model.Sabores;
import model.dto.BordaDTO;
import model.dto.CategoriaDTO;
import model.dto.ProdutoDTO;
import model.dto.SaboresDTO;

/**
 *
 * @author u
 */
@Path("/cadastro")
public class CadastroWS {
   
    
    @POST
    @Path("/borda")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Borda cadastroBorda(String bordaJSON){
        BordaDTO bordaDTO = new Gson().fromJson(bordaJSON, BordaDTO.class);
        Borda borda = bordaDTO.getBorda();

        try{
            return new BordaDao().salvar(borda);
        }catch(Exception e){
            return null;
        }        
    }
    
    
    @POST
    @Path("/categoria")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria cadastroCategoria(String categoriaJSON){
        CategoriaDTO categoriaDTO = new Gson().fromJson(categoriaJSON, CategoriaDTO.class);
        Categoria categoria = categoriaDTO.getCategoria();
       
        try{
            return new CategoriaDao().salvar(categoria);
        }catch(Exception e){
            return null;
        }
    }
    
    
    @POST
    @Path("/sabor")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Sabores cadastroSabor(String saboresJSON){
        SaboresDTO saboresDTO = new Gson().fromJson(saboresJSON, SaboresDTO.class);
        Sabores sabor = saboresDTO.getSabor();
        
        try{
            return new SaboresDao().salvar(sabor);
        }catch(Exception e){
            return null;
        }
    }
    
    @POST
    @Path("/produto")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Produto cadastroProduto(String produtoJSON){
        ProdutoDTO produtoDTO = new Gson().fromJson(produtoJSON, ProdutoDTO.class);
        Produto produto = produtoDTO.getProduto();
        
        try{
            return new ProdutoDao().salvar(produto);
        }catch(Exception e){
            return null;
        }
    }
    
 }   
    
 /*       
    
  
    
    outra possibilidade que funciona
    
        @POST
    @Path("/borda")
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Borda cadastroBorda(@FormParam("dado") String dadoJSON){
        Gson gson = new Gson();
        BordaDTO bordaDTO = gson.fromJson(dadoJSON, BordaDTO.class);
        Borda borda = bordaDTO.getBorda();
        
        //cadastrar no banco chamando o borda dao
        
        return borda;
        
    }
    
    */
    
    
    

