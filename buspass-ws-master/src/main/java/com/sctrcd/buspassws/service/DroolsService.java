package com.sctrcd.buspassws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sctrcd.buspassws.model.ItemCountUser;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sctrcd.buspassws.model.BusPass;
import com.sctrcd.buspassws.model.Person;

@Service
public class DroolsService {

    private static Logger log = LoggerFactory.getLogger(DroolsService.class);

    private final KieContainer kieContainer;

    @Autowired
    public DroolsService(KieContainer kieContainer) {
        log.info("Initialising a new bus pass session.");
        this.kieContainer = kieContainer;
    }

    /**
     * Create a new session, insert a person's details and fire rules to
     * determine what kind of bus pass is to be issued.
     */
    public ItemCountUser getItemCountUser(ItemCountUser card) {
        KieSession kieSession = kieContainer.newKieSession("ItemCountUserSession");
        kieSession.insert(card);
        kieSession.fireAllRules();

        ItemCountUser doneCard = findItemCountUser(kieSession);
        kieSession.dispose();
        return doneCard;
    }
    
    /**
     * Search the {@link KieSession} for bus passes.
     */
    private ItemCountUser findItemCountUser(KieSession kieSession) {
        
        // Find all BusPass facts and 1st generation child classes of BusPass.
        ObjectFilter busPassFilter = new ObjectFilter() {
            public boolean accept(Object object) {
                if (ItemCountUser.class.equals(object.getClass())) return true;
                if (ItemCountUser.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        // printFactsMessage(kieSession);
        
        List<ItemCountUser> facts = new ArrayList<ItemCountUser>();
        for (FactHandle handle : kieSession.getFactHandles(busPassFilter)) {
            facts.add((ItemCountUser) kieSession.getObject(handle));
        }
        if (facts.size() == 0) {
            return null;
        }
        // Assumes that the rules will always be generating a single bus pass. 
        return facts.get(0);
    }
    
    /**
     * Print out details of all facts in working memory.
     * Handy for debugging.
     */
    @SuppressWarnings("unused")
    private void printFactsMessage(KieSession kieSession) {
        Collection<FactHandle> allHandles = kieSession.getFactHandles();
        
        String msg = "\nAll facts:\n";
        for (FactHandle handle : allHandles) {
            msg += "    " + kieSession.getObject(handle) + "\n";
        }
        System.out.println(msg);
    }

}
