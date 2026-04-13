package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurveController.class)
public class CurvePointControllerTest {

    @MockBean
    private CurvePointServiceInterface curvePointService;

    @Autowired
    private MockMvc mockMvc;

    //GET List: on crée dans un premier temps un objet de test
    //On se rend sur l'url /curvePoint/list
    //On vérifie que la requête est ok, qu'on a la bonne view et qu'on a bien la liste
    @Test
    void testList() throws Exception{
        CurvePoint cp = new CurvePoint();
        cp.setId(1);
        cp.setCurveId(10);
        cp.setTerm(10d);
        cp.setValue(30d);

        when(curvePointService.findAll()).thenReturn(List.of(cp));

        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"))
                .andExpect(model().attributeExists("curvePoints"));
    }

    //TEST GET page add, on vérifie que lorsqu'on fait la requête tout est ok et on récupère la bonne vue
    @Test
    void testAddPage() throws Exception{
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    //TEST ADD une nouvelle cp avec respect des champs et redirection vers list après ajout
    @Test
    void testValidate() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", "10")
                .param("term", "20d")
                .param("value", "30d"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    //TEST ADD avec non-respect d'un champ et retour vers la page d'ajout
    @Test
    void testValidate_withInvalidData() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                .param("curveId", "")
                .param("term", "20d")
                .param("value", "30d"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }
    //TEST GET formulaire de mise à jour d'une cp
    @Test
    void testShowUpdateForm() throws Exception{
        //On crée une cp afin de pouvoir en avoir une qu'on puisse modifier
        CurvePoint cp = new CurvePoint();
        cp.setId(1);

        when(curvePointService.findById(1)).thenReturn(cp);

        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"))
                .andExpect(model().attributeExists("curvePoint"));
    }

    //TEST POST mise à jour réussie d'une cp
    @Test
    void testUpdateCurvePoint() throws Exception{
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "10")
                        .param("term", "50d")
                        .param("value", "30d"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    //TEST POST lors que les données mise à jour ne respectent pas le format donc on retourne vers le formulaire
    @Test
    void testUpdateCurvePoint_whenInvalidData() throws Exception {
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "")
                        .param("term", "50d")
                        .param("value", "30d"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }

    //TEST GET lorsqu'on supprime une cp
    @Test
    void testDeleteCurvePoint() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

}
