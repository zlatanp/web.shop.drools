package com.sctrcd.buspassws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sctrcd.buspassws.model.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.ObjectFilter;
import org.kie.api.runtime.rule.FactHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DroolsServiceCeoRacun {

    private static Logger log = LoggerFactory.getLogger(DroolsServiceCeoRacun.class);

    private final KieContainer kieContainer;

    @Autowired
    public DroolsServiceCeoRacun(KieContainer kieContainer) {
        log.info("Initialising a new bus pass session.");
        this.kieContainer = kieContainer;
    }

    /**
     * Create a new session, insert a person's details and fire rules to
     * determine what kind of bus pass is to be issued.
     */
    public ItemCount getItemCount(ItemCountUser card) {
        KieSession kieSession = kieContainer.newKieSession("DroolsServiceCeoRacun");
        kieSession.insert(card);
        kieSession.fireAllRules();

        ItemCount doneItem = findItemCount(kieSession);
        kieSession.dispose();
        return doneItem;
    }

    /**
     * Search the {@link KieSession} for bus passes.
     */
    private ItemCount findItemCount(KieSession kieSession) {

        // Find all BusPass facts and 1st generation child classes of BusPass.
        ObjectFilter busPassFilter = new ObjectFilter() {
            public boolean accept(Object object) {
                if (ItemCount.class.equals(object.getClass())) return true;
                if (ItemCount.class.equals(object.getClass().getSuperclass())) return true;
                return false;
            }
        };

        // printFactsMessage(kieSession);

        List<ItemCount> facts = new ArrayList<ItemCount>();
        for (FactHandle handle : kieSession.getFactHandles(busPassFilter)) {
            facts.add((ItemCount) kieSession.getObject(handle));
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
