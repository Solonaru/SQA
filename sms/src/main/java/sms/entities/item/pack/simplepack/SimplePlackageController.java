package sms.entities.item.pack.simplepack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sms.utils.DisplayData;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simplePackage")
@CrossOrigin(origins = "http://localhost:4200")
public class SimplePlackageController {

    @Autowired
    private ISimplePackageService simplePackageService;

    @Autowired
    private DisplayData dataDisplay;

    @RequestMapping(value = "/{simplePackageId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<SimplePackage> findSimplePacakgeById(@PathVariable("simplePackageId") int simplePackageId) {
        dataDisplay.printCrudInfo(simplePackageId);
        return simplePackageService.findSimplePackageById(simplePackageId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SimplePackage> getSimplePackages() {
        dataDisplay.printCrudInfo();
        return simplePackageService.findAllSimplePackages();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSimplePackage(@RequestBody SimplePackage simplePackage) {
        dataDisplay.printCrudInfo();
        simplePackageService.insertSimplePackage(simplePackage);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateSimplePackage(@RequestBody SimplePackage simplePackage) {
        dataDisplay.printCrudInfo();
        simplePackageService.updateSimplePackage(simplePackage);
    }

    @RequestMapping(value = "/delete/{simplePackageId}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSimplePackage(@PathVariable("simplePackageId") int simplePackageId) {
        dataDisplay.printCrudInfo(simplePackageId);
        simplePackageService.deleteSimplePackageById(simplePackageId);
    }
}
