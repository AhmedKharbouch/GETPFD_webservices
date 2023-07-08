package com.example.Depotservice.webController;

import com.example.Depotservice.entities.Depot;
import com.example.Depotservice.entities.Rangee;
import com.example.Depotservice.exceptions.DepotExistException;
import com.example.Depotservice.exceptions.DepotFULL;
import com.example.Depotservice.exceptions.TypeRangeeExistException;
import com.example.Depotservice.repositories.DepotRepository;
import com.example.Depotservice.repositories.RangeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class DepotController {
    //private ProductService productService;

    private DepotRepository depotRepository;

    private RangeeRepository rangeeRepository;


    @GetMapping(path = "/depots")
    public Collection<Depot> getDepots(){
        return depotRepository.findAll();

    }
    @GetMapping(path = "/depots/{id}")
    public Depot getDepotByID(@PathVariable("id")Long id){
        Depot depotById = depotRepository.findDepotById(id);

        for (Rangee rangee : depotById.getRangees()) {{

            System.out.println(rangee.getNom());
            System.out.println(rangee.getCurrentSize());
        }

        }

        return depotById;

    }

    @PostMapping(path = "/addDepot")
    public Depot addDepot(@RequestBody Depot depot) throws DepotExistException {

        Depot depot1=depotRepository.findDepotByNom(depot.getNom());

        if(depot1!=null)
            throw  new DepotExistException("Depot already exist") ;

        depot.setCreatedAt(new Date());
        depot.setModifiedAt(null);
        return depotRepository.save(depot);

    }
    @PostMapping (path = "/updateDepot")
    public Depot updateDepot(@RequestBody Depot depot){

        Depot depot1=depotRepository.findDepotById(depot.getId());

        if(depot1!=null){
            depot.setModifiedAt(new Date());
            return depotRepository.save(depot);
        }else {
            return new Depot();
        }


    }
    @DeleteMapping (path = "/deleteDepot/{id}")
    public boolean DeleteProduct(@PathVariable Long id){

        Depot depot = depotRepository.findDepotById(id);

        if(depot!=null){
            depotRepository.deleteById(id);

            //delete all rangees
            depot.getRangees().removeAll(depot.getRangees());

            return true;
        }else {
            return false;
        }


    }

    /* ------------------------------- Category --------------------------------------- */

// create all api for TypeRangee



    @GetMapping(path = "/rangees")
    public Collection<Rangee> getTypeRangees(){
        return rangeeRepository.findAll();

    }
    @GetMapping(path = "/rangees/{id}")
    public Rangee getTypeRangeeByID(@PathVariable("id")Long id){
        return rangeeRepository.findRangeeById(id);

    }

    @PostMapping(path = "/addRangee")
    public Rangee addTypeRangee(@RequestBody Rangee typeRangee) throws TypeRangeeExistException {

        Rangee typeRangee1=rangeeRepository.findRangeeByNom(typeRangee.getNom());



        if(typeRangee1!=null)
            throw  new TypeRangeeExistException("TypeRangee already exist") ;

        typeRangee.setCreatedAt(new Date());
        return rangeeRepository.save(typeRangee);

    }
    @PostMapping (path = "/updateRangee")
    public Rangee updateTypeRangee(@RequestBody Rangee rangee) {

        Rangee rangee1 = rangeeRepository.findRangeeById(rangee.getId());

        if (rangee1 != null) {
            rangee.setModifiedAt(new Date());
            return rangeeRepository.save(rangee);
        } else {
            return new Rangee();
        }


    }

    // add TypeRangee to Depot
    @PostMapping (path = "/addRangeeToDepot/{id}/{idRangee}")
    public Depot addTypeRangeeToDepot(@PathVariable("id")Long id, @PathVariable("idRangee") Long idRangee ) throws TypeRangeeExistException, DepotExistException, DepotFULL {

        Depot depot = depotRepository.findDepotById(id);


        if (depot == null) throw new DepotExistException("Depot not found");

        Rangee rangee = rangeeRepository.findRangeeById(idRangee);

        if (rangee == null) throw new TypeRangeeExistException("TypeRangee not found");

        System.out.println(depot.getId());
        //verifier si la taille de la liste des rangees est inferieur a la taille du depot
        if (depot.getCurrentSize() + rangee.getMaxSize() <= depot.getMaxSize()) {

            if(depot.getRangees().contains(rangee)){

                depot.getRangees().forEach(rangee1 -> {
                    if(Objects.equals(rangee1.getId(), rangee.getId())){
                        rangee1.setModifiedAt(new Date());
                        rangee1.setMaxSize(rangee1.getMaxSize()+rangee.getMaxSize());
                    }
                });

            }else{


            depot.getRangees().add(rangee);
            }
            depot.setCurrentSize(depot.getCurrentSize() + rangee.getMaxSize());
            depot.setModifiedAt(new Date());
            return depotRepository.save(depot);


        }else{
            throw new DepotFULL("Depot is full");
        }
    }

    //get all rangees of depot
    @GetMapping(path = "/rangeesOfDepot/{id}")
    public Collection<Rangee> getRangeesOfDepot(@PathVariable("id")Long id){
        Depot depot = depotRepository.findDepotById(id);
        return depot.getRangees();

    }
}
