package com.egg.libreriaapi.servicios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;//para usas el "readOnly = true"

import com.egg.libreriaapi.entidades.Editorial;
import com.egg.libreriaapi.models.Editorial.EditorialCreateDTO;
import com.egg.libreriaapi.models.Editorial.EditorialDarBajaDTO;
import com.egg.libreriaapi.models.Editorial.EditorialDeleteDTO;
import com.egg.libreriaapi.models.Editorial.EditorialListDTO;
import com.egg.libreriaapi.repositorio.EditorialRepositorio;



@Service
public class EditorialServicios {

    @Autowired
    private EditorialRepositorio editorialRepositorio;
    
    //crear editorial
    @Transactional
    public void crearEditorial(String nombre){
        Editorial editorial = new Editorial();

        editorial.setNombreEditorial(nombre);
        editorial.setEditorialActiva(true);
        
        editorialRepositorio.save(editorial);
    }
    //--------------------USANDO EL DTO
    @Transactional
    public void crearEditorialDTO(EditorialCreateDTO editorialCreateDTO){
       
        Editorial editorialNva = new Editorial();//instanciamos un objeto del tipo editorial

        //asignamos nombre, pero obtemos el atributo NombreEditoriaDTO, del editorialCreateDTO
        editorialNva.setNombreEditorial(editorialCreateDTO.getNombreEditorialDTO());
        editorialNva.setEditorialActiva(editorialCreateDTO.getEditorialActivaDTO());

        editorialRepositorio.save(editorialNva); //persistimos el dato

    }

    //Metodo para obtener una Editorial por ID
    @Transactional
    public Editorial editorialPorId(UUID id) throws Exception{
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()) {
            //obtener los atributos y guardarlos en 'editorial'
            Editorial editorial = respuesta.get();
            return editorial;
            
        }else{
            throw new Exception ("Editorial no encontrada");
        }
    }

    //metodo para listar todas las editoriales
    @Transactional(readOnly = true)
    public List<Editorial> listaEditoriales(){
        return editorialRepositorio.findAll();
    }

    @Transactional
    public List<EditorialListDTO> listarEditorialDTO(){
        //imporatmos la consulta echa en el repositorio
        return editorialRepositorio.listarEditoriales();
    }


    //metodo para dar de baja una editorial por ID
    @Transactional
    public void darBajaEditorial(UUID id){
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if (respuesta.isPresent()) {

            Editorial editorial = respuesta.get();
            editorial.setEditorialActiva(false);

            editorialRepositorio.save(editorial);
        }
    }

    @Transactional
    public void darBajaEditorialDTO(EditorialDarBajaDTO editorialBajaDTO) throws Exception{
        Optional<Editorial> editorialRpta=editorialRepositorio.findById(editorialBajaDTO.getIdEditorial());

        if (editorialRpta != null || editorialRpta.isPresent()) {
            Editorial editorial = editorialRpta.get();
            editorial.setEditorialActiva(false);

            editorialRepositorio.save(editorial);
        }
    }

    //metodo para actualizar el nombre de la editorial    
    @Transactional
    public void modificarEditorial(UUID id, String nuevonombre) {
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);

        if (respuesta.isPresent()){
            Editorial editorial = respuesta.get();

            editorial.setNombreEditorial(nuevonombre);
            editorialRepositorio.save(editorial);
        }
    }
    
    //usamos Para implementar el Path Variable
    @Transactional(readOnly = true)
    public Editorial getOneEdi(UUID id){
        return editorialRepositorio.getReferenceById(id);
    }
    
    @Transactional
    public void eliminarEditorial(UUID id)throws Exception{
        editorialRepositorio.deleteById(id);
    }

    @Transactional
    public void eliminarEditorialDTO(EditorialDeleteDTO editorialEliDTO)throws Exception{
        editorialRepositorio.deleteById(editorialEliDTO.getIdEditorial());
    }
}
